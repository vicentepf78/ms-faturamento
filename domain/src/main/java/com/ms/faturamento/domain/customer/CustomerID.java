package com.ms.faturamento.domain.customer;

import com.ms.faturamento.domain.Identifier;
import com.ms.faturamento.domain.utils.IdUtils;

import java.util.Objects;

public class CustomerID extends Identifier {

    private final String value;

    private CustomerID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static CustomerID from(final String anId) {
        return new CustomerID(anId.toLowerCase());
    }

    public static CustomerID unique() {
        return CustomerID.from(IdUtils.uuid());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CustomerID that = (CustomerID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
