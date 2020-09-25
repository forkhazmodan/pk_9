package com.kp.chukhnovm.hw9.Builders;

import com.kp.chukhnovm.hw9.Enums.Gender;
import com.kp.chukhnovm.hw9.Student;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class StudentBuilder{

    private Student student = new Student();
    private final Scanner scanner;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public StudentBuilder(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setName() {
        this.setName("Enter students name:");
    }

    public void setName(String message) {
        System.out.println(message);
        String name = scanner.nextLine();
        student.setName(name);
    }

    public void setBirthDay() {
        this.setBirthDay("Enter students birth-date:");
    }

    public void setBirthDay(String message) {
        String pattern = "yyyy-mm-dd";
        System.out.println(message);
        String dateString = scanner.nextLine();

        Date date = new Date();

        try{

            DateFormat df = new SimpleDateFormat(pattern);
            date = df.parse(dateString);

        } catch (ParseException exception) {
            message = String.format("Please try again date format should be: %s. ", pattern);

            this.setBirthDay(message);
        }

        student.setBirthDay(date);
    }

    public void setGender() {
        this.setGender("Enter students gender:");
    }

    public void setGender(String message) {
        System.out.println(message);

        String gender = scanner.nextLine();

        if(!gender.isEmpty()) {

            try {
                student.setGender(Gender.valueOf(gender.toUpperCase()));
            } catch (IllegalArgumentException e) {
                this.setGender(String.format("Invalid gender. `%s` or `male` %s",
                                Gender.FEMALE.getDescription(),
                                Gender.MALE.getDescription()
                        )
                );
            }
        }
    }

    public Student getResult(){
        return this.student;
    }

}
