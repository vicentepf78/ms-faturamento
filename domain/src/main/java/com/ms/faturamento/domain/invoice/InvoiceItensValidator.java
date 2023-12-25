package com.ms.faturamento.domain.invoice;

import com.ms.faturamento.domain.validation.ValidationHandler;
import com.ms.faturamento.domain.validation.Validator;

public class InvoiceItensValidator extends Validator  {


    private final InvoiceItens invoiceItens;

    protected InvoiceItensValidator(final InvoiceItens aInvoiceItens, final ValidationHandler aHandler) {
        super(aHandler);
        this.invoiceItens = aInvoiceItens;
    }

    @Override
    public void validate() {
    }

//    private void checkInvoiceNumberConstraints() {
//        final var number = this.invoiceItens.getInvoiceNumber();
//        if (number == null) {
//            this.validationHandler().append(new Error("'Number' should not be null"));
//            return;
//        }
//
//        if (number.isBlank()) {
//            this.validationHandler().append(new Error("'Number' should not be empty"));
//            return;
//        }
//
//        final int length = number.trim().length();
//        if (length > NUMBER_MAX_LENGTH) {
//            this.validationHandler().append(new Error("'Number' maximum size invoice number is up to 9 digits"));
//        }
//    }

}
