package com.example.car.models;

import com.example.car.entities.Car;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarResponse {
    private Long id;
    private String brand;
    private String model;
    private String matricule;
    private Client client;

    public static CarResponse from(Car car, Client client) {
        return CarResponse.builder().id(car.getId()).brand(car.getBrand()).model(car.getModel())
                .matricule(car.getMatricule()).client(client).build();
    }
}
