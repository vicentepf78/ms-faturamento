package com.ms.faturamento.domain.customer;

import com.ms.faturamento.domain.ValueObject;

import java.time.Instant;

public class Endereco extends ValueObject {

    private String id;
    private String deliveryPlace;
    private String district;
    private Integer number;
    private String complement;
    private String city;
    private String state;
    private String cep;
    private String country;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deleteAt;

}
