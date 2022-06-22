package com.laptrinhjavaweb.dto.request;


public class CustomerDTO  {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String company;
    private String needs;
    private String note;
    private String code;
    private String noteOfCSKH;
    private String noteOfSHOW_VIEW;
    private String noteTransaction;
    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoteOfCSKH() {
        return noteOfCSKH;
    }

    public void setNoteOfCSKH(String noteOfCSKH) {
        this.noteOfCSKH = noteOfCSKH;
    }

    public String getNoteOfSHOW_VIEW() {
        return noteOfSHOW_VIEW;
    }

    public void setNoteOfSHOW_VIEW(String noteOfSHOW_VIEW) {
        this.noteOfSHOW_VIEW = noteOfSHOW_VIEW;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNoteTransaction() {
        return noteTransaction;
    }

    public void setNoteTransaction(String noteTransaction) {
        this.noteTransaction = noteTransaction;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getNeeds() {
        return needs;
    }

    public void setNeeds(String needs) {
        this.needs = needs;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
