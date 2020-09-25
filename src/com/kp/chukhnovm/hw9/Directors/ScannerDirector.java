package com.kp.chukhnovm.hw9.Directors;

import com.kp.chukhnovm.hw9.Builders.StudentBuilder;
import com.kp.chukhnovm.hw9.Student;

import java.util.Scanner;

public class ScannerDirector {

    private Scanner sc = new Scanner(System.in);
    private StudentBuilder builder = new StudentBuilder(sc);

    public Scanner getSc() {
        return sc;
    }

    public com.kp.chukhnovm.hw9.Student createStudent() {

        com.kp.chukhnovm.hw9.Student student = new com.kp.chukhnovm.hw9.Student();
        student.generateDocumentId();

        builder.setStudent(student);
        builder.setName();
        builder.setBirthDay();
        builder.setGender();

        return builder.getResult();
    }
}
