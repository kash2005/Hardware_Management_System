package lk.ijse.project.dto;

import lombok.*;



public class EmployeeDTO {
    private String eId;
    private String eName;
    private String eAddress;
    private String eJob;
    private Double eSalary;
    private String eContactNo;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String eId, String eName, String eAddress, String eJob, Double eSalary, String eContactNo) {
        this.eId = eId;
        this.eName = eName;
        this.eAddress = eAddress;
        this.eJob = eJob;
        this.eSalary = eSalary;
        this.eContactNo = eContactNo;
    }

    public String getEId() {
        return eId;
    }

    public void setEId(String eId) {
        this.eId = eId;
    }

    public String getEName() {
        return eName;
    }

    public void setEName(String eName) {
        this.eName = eName;
    }

    public String getEAddress() {
        return eAddress;
    }

    public void setEAddress(String eAddress) {
        this.eAddress = eAddress;
    }

    public String getEJob() {
        return eJob;
    }

    public void setEJob(String eJob) {
        this.eJob = eJob;
    }

    public Double getESalary() {
        return eSalary;
    }

    public void setESalary(Double eSalary) {
        this.eSalary = eSalary;
    }

    public String getEContactNo() {
        return eContactNo;
    }

    public void setEContactNo(String eContactNo) {
        this.eContactNo = eContactNo;
    }
}
