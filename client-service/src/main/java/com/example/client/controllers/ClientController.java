package com.example.client.controllers;

import com.example.client.entities.Client;
import com.example.client.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    private final ClientService service;
    public ClientController(ClientService service) { this.service = service; }
    @GetMapping public List<Client> findAll() { return service.findAll(); }
    @GetMapping("/{id}") public Client findById(@PathVariable Long id) { return service.findById(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Client save(@RequestBody Client client) { return service.addClient(client); }
    @ExceptionHandler(ClientService.ClientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String clientNotFound(ClientService.ClientNotFoundException exception) { return exception.getMessage(); }
}
