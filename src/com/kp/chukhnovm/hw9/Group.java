package com.kp.chukhnovm.hw9;

import com.kp.chukhnovm.hw9.Enums.Gender;
import com.kp.chukhnovm.hw9.Enums.SortOrder;
import com.kp.chukhnovm.hw9.Exceptions.GroupDuplicateStudentException;
import com.kp.chukhnovm.hw9.Exceptions.GroupFulFilledException;
import com.kp.chukhnovm.hw9.Interfaces.CsvCompatible;
import com.kp.chukhnovm.hw9.Interfaces.IsMilita;
import com.kp.chukhnovm.hw9.Interfaces.Voenkom;
import com.kp.chukhnovm.hw9.Students.FirstNameComparator;
import com.kp.chukhnovm.hw9.Students.LastNameComparator;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Group implements Voenkom, CsvCompatible, Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    private final int maxLength = 10;

    private List<Student> students = new ArrayList<>(maxLength);

    /*
    |--------------------------------------------------------------------------
    | CONSTRUCTORS
    |--------------------------------------------------------------------------
    */

    public Group() {
    }

    public Group(Student[] students) throws GroupFulFilledException {
        this.setStudents(students);
    }

    public Group(List<Student> students) throws GroupFulFilledException {
        this.setStudents(students);
    }

    /*
    |--------------------------------------------------------------------------
    | GETTERS & SETTERS
    |--------------------------------------------------------------------------
    */

    public void setStudents(List<Student> students) throws GroupFulFilledException {

        if (students.size() > maxLength) {
            throw new GroupFulFilledException(String.format(
                    "You try create group with %s students. Group cannot carry more than %s students.",
                    students.size(),
                    maxLength
            ));
        }

        this.students = students;
        while (this.students.remove(null));
    }

    public void setStudents(Student[] students) throws GroupFulFilledException {
        this.setStudents(new ArrayList<>(Arrays.asList(students)));
    }

    public List<Student> getStudentsList() {

        List<Student> listStudents = this.students;
        Comparator<Student> c = new FirstNameComparator();
        listStudents.sort(c);

        return listStudents;
    }


    public Student[] getStudents() {
        List<Student> studentsList = this.getStudentsList();
        Student[] students = new Student[studentsList.size()];
        return studentsList.toArray(students);
    }

    /*
    |--------------------------------------------------------------------------
    | METHODS
    |--------------------------------------------------------------------------
    */

    public void addStudent(Student student) throws GroupFulFilledException, GroupDuplicateStudentException {

        if(this.students.contains(student)) {
            throw new GroupDuplicateStudentException("Such student already exists");
        } else if(this.students.size() >= this.maxLength) {
            throw new GroupFulFilledException("The group is full.");
        }

        if(student != null) {
            this.students.add(student);
        }
    }

    public Student[] searchStudent(String lastName) {

        List<Student> buffer = new ArrayList<>();

        for (Student student : this.students) {
            if (student == null) continue;
            if (student.name.matches(".*\\b" + lastName + "\\b")) {
                buffer.add(student);
            }
        }

        Student[] searchResult = new Student[buffer.size()];

        return buffer.toArray(searchResult);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    public Student[] sortByLastName() {
        return this.sortByLastName(SortOrder.ASC);
    }

    public Student[] sortByLastName(SortOrder order) {

        Student[] students = this.getStudents();
        Comparator<Student> comparator = new LastNameComparator();

        if (order.equals(SortOrder.DESC)) {
            Arrays.sort(students, comparator.reversed());
        } else {
            Arrays.sort(students, comparator);
        }

        return students;
    }

    @Override
    public Student[] getMilitia() {

        IsMilita isMilitia = (Human h) -> {
            return h != null &&
                    h.gender.equals(Gender.MALE) &&
                    h.getAgeInYears() >= 18;
        };

        List<Student> militia = this.getStudentsList()
                .stream()
                .filter(isMilitia::check)
                .collect(Collectors.toList());

        Student[] students = new Student[militia.size()];

        return militia.toArray(students);
    }

    public String toCSVString() {
        StringBuilder strB = new StringBuilder();
        for (CsvCompatible student : this.getStudents()) {
            strB.append(student.toCSVString());
            strB.append("\n");
        }

        return strB.toString();
    }

    @Override
    public String toString() {
        return "Group{" +
                "maxLength=" + maxLength +
                ", students=" + Arrays.toString(this.getStudents()) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (maxLength != group.maxLength) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(getStudents(), group.getStudents());
    }

    @Override
    public int hashCode() {
        int result = maxLength;
        result = 31 * result + Arrays.hashCode(getStudents());
        return result;
    }
}
