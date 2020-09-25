package com.kp.chukhnovm.hw9;

import com.kp.chukhnovm.hw9.Enums.Gender;
import com.kp.chukhnovm.hw9.Interfaces.CsvCompatible;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Student extends com.kp.chukhnovm.hw9.Human implements CsvCompatible, Serializable {

    private static final long serialVersionUID = 1L;
    private String documentId;

    /*
    |--------------------------------------------------------------------------
    | CONSTRUCTORS
    |--------------------------------------------------------------------------
    */

    public Student() {
    }

    public Student(String name, Date birthDay, Gender gender) {
        super(name, birthDay, gender);
        this.generateDocumentId();
    }

    public Student(String name, Date birthDay, Gender gender, String documentId) {
        super(name, birthDay, gender);
        this.setDocumentId(documentId);
    }

    /*
    |--------------------------------------------------------------------------
    | GETTERS & SETTERS
    |--------------------------------------------------------------------------
    */

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId.trim();
    }

    /*
    |--------------------------------------------------------------------------
    | METHODS
    |--------------------------------------------------------------------------
    */

    public void generateDocumentId() {
        this.setDocumentId(UUID.randomUUID().toString());
    }

    public String toCSVString() {
        return String.format("\"%s\",\"%s\",\"%s\",\"%s\"",
                this.getName(),
                this.getBirthday("yyyy-mm-dd"),
                gender.getDescription(),
                this.getDocumentId()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.kp.chukhnovm.hw9.Student student = (com.kp.chukhnovm.hw9.Student) o;
        return Objects.equals(documentId, student.documentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentId);
    }

    @Override
    public String toString() {
        return "Student{" +
                "documentId='" + documentId + '\'' +
                ", name='" + name + '\'' +
                ", birthDay=" + birthDay +
                ", gender=" + gender +
                '}';
    }
}
