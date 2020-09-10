package com.basicbackend.ticket.representation;

import com.basicbackend.ticket.Ticket;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TicketAllRepresentation {
    private int size;

    public static TicketAllRepresentation from(List<Ticket> ticketList) {
        return TicketAllRepresentation.builder().size(ticketList.size()).build();
    }
}
