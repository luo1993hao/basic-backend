package com.basicbackend.infrastructure.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.broadcastingmanage.infrastructure.persistence.mybatis.UserMapper;
import com.broadcastingmanage.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String name) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("name", name);
        User user = userMapper.selectOne(userQueryWrapper);
        return new JwtUser(user);
    }

}
