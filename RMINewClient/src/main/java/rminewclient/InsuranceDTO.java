package rminewclient;

import java.io.Serializable;

public class InsuranceDTO implements Serializable {
    private String name;
    private String insurancePolicyNumber;

    public InsuranceDTO(String name, String insurancePolicyNumber) {
        this.name = name;
        this.insurancePolicyNumber = insurancePolicyNumber;
    }

    public String getName() {
        return name;
    }

    public String getinsurancePolicyNumber() {
        return insurancePolicyNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setinsurancePolicyNumber(String insurancePolicyNumber) {
        this.insurancePolicyNumber = insurancePolicyNumber;
    }
}