package com.kp.chukhnovm.hw9.Students;

import com.kp.chukhnovm.hw9.Student;

import java.util.Comparator;

public class LastNameComparator implements Comparator {
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

        String[] s1NameSplited = s1.getName().split("\\s+");
        String[] s2NameSplited = s2.getName().split("\\s+");

        String s1LastName = s1NameSplited[s1NameSplited.length - 1];
        String s2LastName = s2NameSplited[s2NameSplited.length - 1];

        return s1LastName.compareTo(s2LastName);
    }
}
