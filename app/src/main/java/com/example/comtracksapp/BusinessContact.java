package com.example.comtracksapp;

public class BusinessContact extends BaseContact{
    private String businessHours;
    private String website;
    private String description;

    public BusinessContact(int id, String type, String name, int phoneNumber, String emailAddress, String address, int photo_id, String businessHours, String website, String description) {
        super(id, type, name, phoneNumber, emailAddress, address, photo_id);
        this.businessHours = businessHours;
        this.website = website;
        this.description = description;

    }

    public BusinessContact(){super();}

    public String getBusinessHours() {
        return businessHours;
    }

    public void setBusinessHours(String businessHours) {
        this.businessHours = businessHours;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //TODO : create a method to load the business website url into the web browser
    public void openURLinBrowser() {
        System.out.println("We are now loading... " + this.getWebsite());
    }

    @Override
    public int compareTo(BaseContact o) {
        // TODO Auto-generated method stub
        return 0;
    }

    public String toString() {
        return this.getId() +","+ this.getType() +","+this.getName() +","+ this.getPhoneNumber() +","+ this.getEmailAddress() +","+  this.getAddress() +","+ this.getPhoto_id() + "," + this.businessHours +","+ this.website +","+ this.description;

    }

}
