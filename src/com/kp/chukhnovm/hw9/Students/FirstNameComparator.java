package com.kp.chukhnovm.hw9.Students;

import com.kp.chukhnovm.hw9.Student;

import java.util.Comparator;

public class FirstNameComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {

        if (o1 == null && o2 == null) {
            return 0;
        } else if (o1 == null) {
            return 1;
        } else if (o2 == null) {
            return -1;
        }

        Student s1 = (Student) o1;
        Student s2 = (Student) o2;

        return s1.getName().compareTo(s2.getName());
    }

}
