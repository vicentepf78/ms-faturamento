package com.ms.faturamento.domain.customer;

import com.ms.faturamento.domain.AggregateRoot;
import com.ms.faturamento.domain.utils.InstantUtils;
import com.ms.faturamento.domain.validation.ValidationHandler;

import java.sql.Date;
import java.time.Instant;

public class Customer extends AggregateRoot<CustomerID> {

    private String name;
    private Date birthDate;
    private Date foundationDate;
    private Document document;
    private TypePerson typePerson;
    private Endereco endereco;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deleteAt;

    protected Customer(final CustomerID customerID,
                       final String aName,
                       final Document aDocument,
                       final Instant aCreatedAt) {
        super(customerID);
        this.name = aName;
        this.document = aDocument;
        if(aDocument != null) {
            if(aDocument.getDocumentType().compareTo(DocumentType.CPF) == 0) {
                this.typePerson = TypePerson.valueOf("PF");
            }else {
                this.typePerson = TypePerson.valueOf("PJ");
            }
        }
        this.createdAt = aCreatedAt;
        this.updatedAt = null;
    }

    protected Customer(
            final CustomerID anId,
            final String aName,
            final Date aBrithDate,
            final Date aFoundationDate,
            final Document aDocument,
            final TypePerson aTypePerson,
            final Endereco aEndereco,
            final Instant aCreationDate,
            final Instant anUpdateDate,
            final Instant aDeleteDate
    ) {
        super(anId);
        this.name = aName;
        this.birthDate = aBrithDate;
        this.foundationDate = aFoundationDate;
        this.document = aDocument;
        this.typePerson = aTypePerson;
        this.endereco = aEndereco;
        this.createdAt = aCreationDate;
        this.updatedAt = anUpdateDate;
        this.deleteAt = aDeleteDate;
    }


    @Override
    public void validate(ValidationHandler handler) {
        new CustomerValidator(this, handler).validate();
    }


    public static Customer newCustomer(final String aName, final Document aDocument) {
        final var anId = CustomerID.unique();
        final var now = InstantUtils.now();

        return new Customer(anId, aName, aDocument, now);
    }
    public static Customer newCustomer(final String aName,
                                       final Date aBrithDate,
                                       final Date aFoundationDate,
                                       final Document aDocument,
                                       final TypePerson aTypePerson,
                                       final Endereco aEndereco
    ) {
        final var now = InstantUtils.now();
        final var anId = CustomerID.unique();

        return new Customer(
                anId,
                aName,
                aBrithDate,
                aFoundationDate,
                aDocument,
                aTypePerson,
                aEndereco,
                now,
                null,
                null
        );
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Date getFoundationDate() {
        return foundationDate;
    }

    public Document getDocument() {
        return document;
    }

    public TypePerson getTypePerson() {
        return typePerson;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeleteAt() {
        return deleteAt;
    }
}
