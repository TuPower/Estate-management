package com.laptrinhjavaweb.dto.request;

public class TransactionDTOResquest {
    private Long customer_id;
    private String noteOfTrans;
    private String codeOfTrans;

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public String getNoteOfTrans() {
        return noteOfTrans;
    }

    public void setNoteOfTrans(String noteOfTrans) {
        this.noteOfTrans = noteOfTrans;
    }

    public String getCodeOfTrans() {
        return codeOfTrans;
    }

    public void setCodeOfTrans(String codeOfTrans) {
        this.codeOfTrans = codeOfTrans;
    }
}
