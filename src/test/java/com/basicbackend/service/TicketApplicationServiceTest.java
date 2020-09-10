package com.basicbackend.service;


import com.basicbackend.ticket.Ticket;
import com.basicbackend.ticket.TicketApplicationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TicketApplicationServiceTest {
    @Autowired
    private TicketApplicationService ticketApplicationService;

    @Test
    public void should_return_all_ticket_when_select_all() {
        //given
        //when
        List<Ticket> tickets = ticketApplicationService.findAll();
        //then
        Assertions.assertEquals(2, tickets.size());
    }
}
