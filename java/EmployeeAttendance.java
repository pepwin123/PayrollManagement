import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EmployeeAttendance")
public class EmployeeAttendance extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EmployeeAttendance() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int days = Integer.parseInt(request.getParameter("date"));
        int hours = Integer.parseInt(request.getParameter("hours"));

        // Calculate attendance
        int attendance = calculateAttendance(days);

        // Calculate total salary
        long totalSalary = calculateSalary(attendance, hours);

        // Print the total salary
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Employee Attendance</h2>");
        out.println("<p>Id: " + id + "</p>");
        out.println("<p>Name: " + name + "</p>");
        out.println("<p>Attendance: " + attendance + "</p>");
        out.println("</body></html>");

        // Save the employee details and total salary in the database
        saveEmployeeDetails(id, name, totalSalary, attendance);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private int calculateAttendance(int days) {
        // Assuming a simple calculation based on days worked
        int attendance = 0;
        if (days >= 20) {
            attendance = 30;
        } else if (days >= 15) {
            attendance = 25;
        } else if (days >= 10) {
            attendance = 20;
        } else if (days >= 5) {
            attendance = 15;
        } else {
            attendance = 10;
        }
        return attendance;
    }

    private long calculateSalary(int attendance, int hours) {
        // Assuming a simple calculation based on attendance and hours
        long salaryPerHour = 10; // Assuming $10 per hour
        long totalSalary = salaryPerHour * attendance * hours;
        return totalSalary;
    }

    private void saveEmployeeDetails(int id, String name, long totalSalary, int attendance) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Payrolldata", "root", "mysql");
            System.out.println("Connected " + con);

            String sql = "INSERT INTO employeesattendance (id, name, salary, attendance) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setLong(3, totalSalary);
            ps.setInt(4, attendance);
            ps.executeUpdate();

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
