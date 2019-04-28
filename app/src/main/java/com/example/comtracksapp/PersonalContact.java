package com.example.comtracksapp;

public class PersonalContact extends BaseContact{
    private String dateOfBirth;
    private String relation;
    private String nickname;

    public PersonalContact(int id, String type, String name, int phoneNumber, String emailAddress, String address, int photo_id, String dateOfBirth, String relation, String nickname) {
        super(id, type, name, phoneNumber, emailAddress, address, photo_id);
        this.dateOfBirth = dateOfBirth;
        this.relation = relation;
        this.nickname = nickname;
    }

    public PersonalContact() {super();}

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public int compareTo(BaseContact o) {
        // TODO Auto-generated method stub
        return 0;
    }

    public String toString() {
        return this.getId() +","+ this.getType() +","+this.getName() +","+ this.getPhoneNumber() +","+ this.getEmailAddress() +","+  this.getAddress() +","+ this.getPhoto_id() + "," + this.dateOfBirth +","+ this.nickname +","+ this.relation;

    }
}
