package com.example.client.services;

import com.example.client.entities.Client;
import com.example.client.repositories.ClientRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientService {
    private final ClientRepository repository;
    public ClientService(ClientRepository repository) { this.repository = repository; }
    public List<Client> findAll() { return repository.findAll(); }
    public Client findById(Long id) { return repository.findById(id).orElseThrow(() -> new ClientNotFoundException(id)); }
    public Client addClient(Client client) { return repository.save(client); }
    public static class ClientNotFoundException extends RuntimeException {
        public ClientNotFoundException(Long id) { super("Client non trouvé avec l'ID: " + id); }
    }
}
