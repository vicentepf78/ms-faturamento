package com.ms.faturamento.domain;

import com.ms.faturamento.domain.customer.Customer;
import com.ms.faturamento.domain.customer.Document;
import com.ms.faturamento.domain.customer.DocumentType;
import com.ms.faturamento.domain.exceptions.DomainException;
import com.ms.faturamento.domain.invoice.Invoice;
import com.ms.faturamento.domain.invoice.InvoiceItensID;
import com.ms.faturamento.domain.invoice.Operation;
import com.ms.faturamento.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

public class InvoiceTest extends UnitTest {

    @Test
    public void givenAValidParams_whenCallNewInvoice_thenInstantiateAInvoice() {
        final var expectedInvoiceNumber = "1";
        final var expectedOperation = Operation.SALE;
        final var document = Document.with(DocumentType.CPF, "02543031960");
        final var expectedCustomer = Customer.newCustomer("Vicente Filho", document);
        final var expectedAmount = BigDecimal.TEN;
        final var expectInvoiceItens = Set.of(InvoiceItensID.unique());

        final var actualInvoice =
                Invoice.newInvoice(
                        expectedInvoiceNumber,
                        expectedOperation,
                        expectedCustomer,
                        expectedAmount,
                        expectInvoiceItens
                );

        Assertions.assertNotNull(actualInvoice);
        Assertions.assertNotNull(actualInvoice.getId());
        Assertions.assertEquals(expectedInvoiceNumber, actualInvoice.getInvoiceNumber());
        Assertions.assertEquals(expectedOperation, actualInvoice.getOperation());
        Assertions.assertEquals(expectedCustomer, actualInvoice.getCustomer());
        Assertions.assertEquals(expectedAmount, actualInvoice.getAmount());
        Assertions.assertNotNull(actualInvoice.getInvoiceItens());
        Assertions.assertNotNull(actualInvoice.getCreatedAt());
        Assertions.assertNull(actualInvoice.getUpdatedAt());

        Assertions.assertDoesNotThrow(() -> actualInvoice.validate(new ThrowsValidationHandler()));
    }

    @Test
    public void givenAnInvalidNullInvoiceNumber_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final String expectedInvoiceNumber = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'Number' should not be null";
        final var expectedOperation = Operation.SALE;
        final var document = Document.with(DocumentType.CPF, "02543031960");
        final var expectedCustomer = Customer.newCustomer("Vicente Filho", document);
        final var expectedAmount = BigDecimal.TEN;
        final Set<InvoiceItensID> expectInvoiceItens = Set.of();


        final var actualInvoice =
                Invoice.newInvoice(
                        expectedInvoiceNumber,
                        expectedOperation,
                        expectedCustomer,
                        expectedAmount,
                        expectInvoiceItens
                );

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualInvoice.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidEmptyInvoiceNumber_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final String expectedInvoiceNumber = " ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'Number' should not be empty";
        final var expectedOperation = Operation.SALE;
        final var document = Document.with(DocumentType.CPF, "02543031960");
        final var expectedCustomer = Customer.newCustomer("Vicente Filho", document);
        final var expectedAmount = BigDecimal.TEN;
        final Set<InvoiceItensID> expectInvoiceItens = Set.of();


        final var actualInvoice =
                Invoice.newInvoice(
                        expectedInvoiceNumber,
                        expectedOperation,
                        expectedCustomer,
                        expectedAmount,
                        expectInvoiceItens
                );

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualInvoice.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNameLengthLessThan9_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final String expectedInvoiceNumber = "1234567890";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'Number' maximum size invoice number is up to 9 digits";
        final var expectedOperation = Operation.SALE;
        final var document = Document.with(DocumentType.CPF, "02543031960");
        final var expectedCustomer = Customer.newCustomer("Vicente Filho", document);
        final var expectedAmount = BigDecimal.TEN;
        final Set<InvoiceItensID> expectInvoiceItens = Set.of();


        final var actualInvoice =
                Invoice.newInvoice(
                        expectedInvoiceNumber,
                        expectedOperation,
                        expectedCustomer,
                        expectedAmount,
                        expectInvoiceItens
                );

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualInvoice.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNullOperation_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final var expectedInvoiceNumber = "1";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'operation' should not be null";
        final Operation expectedOperation = null;
        final var document = Document.with(DocumentType.CPF, "02543031960");
        final var expectedCustomer = Customer.newCustomer("Vicente Filho", document);
        final var expectedAmount = BigDecimal.TEN;
        final Set<InvoiceItensID> expectInvoiceItens = Set.of();


        final var actualInvoice =
                Invoice.newInvoice(
                        expectedInvoiceNumber,
                        expectedOperation,
                        expectedCustomer,
                        expectedAmount,
                        expectInvoiceItens
                );

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualInvoice.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidEmptyOperation_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final var expectedInvoiceNumber = "1";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'operation' should not be empty";
        final var expectedOperation = Operation.valueOf(" ");
        final var document = Document.with(DocumentType.CPF, "02543031960");
        final var expectedCustomer = Customer.newCustomer("Vicente Filho", document);
        final var expectedAmount = BigDecimal.TEN;
        final Set<InvoiceItensID> expectInvoiceItens = Set.of();


        final var actualInvoice =
                Invoice.newInvoice(
                        expectedInvoiceNumber,
                        expectedOperation,
                        expectedCustomer,
                        expectedAmount,
                        expectInvoiceItens
                );

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualInvoice.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }


}
