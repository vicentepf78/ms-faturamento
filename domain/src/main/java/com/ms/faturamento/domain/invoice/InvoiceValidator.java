package com.ms.faturamento.domain.invoice;

import com.ms.faturamento.domain.validation.Error;
import com.ms.faturamento.domain.validation.ValidationHandler;
import com.ms.faturamento.domain.validation.Validator;

public class InvoiceValidator extends Validator {


    private final int NUMBER_MAX_LENGTH = 9;
    private final Invoice invoice;

    protected InvoiceValidator(final Invoice aInvoice, final ValidationHandler aHandler) {
        super(aHandler);
        this.invoice = aInvoice;
    }

    @Override
    public void validate() {
        checkInvoiceNumberConstraints();
        checkOperationConstraints();
//        checkDescriptionConstraints();
//        checkLaunchedAtConstraints();
//        checkRatingConstraints();
    }

    private void checkInvoiceNumberConstraints() {
        final var number = this.invoice.getInvoiceNumber();
        if (number == null) {
            this.validationHandler().append(new Error("'Number' should not be null"));
            return;
        }

        if (number.isBlank()) {
            this.validationHandler().append(new Error("'Number' should not be empty"));
            return;
        }

        final int length = number.trim().length();
        if (length > NUMBER_MAX_LENGTH) {
            this.validationHandler().append(new Error("'Number' maximum size invoice number is up to 9 digits"));
        }
    }

    private void checkOperationConstraints() {

        final var sale = Operation.SALE.name();
        final var purchase = Operation.PURCHASE.name();

        final var operation = this.invoice.getOperation();
        if (operation == null) {
            this.validationHandler().append(new Error("'operation' should not be null"));
            return;
        }

        if (operation.name().isBlank()) {
            this.validationHandler().append(new Error("'operation' should not be empty"));
            return;
        }

        if (!Operation.SALE.name().equals(sale) || !Operation.PURCHASE.name().equals(purchase)) {
            this.validationHandler().append(new Error("'operation' not different the SALE OR PURCHASE"));
        }
    }

//    private void checkLaunchedAtConstraints() {
//        if (this.invoice.getLaunchedAt() == null) {
//            this.validationHandler().append(new Error("'launchedAt' should not be null"));
//        }
//    }
//
//    private void checkRatingConstraints() {
//        if (this.video.getRating() == null) {
//            this.validationHandler().append(new Error("'rating' should not be null"));
//        }
//    }
}
