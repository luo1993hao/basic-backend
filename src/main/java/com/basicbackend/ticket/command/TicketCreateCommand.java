package com.basicbackend.ticket.command;

import lombok.Data;

@Data
public class TicketCreateCommand {
    private String carNumber;
    private int spotNumber;
    private String carparkId;

}
