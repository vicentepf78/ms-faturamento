package com.ms.faturamento.domain.customer;

public class Document {

    private DocumentType documentType;
    private String documentNumber;

    protected Document(final DocumentType documentType, final String documentNumber) {
        this.documentType = documentType;
        this.documentNumber = documentNumber;
    }

    public static Document with(final DocumentType documentType, final String documentNumber) {
        return new Document(documentType, documentNumber);
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }
}
