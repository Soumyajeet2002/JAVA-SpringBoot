package com.example.Employee.enums;

public enum EmploymentStatus {

    ACTIVE(0),
    INACTIVE(1),
    ON_LEAVE(2),
    TERMINATED(3);

    private final int value;

    EmploymentStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static EmploymentStatus fromValue(int value) {
        for (EmploymentStatus status : EmploymentStatus.values()) {
            if (status.value == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid EmploymentStatus value: " + value);
    }
}
