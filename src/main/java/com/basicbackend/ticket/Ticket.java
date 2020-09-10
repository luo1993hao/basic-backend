package com.basicbackend.ticket;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ticket {
    private Integer id;
    private int spotNumber;
    private String carNumber;
    private String carparkId;
}
