package com.basicbackend.infrastructure.security;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @description 过滤器处理所有HTTP请求，并检查是否存在带有正确令牌的Authorization标头。例如，如果令牌未过期或签名密钥正确。
 */
@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final UserDetailsServiceImpl userDetailsService;
    private static final Logger logger = Logger.getLogger(JwtAuthorizationFilter.class.getName());

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String token = request.getHeader(SecurityConstants.TOKEN_HEADER);
        if (token == null || !token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            SecurityContextHolder.clearContext();
        } else {
            try {
                UsernamePasswordAuthenticationToken authentication = getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                JwtTokenUtils.extensionExpirationTime(token);
            } catch (BadCredentialsException e) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            }


        }
        chain.doFilter(request, response);
    }

    /**
     * 获取用户认证信息 Authentication
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String authorization) throws BadCredentialsException {
        log.info("get authentication");
        String token = authorization.replace(SecurityConstants.TOKEN_PREFIX, "");
        this.checkToken(token);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = null;
        try {
            String username = JwtTokenUtils.getUsernameByToken(token);
            logger.info("checking username:" + username);
            if (!StringUtils.isEmpty(username)) {
                // 这里我们是又从数据库拿了一遍,避免用户的角色信息有变
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
                if (!userDetails.isEnabled()) {
                    throw new BadCredentialsException("用户已锁定");
                }
            }
        } catch (Exception exception) {
            logger.warning("Request to parse JWT with invalid signature . Detail : " + exception.getMessage());
            throw new BadCredentialsException("认证失败！");
        }
        return usernamePasswordAuthenticationToken;
    }

    private void checkToken(String token) {
        if (JwtTokenUtils.isTokenExpired(token)) {
            throw new BadCredentialsException("token已过期");
        }
    }
}
