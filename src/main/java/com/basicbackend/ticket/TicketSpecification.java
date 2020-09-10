package com.basicbackend.ticket;

import com.basicbackend.ticket.command.TicketCreateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketSpecification {
    public void createSpec(TicketCreateCommand createCommand) {

    }
}
