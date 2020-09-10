package com.basicbackend.ticket;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.basicbackend.infrastructure.persistence.mybatis.TicketMapper;
import com.basicbackend.ticket.command.TicketCreateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TicketApplicationService {
    private final TicketMapper ticketMapper;
    private final TicketSpecification ticketSpecification;
    private final TicketFactory ticketFactory;

    @Transactional(readOnly = true)
    public List<Ticket> findAll() {
        return ticketMapper.selectList(new QueryWrapper<>());
    }

    @Transactional(readOnly = true)
    public Ticket getDetail(String id) {
        return ticketMapper.selectById(id);
    }

    @Transactional
    public void create(TicketCreateCommand ticketCreateCommand) {
        ticketSpecification.createSpec(ticketCreateCommand);
        Ticket ticket = ticketFactory.create(ticketCreateCommand);
        ticketMapper.insert(ticket);
    }
}
