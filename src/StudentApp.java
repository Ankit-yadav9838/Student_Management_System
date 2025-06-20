import java.sql.*;
import java.util.Scanner;

public class StudentApp {
    static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl"; // SID-based
    static final String USER = "scott"; // change as per your Oracle DB user
    static final String PASSWORD = "tiger"; // your DB password

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                Scanner sc = new Scanner(System.in)) {

            int choice;
            do {
                System.out.println("\n===== Student Management System =====");
                System.out.println("1. Add Student");
                System.out.println("2. View All Students");
                System.out.println("3. Search Student by ID");
                System.out.println("4. Update Student");
                System.out.println("5. Delete Student");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        addStudent(conn, sc);
                        break;
                    case 2:
                        viewStudents(conn);
                        break;
                    case 3:
                        searchStudent(conn, sc);
                        break;
                    case 4:
                        updateStudent(conn, sc);
                        break;
                    case 5:
                        deleteStudent(conn, sc);
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } while (choice != 6);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void addStudent(Connection conn, Scanner sc) throws SQLException {
        sc.nextLine(); // Clear buffer
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter roll number: ");
        String rollNo = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter course: ");
        String course = sc.nextLine();

        String sql = "INSERT INTO students (name, roll_no, email, age, course) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, rollNo);
        ps.setString(3, email);
        ps.setInt(4, age);
        ps.setString(5, course);
        ps.executeUpdate();

        System.out.println("Student added successfully.");
    }

    static void viewStudents(Connection conn) throws SQLException {
        String sql = "SELECT * FROM students";
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            System.out.printf("%-5s %-20s %-10s %-5s %-25s %-15s%n", "ID", "Name", "Roll No", "Age", "Email", "Course");
            System.out.println("--------------------------------------------------------------------------");
            while (rs.next()) {
                System.out.printf("%-5d %-20s %-10s %-5d %-25s %-15s%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("roll_no"),
                        rs.getInt("age"),
                        rs.getString("email"),
                        rs.getString("course"));
            }
        }
    }

    static void searchStudent(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter student ID: ");
        int id = sc.nextInt();

        String sql = "SELECT * FROM students WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Roll No: " + rs.getString("roll_no"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Course: " + rs.getString("course"));
            } else {
                System.out.println("Student not found.");
            }
        }
    }

    static void updateStudent(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter student ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter new name: ");
        String name = sc.nextLine();

        System.out.print("Enter new roll number: ");
        String rollNo = sc.nextLine();

        System.out.print("Enter new email: ");
        String email = sc.nextLine();

        System.out.print("Enter new age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter new course: ");
        String course = sc.nextLine();

        String sql = "UPDATE students SET name = ?, roll_no = ?, email = ?, age = ?, course = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, rollNo);
            ps.setString(3, email);
            ps.setInt(4, age);
            ps.setString(5, course);
            ps.setInt(6, id);

            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Student updated." : "Student not found.");
        }
    }

    static void deleteStudent(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter student ID to delete: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Student deleted." : "Student not found.");
        }
    }
}
