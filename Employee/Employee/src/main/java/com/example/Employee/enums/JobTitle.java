package com.example.Employee.enums;

public enum JobTitle {

    SOFTWARE_ENGINEER(0),
    SENIOR_SOFTWARE_ENGINEER(1),
    TECH_LEAD(2),
    TEAM_LEAD(3),
    PROJECT_MANAGER(4),
    HR_EXECUTIVE(5),
    HR_MANAGER(6),
    ACCOUNTANT(7),
    FINANCE_MANAGER(8),
    SALES_EXECUTIVE(9),
    SALES_MANAGER(10),
    MARKETING_EXECUTIVE(11),
    BUSINESS_ANALYST(12),
    QA_ENGINEER(13),
    DEVOPS_ENGINEER(14),
    SYSTEM_ADMINISTRATOR(15),
    UI_UX_DESIGNER(16),
    PRODUCT_MANAGER(17),
    INTERN(18);

    private final int value;

    JobTitle(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static JobTitle fromValue(int value) {
        for (JobTitle title : JobTitle.values()) {
            if (title.value == value) {
                return title;
            }
        }
        throw new IllegalArgumentException("Invalid JobTitle value: " + value);
    }
}