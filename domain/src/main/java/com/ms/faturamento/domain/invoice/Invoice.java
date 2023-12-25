package com.ms.faturamento.domain.invoice;

import com.ms.faturamento.domain.AggregateRoot;
import com.ms.faturamento.domain.customer.Customer;
import com.ms.faturamento.domain.utils.InstantUtils;
import com.ms.faturamento.domain.validation.ValidationHandler;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Invoice extends AggregateRoot<InvoiceID> {

    private String invoiceNumber;
    private Operation operation;
    private Customer customer;
    private BigDecimal amount;
    private boolean canceled;
    private Instant createdAt;
    private Instant updatedAt;

    private Set<InvoiceItensID> invoiceItens;

    protected Invoice(
            final InvoiceID anId,
            final String aInvoiceNumber,
            final Operation anOperation,
            final Customer aCustomer,
            final BigDecimal anAmount,
            final boolean wasCanceled,
            final Instant aCreationDate,
            final Instant anUpdateDate,
            final Set<InvoiceItensID> invoiceItens
    ) {
        super(anId);
        this.invoiceNumber = aInvoiceNumber;
        this.operation = anOperation;
        this.customer = aCustomer;
        this.amount = anAmount;
        this.canceled = wasCanceled;
        this.createdAt = aCreationDate;
        this.updatedAt = anUpdateDate;
        this.invoiceItens = invoiceItens;
    }


    @Override
    public void validate(final ValidationHandler handler) {
        new InvoiceValidator(this, handler).validate();
    }

    public Invoice update(
            final BigDecimal anAmount,
            final boolean wasCanceled,
            final Set<InvoiceItensID> invoiceItens
    ) {
        this.amount = anAmount;
        this.canceled = wasCanceled;
        this.updatedAt = InstantUtils.now();
        this.setInvoiceItens(invoiceItens);
        return this;
    }

    public Invoice updateCanceled(final boolean wasCanceled) {
        this.canceled = wasCanceled;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public Operation getOperation() {
        return operation;
    }

    public Customer getCustomer() {
        return customer;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }


    public Set<InvoiceItensID> getInvoiceItens() {
        return invoiceItens != null ? Collections.unmodifiableSet(invoiceItens) : Collections.emptySet();
    }

    private void setInvoiceItens(final Set<InvoiceItensID> invoiceItens) {
        this.invoiceItens = invoiceItens != null ? new HashSet<>(invoiceItens) : Collections.emptySet();
    }

    public static Invoice newInvoice(
            final String aInvoiceNumber,
            final Operation anOperation,
            final Customer aCustomer,
            final BigDecimal anAmount,
            final Set<InvoiceItensID> invoiceItens
    ) {
        final var now = InstantUtils.now();
        final var anId = InvoiceID.unique();
        return new Invoice(
                anId,
                aInvoiceNumber,
                anOperation,
                aCustomer,
                anAmount,
                true,
                now,
                null,
                invoiceItens
        );
    }

    public static Invoice with(final Invoice aInvoice) {
        return new Invoice(
                aInvoice.getId(),
                aInvoice.getInvoiceNumber(),
                aInvoice.getOperation(),
                aInvoice.getCustomer(),
                aInvoice.getAmount(),
                aInvoice.isCanceled(),
                aInvoice.createdAt,
                aInvoice.updatedAt,
                aInvoice.getInvoiceItens()
        );
    }


}
