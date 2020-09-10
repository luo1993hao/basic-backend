package com.basicbackend.ticket;

import com.basicbackend.ticket.command.TicketCreateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class TicketFactory {
    public Ticket create(TicketCreateCommand ticketCreateCommand) {
        Random random = new Random();
        return Ticket.builder()
                .id(random.nextInt())
                .carNumber(ticketCreateCommand.getCarNumber())
                .spotNumber(ticketCreateCommand.getSpotNumber())
                .carparkId(ticketCreateCommand.getCarparkId())
                .build();
    }
}
