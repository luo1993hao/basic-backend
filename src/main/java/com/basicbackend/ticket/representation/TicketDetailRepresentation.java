package com.basicbackend.ticket.representation;

import com.basicbackend.ticket.Ticket;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketDetailRepresentation {
    private Integer id;
    private int spotNumber;
    private String carNumber;
    private String carparkId;

    public static TicketDetailRepresentation from(Ticket ticket) {
        return TicketDetailRepresentation.builder()
                .id(ticket.getId())
                .spotNumber(ticket.getSpotNumber())
                .carNumber(ticket.getCarNumber())
                .carparkId(ticket.getCarparkId())
                .build();
    }
}
