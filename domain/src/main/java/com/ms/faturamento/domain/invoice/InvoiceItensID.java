package com.ms.faturamento.domain.invoice;

import com.ms.faturamento.domain.Identifier;
import com.ms.faturamento.domain.utils.IdUtils;

import java.util.Objects;

public class InvoiceItensID extends Identifier {

    private final String value;

    private InvoiceItensID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static InvoiceItensID from(final String anId) {
        return new InvoiceItensID(anId.toLowerCase());
    }

    public static InvoiceItensID unique() {
        return InvoiceItensID.from(IdUtils.uuid());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final InvoiceItensID that = (InvoiceItensID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

}
