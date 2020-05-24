package com.example.ambcab;

public class patientUserHelperClass
{
        String name,email,DOB,contactNO,bloodGroup,weight,age,emergencyNO;//addMedDets,


    public patientUserHelperClass(String name, String email,String DOB, String contactNO, String bloodGroup, String weight, String age, String emergencyNO){   // String addMedDets, String DOB) {
        this.name = name;
        this.email = email;
        this.contactNO = contactNO;
        this.DOB = DOB;
        this.bloodGroup = bloodGroup;
        this.weight = weight;
        this.age = age;
        this.emergencyNO = emergencyNO;
        //this.addMedDets = addMedDets;
    }

    public patientUserHelperClass() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNO() {
        return contactNO;
    }

    public void setContactNO(String contactNO) {
        this.contactNO = contactNO;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmergencyNO() {
        return emergencyNO;
    }

    public void setEmergencyNO(String emergencyNO) {
        this.emergencyNO = emergencyNO;
    }

   /* public String getAddMedDets() {
        return addMedDets;
    }

    public void setAddMedDets(String addMedDets) {
        this.addMedDets = addMedDets;
    }*/
}
