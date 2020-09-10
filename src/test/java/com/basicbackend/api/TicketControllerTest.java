package com.basicbackend.api;


import com.basicbackend.infrastructure.persistence.mybatis.TicketMapper;
import com.basicbackend.ticket.Ticket;
import com.basicbackend.ticket.command.TicketCreateCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.is;

public class TicketControllerTest extends BaseApiTest {
    @Autowired
    private TicketMapper ticketMapper;

    @BeforeEach
    public void insertTicket() {
        ticketMapper.insert(Ticket.builder()
                .id(1)
                .spotNumber(2)
                .carparkId("3")
                .carNumber("4")
                .build());
    }

    @Test
    public void should_return_ticket_detail_when_select_by_id() {
        //given

        //when
        given()
                .when()
                .get("/ticket/1")
                .then()
                .log()
                .all()
                .body("data.id", is(1));
    }

    @Test
    public void should_create_ticket_success() {
        //given
        TicketCreateCommand ticketCreateCommand = new TicketCreateCommand();
        ticketCreateCommand.setCarNumber("123");
        ticketCreateCommand.setCarparkId("1234");
        ticketCreateCommand.setSpotNumber(22);
        //when
        given().body(ticketCreateCommand)
                .when()
                .put("/ticket")
                .then()
                .statusCode(200);
    }

    @Test
    public void should_return_all_ticket() {
        given()
                .when()
                .post("/ticket")
                .then()
                .log()
                .all()
                .body("data.size", is(1));
    }
}
