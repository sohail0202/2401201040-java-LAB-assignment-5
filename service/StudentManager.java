package service;

import model.Student;
import java.io.*;
import java.util.*;

public class StudentManager implements RecordActions {

    private Map<Integer, Student> studentMap = new HashMap<>();

    // LOAD FROM FILE
    public void loadFromFile() {

        try (BufferedReader br = new BufferedReader(new FileReader("students.txt"))) {

            String line;
            while ((line = br.readLine()) != null) {

                String[] s = line.split(",");

                int roll = Integer.parseInt(s[0]);
                String name = s[1];
                String email = s[2];
                String course = s[3];
                double marks = Double.parseDouble(s[4]);

                studentMap.put(roll, new Student(roll, name, email, course, marks));
            }

            System.out.println("Records Loaded.");
        } catch (Exception e) {
            System.out.println("No previous record found.");
        }
    }

    // SAVE INTO FILE
    public void saveToFile() {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("students.txt"))) {

            for (Student s : studentMap.values()) {

                bw.write(
                        s.getRollNo() + "," +
                        s.getName() + "," +
                        s.getEmail() + "," +
                        s.getCourse() + "," +
                        s.getMarks()
                );

                bw.newLine();
            }

            System.out.println("Records Saved.");

        } catch (Exception e) {
            System.out.println("Error saving file.");
        }
    }

    @Override
    public void addStudent(Student s) {

        if (studentMap.containsKey(s.getRollNo())) {
            System.out.println("Duplicate Roll Number!");
            return;
        }

        Thread t = new Thread(new Loader());
        t.start();

        studentMap.put(s.getRollNo(), s);
        System.out.println("Student Added!");
    }

    @Override
    public void deleteStudent(String name) throws Exception {

        boolean found = false;

        Iterator<Student> it = studentMap.values().iterator();

        while (it.hasNext()) {

            Student s = it.next();
            if (s.getName().equalsIgnoreCase(name)) {
                it.remove();
                found = true;
                break;
            }
        }

        if (!found)
            throw new StudentNotFoundException("Student Not Found!");

        System.out.println("Student Deleted.");
    }

    @Override
    public Student searchStudent(String name) throws Exception {

        for (Student s : studentMap.values()) {

            if (s.getName().equalsIgnoreCase(name))
                return s;
        }

        throw new StudentNotFoundException("Student Not Found!");
    }

    @Override
    public void updateStudent(String name) throws Exception {

        Student s = searchStudent(name);

        System.out.println("Enter Updated Details: ");
        s.inputDetails();
    }

    @Override
    public void viewAllStudents() {

        if (studentMap.isEmpty()) {
            System.out.println("No Students Found.");
            return;
        }

        for (Student s : studentMap.values()) {
            s.displayDetails();
            System.out.println("------------------------");
        }
    }

    public void sortByMarks() {

        List<Student> list = new ArrayList<>(studentMap.values());
        list.sort((a, b) -> Double.compare(b.getMarks(), a.getMarks()));

        System.out.println("Sorted by Marks:");
        for (Student s : list) {
            s.displayDetails();
            System.out.println("------------------------");
        }
    }
}
