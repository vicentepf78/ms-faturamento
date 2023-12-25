package com.ms.faturamento.domain.invoice;

import com.ms.faturamento.domain.AggregateRoot;
import com.ms.faturamento.domain.validation.ValidationHandler;

public class InvoiceItens extends AggregateRoot<InvoiceItensID> {


    protected InvoiceItens(final InvoiceItensID invoiceItensID) {
        super(invoiceItensID);
    }


    @Override
    public void validate(final ValidationHandler handler) {
        new InvoiceItensValidator(this, handler).validate();
    }
}
