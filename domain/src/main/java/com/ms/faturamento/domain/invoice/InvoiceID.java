package com.ms.faturamento.domain.invoice;

import com.ms.faturamento.domain.Identifier;
import com.ms.faturamento.domain.utils.IdUtils;

import java.util.Objects;

public class InvoiceID extends Identifier {
    private final String value;

    private InvoiceID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static InvoiceID from(final String anId) {
        return new InvoiceID(anId.toLowerCase());
    }

    public static InvoiceID unique() {
        return InvoiceID.from(IdUtils.uuid());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final InvoiceID that = (InvoiceID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
