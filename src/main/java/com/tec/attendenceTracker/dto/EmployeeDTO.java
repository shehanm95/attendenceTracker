package com.tec.attendenceTracker.dto;


import org.springframework.web.multipart.MultipartFile;


public class EmployeeDTO {

    private Long id;

    public EmployeeDTO(Long id, String firstName, String lastName, String personalPhone, String personalEmail, String emergencyContactName, String emergencyContactPhone, MultipartFile profilePicture, AddressDTO permanentAddress, AddressDTO currentAddress, String profilePicturePath, String officialEmail, String officialPhone, String designation, String department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalPhone = personalPhone;
        this.personalEmail = personalEmail;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactPhone = emergencyContactPhone;
        this.profilePicture = profilePicture;
        this.permanentAddress = permanentAddress;
        this.currentAddress = currentAddress;
        this.profilePicturePath = profilePicturePath;
        this.officialEmail = officialEmail;
        this.officialPhone = officialPhone;
        this.designation = designation;
        this.department = department;
    }


    public EmployeeDTO() {

    }
    // Personal Information
    private String firstName;
    private String lastName;
    private String personalPhone;
    private String personalEmail;
    private String emergencyContactName;
    private String emergencyContactPhone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonalPhone() {
        return personalPhone;
    }

    public void setPersonalPhone(String personalPhone) {
        this.personalPhone = personalPhone;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactPhone() {
        return emergencyContactPhone;
    }

    public void setEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
    }

    public MultipartFile getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(MultipartFile profilePicture) {
        this.profilePicture = profilePicture;
    }

    public AddressDTO getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(AddressDTO permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public AddressDTO getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(AddressDTO currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }

    public String getOfficialEmail() {
        return officialEmail;
    }

    public void setOfficialEmail(String officialEmail) {
        this.officialEmail = officialEmail;
    }

    public String getOfficialPhone() {
        return officialPhone;
    }

    public void setOfficialPhone(String officialPhone) {
        this.officialPhone = officialPhone;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    private MultipartFile profilePicture;

    private AddressDTO permanentAddress;
    private AddressDTO currentAddress;

    private String profilePicturePath; // file name only

    // Official (view-only)
    private String officialEmail;
    private String officialPhone;
    private String designation;
    private String department;

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", personalPhone='" + personalPhone + '\'' +
                ", personalEmail='" + personalEmail + '\'' +
                ", emergencyContactName='" + emergencyContactName + '\'' +
                ", emergencyContactPhone='" + emergencyContactPhone + '\'' +
                ", profilePicture=" + profilePicture +
                ", permanentAddress=" + permanentAddress +
                ", currentAddress=" + currentAddress +
                ", profilePicturePath='" + profilePicturePath + '\'' +
                ", officialEmail='" + officialEmail + '\'' +
                ", officialPhone='" + officialPhone + '\'' +
                ", designation='" + designation + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
