package co.edu.poli.ces3.customers.entities;

public class Customer {
    public String COMPANY_PUBLIC_KEY = "abc";
    protected String document;
    private String name;
    private String lastName;
    private String cellPhone;
    private String email;
    private String municipality;
    private String address;
    private String contactName;
    private String cellPhoneContact;

    public Customer(String document, String name, String lastName, String cellPhone, String email, String municipality, String address, String contactName, String cellPhoneContact) {
        this.document = document;
        this.name = name;
        this.lastName = lastName;
        this.cellPhone = cellPhone;
        this.email = email;
        this.municipality = municipality;
        this.address = address;
        this.contactName = contactName;
        this.cellPhoneContact = cellPhoneContact;
    }

    public String getCOMPANY_PUBLIC_KEY() {
        return COMPANY_PUBLIC_KEY;
    }

    public void setCOMPANY_PUBLIC_KEY(String COMPANY_PUBLIC_KEY) {
        this.COMPANY_PUBLIC_KEY = COMPANY_PUBLIC_KEY;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getCellPhoneContact() {
        return cellPhoneContact;
    }

    public void setCellPhoneContact(String cellPhoneContact) {
        this.cellPhoneContact = cellPhoneContact;
    }

    @Override
    public String toString() {
        return this.name + " " + this.lastName;
    }
}
