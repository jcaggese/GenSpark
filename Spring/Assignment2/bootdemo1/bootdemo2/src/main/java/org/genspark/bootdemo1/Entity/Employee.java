package org.genspark.bootdemo1.Entity;

public class Employee {

    private int id;
    private String jobTitle;
    private String name;
    private String securityAccess;

    public Employee() {
    }

    public Employee(int id, String jobTitle, String name, String securityAccess) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.name = name;
        this.securityAccess = securityAccess;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecurityAccess() {
        return securityAccess;
    }

    public void setSecurityAccess(String securityAccess) {
        this.securityAccess = securityAccess;
    }
}
