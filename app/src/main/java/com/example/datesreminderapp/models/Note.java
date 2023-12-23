package com.example.datesreminderapp.models;

public class Note {
    private  int id;
    private String title;
    private String description;

    private String date;
    private String time;

    private String reminderTime;
    private String agentName;

    private String agentNumber;
    private String dependencyName;
    private boolean isUpdated;
    private String status;

    public Note(int id, String title, String description, String date, String time, String reminderTime, String agentName, String agentNumber, String dependencyName, boolean isUpdated, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.reminderTime = reminderTime;
        this.agentName = agentName;
        this.agentNumber = agentNumber;
        this.dependencyName = dependencyName;
        this.isUpdated = isUpdated;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getReminderTime() {
        return reminderTime;
    }

    public String getAgentName() {
        return agentName;
    }

    public String getAgentNumber() {
        return agentNumber;
    }

    public String getDependencyName() {
        return dependencyName;
    }

    public boolean isUpdated() {
        return isUpdated;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setReminderTime(String reminderTime) {
        this.reminderTime = reminderTime;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public void setAgentNumber(String agentNumber) {
        this.agentNumber = agentNumber;
    }

    public void setDependencyName(String dependencyName) {
        this.dependencyName = dependencyName;
    }

    public void setUpdated(boolean updated) {
        isUpdated = updated;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
