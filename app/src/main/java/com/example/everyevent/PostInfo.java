package com.example.everyevent;

public class PostInfo {
    private String userInfo;
    private String title;
    private String contents;
    private String address;
    private String startDate;
    private int numberOfApplicants = 0;
    private int numberOfAttendees = 0;
    private String numberOfPeopleCanApply;
    private boolean running = false;


    public PostInfo(String userInfo, String title, String contents, String address, String startDate, String numberOfPeopleCanApply) {
        this.userInfo = userInfo;
        this.title = title;
        this.contents = contents;
        this.address = address;
        this.startDate = startDate;
        this.numberOfPeopleCanApply = numberOfPeopleCanApply;
    }

    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAddress(){
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getStartDate(){
        return this.startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public int getNumberOfApplicants() { return this.numberOfApplicants; }
    public void setNumberOfApplicants(int numberOfApplicants) {this.numberOfApplicants = numberOfApplicants;}
    public String getNumberOfPeopleCanApply() { return this.numberOfPeopleCanApply; }
    public void setNumberOfPeopleCanApply(String numberOfPeopleCanApply) {this.numberOfPeopleCanApply = numberOfPeopleCanApply; }
    public int getNumberOfAttendees() { return this.numberOfAttendees; }
    public void setNumberOfAttendees(int numberOfAttendees) { this.numberOfAttendees = numberOfAttendees; }
    public boolean getRunning() { return this.running;}
    public void setRunning (boolean running) { this.running = running;}
    public String getContents() { return this.contents; }
    public void setContents(String contents) { this.contents = contents; }
    public String getUserInfo() { return numberOfPeopleCanApply; }
    public void setUserInfo(String numberOfPeopleCanApply) { this.numberOfPeopleCanApply = numberOfPeopleCanApply; }









}
