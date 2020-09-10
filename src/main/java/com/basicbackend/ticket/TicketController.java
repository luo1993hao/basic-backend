package com.basicbackend.ticket;

import com.basicbackend.infrastructure.HttpResult.HttpResult;
import com.basicbackend.ticket.command.TicketCreateCommand;
import com.basicbackend.ticket.representation.TicketAllRepresentation;
import com.basicbackend.ticket.representation.TicketDetailRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ticket")
@RequiredArgsConstructor
public class TicketController {
    private final TicketApplicationService ticketApplicationService;

    @GetMapping("/{id}")
    public HttpResult<TicketDetailRepresentation> getDetail(@PathVariable String id) {
        return HttpResult.success(TicketDetailRepresentation.from(ticketApplicationService.getDetail(id)));
    }

    @PutMapping
    public HttpResult<Void> create(@Validated @RequestBody TicketCreateCommand ticketCreateCommand) {
        ticketApplicationService.create(ticketCreateCommand);
        return HttpResult.success();
    }

    @PostMapping
    public HttpResult<TicketAllRepresentation> findAll() {
        return HttpResult.success(TicketAllRepresentation.from(ticketApplicationService.findAll()));
    }

    @GetMapping("test")
    public HttpResult<String> test() {
        return HttpResult.success("111112");
    }

}
