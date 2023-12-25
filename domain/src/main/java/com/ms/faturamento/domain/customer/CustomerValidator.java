package com.ms.faturamento.domain.customer;

import com.ms.faturamento.domain.validation.ValidationHandler;
import com.ms.faturamento.domain.validation.Validator;

public class CustomerValidator extends Validator {

    private final Customer customer;

    protected CustomerValidator(final Customer aCustomer, final ValidationHandler aHandler) {
        super(aHandler);
        this.customer = aCustomer;
    }

    @Override
    public void validate() {

    }
}
