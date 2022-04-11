package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.demo.generic.GenericEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "patients")
public class Patient implements GenericEntity<Patient, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String fullName;

    @Column(name = "birth_date")
    private Date birthDate;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<VitalSign> vitalSigns;

    public Patient() {
    }

    public Patient(String fullName, Date birthDate, List<VitalSign> vitalSigns) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.vitalSigns = vitalSigns;
    }

    public void addSignoVital(VitalSign vitalSign){
        if( this.vitalSigns == null){
            this.vitalSigns = new ArrayList<>();
        }
        this.vitalSigns.add(vitalSign);
        vitalSign.setPatient(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<VitalSign> getVitalSigns() {
        return vitalSigns;
    }

    public void setVitalSigns(List<VitalSign> vitalSigns) {
        this.vitalSigns = vitalSigns;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", birthDate=" + birthDate +
                ", vitalSigns=" + vitalSigns +
                '}';
    }
}