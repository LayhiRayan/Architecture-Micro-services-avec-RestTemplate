package com.example.car.services;

import com.example.car.entities.Car;
import com.example.car.models.CarResponse;
import com.example.car.models.Client;
import com.example.car.repositories.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class CarService {
    private static final String CLIENT_SERVICE_URL = "http://localhost:8081/api/client/";
    private final CarRepository repository;
    private final RestTemplate restTemplate;

    public CarService(CarRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public List<CarResponse> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public CarResponse findById(Long id) {
        Car car = repository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
        return toResponse(car);
    }

    public Car add(Car car) {
        return repository.save(car);
    }

    private CarResponse toResponse(Car car) {
        Client client = restTemplate.getForObject(CLIENT_SERVICE_URL + car.getClient_id(), Client.class);
        return CarResponse.from(car, client);
    }

    public static class CarNotFoundException extends RuntimeException {
        public CarNotFoundException(Long id) { super("Voiture non trouvée avec l'ID: " + id); }
    }
}
