package com.kp.chukhnovm.hw9.Services;

import com.kp.chukhnovm.hw9.Enums.Gender;
import com.kp.chukhnovm.hw9.Exceptions.GroupDuplicateStudentException;
import com.kp.chukhnovm.hw9.Exceptions.GroupFulFilledException;
import com.kp.chukhnovm.hw9.Interfaces.CsvCompatible;

import java.io.*;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class GroupService {

    public static com.kp.chukhnovm.hw9.Group exportGroup(String filePath) {

        String line;
        String lineArray[];
        String separator = ",";
        com.kp.chukhnovm.hw9.Group group = new com.kp.chukhnovm.hw9.Group();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            while ((line = br.readLine()) != null) {

                // Prepare line
                lineArray = line.replace("\"", "").split(separator);

                // Create Student from parsed cells
                com.kp.chukhnovm.hw9.Student student = new com.kp.chukhnovm.hw9.Student();

                student.setName(lineArray[0]);
                student.setBirthDay(lineArray[1]);
                student.setGender(lineArray[2]);

                // As optional parameter documentId should be checked
                if (lineArray.length == 4) {
                    student.setDocumentId(lineArray[3]);
                } else {
                    student.generateDocumentId();
                }

                // Add students to group
                group.addStudent(student);
            }

        } catch (IOException | ParseException | GroupDuplicateStudentException | GroupFulFilledException e) {
            e.printStackTrace();
        }

        return group;
    }

    public static void importGroup(CsvCompatible canCsv, String targetFilePath) {

        File f = new File(targetFilePath);

        try (PrintWriter a = new PrintWriter(new FileOutputStream(
                new File(targetFilePath),
                f.exists()
        ))) {

            a.println(canCsv.toCSVString());

        } catch (FileNotFoundException e) {
            System.err.println("ERROR FILE WRITE");
        }
    }

    public static void generateCSV(String targetFilePath, int groups) {

        try {
            com.kp.chukhnovm.hw9.Student[] students = {
                    new com.kp.chukhnovm.hw9.Student("Valentyn Test6", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.FEMALE),
                    new com.kp.chukhnovm.hw9.Student("Vladislav Test7", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.MALE),
                    new com.kp.chukhnovm.hw9.Student("Andrew Test1", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.MALE),
                    new com.kp.chukhnovm.hw9.Student("John Test2", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.FEMALE),
                    new com.kp.chukhnovm.hw9.Student("Peter Test3", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.MALE),
                    new com.kp.chukhnovm.hw9.Student("Lesya Test4", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.FEMALE),
                    new com.kp.chukhnovm.hw9.Student("Petro Test8", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.FEMALE),
                    new com.kp.chukhnovm.hw9.Student("Tesla Test9", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.MALE),
                    new com.kp.chukhnovm.hw9.Student("Rocket Test10", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.MALE),
                    new com.kp.chukhnovm.hw9.Student("Rocket Test10", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.MALE),
            };

            com.kp.chukhnovm.hw9.Group group = new com.kp.chukhnovm.hw9.Group(students);

            for (int i = 1; i < groups; i++) {
                GroupService.importGroup(group, targetFilePath);
                System.out.println(String.format("Iteration №%d Students added: %d", i, i * students.length));
            }

        } catch (GroupFulFilledException e) {
            e.printStackTrace();
        }

    }

    public static String serializeToFile(com.kp.chukhnovm.hw9.Group group) throws IOException {

        String fileName = group.getClass() + "-" + group.hashCode();
        try(ObjectOutputStream OOS=new ObjectOutputStream(new FileOutputStream(fileName))){
            OOS.writeObject(group);
        } catch(IOException e){
            throw e;
        }

        return fileName;
    }

    public static com.kp.chukhnovm.hw9.Group unserializeFromFile(String fileName) throws IOException, ClassNotFoundException {

        com.kp.chukhnovm.hw9.Group group = null;

        try (ObjectInputStream OIS=new ObjectInputStream(new FileInputStream(fileName))){
            group=(com.kp.chukhnovm.hw9.Group)OIS.readObject();
        }
        catch(IOException | ClassNotFoundException e){
            throw e;
        }

        return group;
    }
}
