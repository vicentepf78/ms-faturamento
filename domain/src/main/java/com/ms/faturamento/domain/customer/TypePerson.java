package com.ms.faturamento.domain.customer;

public enum TypePerson {
    PF(1,"Pessoa Física"),
    PJ(2,"Pessoa Jurídica");

    private Integer id;
    private String description;


    TypePerson(final Integer anId, final String aDescription) {
        this.id = anId;
        this.description = aDescription;
    }
}
