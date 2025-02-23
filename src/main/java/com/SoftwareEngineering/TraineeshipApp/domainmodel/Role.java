package com.SoftwareEngineering.TraineeshipApp.domainmodel;

public enum Role {
    STUDENT("Student"),
    PROFESSOR("Professor"),
    COMPANY("Company"),
    COMMITTEE_MEMBER("Committee Member");

    private final String value;

    private Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
