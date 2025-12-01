import java.util.Scanner;
import model.Student;
import service.StudentManager;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentManager sm = new StudentManager();

        sm.loadFromFile();

        while (true) {

            System.out.println("\n===== Student Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Sort by Marks");
            System.out.println("6. Save & Exit");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();
            sc.nextLine();

            try {

                switch (ch) {

                    case 1:
                        Student s = new Student();
                        s.inputDetails();
                        sm.addStudent(s);
                        break;

                    case 2:
                        sm.viewAllStudents();
                        break;

                    case 3:
                        System.out.print("Enter name: ");
                        sm.searchStudent(sc.nextLine()).displayDetails();
                        break;

                    case 4:
                        System.out.print("Enter name: ");
                        sm.deleteStudent(sc.nextLine());
                        break;

                    case 5:
                        sm.sortByMarks();
                        break;

                    case 6:
                        sm.saveToFile();
                        System.out.println("Saved. Exiting...");
                        return;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (Exception e) {
                System.out.println("Error: " +
                        e.getMessage());
            }
        }
    }
}
