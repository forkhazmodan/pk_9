package com.kp.chukhnovm.hw9;

import com.kp.chukhnovm.hw9.Enums.Gender;
import com.kp.chukhnovm.hw9.Exceptions.GroupDuplicateStudentException;
import com.kp.chukhnovm.hw9.Exceptions.GroupFulFilledException;
import com.kp.chukhnovm.hw9.Services.GroupService;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
//        task1();
        task2();
    }

    public static void task1() {
        Random rd = new Random();

        List<Integer> intList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            intList.add(rd.nextInt());
        }

        // Delete from ArrayList two elements from the start
        System.out.println(intList);
        for (int i = 0; i < 2; i++) {
            if(intList.size() == 0) break;
            intList.remove(0);
        }

        System.out.println(intList);

        // Delete from ArrayList two elements from the end
        for (int i = 0; i < 1; i++) {
            if(intList.size() == 0) break;
            intList.remove(intList.size() - 1);
        }

        System.out.println(intList);
    }

    public static void task2() {

        Student[] students = {
                new Student("Valentyn Test6", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.FEMALE, "1"),
                new Student("Vladislav Test7", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.MALE, "2"),
                new Student("Andrew Test1", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.MALE, "3"),
                new Student("John Test2", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.FEMALE, "4"),
                new Student("Peter Test3", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.MALE, "5"),
                new Student("Lesya Test4", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.FEMALE, "6"),
                new Student("Petro Test8", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.FEMALE, "7"),
                new Student("Tesla Test9", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.MALE, "8"),
        };

        try {
            Group group1 = new Group(students);
            Group group2 = new Group(Arrays.asList(students));

            Student student10 = new Student("Lesya Test", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.FEMALE, "10");
            Student student11 = new Student("Lesya Test", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.FEMALE, "11");
            // TODO: find why throws UnsupportedOperationException
            group1.addStudent(student10);
            group1.addStudent(student11);

            String filename1 = GroupService.serializeToFile(group1);
            String filename2 = GroupService.serializeToFile(group2);

            group1 = GroupService.unserializeFromFile(filename1);
            group2 = GroupService.unserializeFromFile(filename2);

            System.out.println(group1.equals(group2));
            System.out.println(group1.getStudentsList());



        } catch (GroupFulFilledException | IOException | ClassNotFoundException | GroupDuplicateStudentException e) {
            e.printStackTrace();
        }
    }
}
