package service;

import model.Student;

public interface RecordActions {
    void addStudent(Student s);
    void deleteStudent(String name) throws Exception;
    Student searchStudent(String name) throws Exception;
    void updateStudent(String name) throws Exception;
    void viewAllStudents();
}
