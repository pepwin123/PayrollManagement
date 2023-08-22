import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Salarycal")
public class SalaryCalculator extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SalaryCalculator() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int hours = Integer.parseInt(request.getParameter("hours"));
        int days = Integer.parseInt(request.getParameter("days"));
        long salary = Long.parseLong(request.getParameter("salary"));

        // Calculate total salary
        long totalSalary = salary * hours * days;

        // Print the total salary
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Employee Added Successfully</h2>");
        out.println("<p>Total Salary: " + totalSalary + "</p>");
        out.println("</body></html>");

        // Save the employee details and total salary in the database
        saveEmployeeDetails(id, name, totalSalary);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void saveEmployeeDetails(int id, String name, long totalSalary) {
        // Perform database operations to save the employee details and total salary
        // You can use JDBC to connect to the database and execute the necessary queries
        // Here, we assume a simple implementation without a database connection
        System.out.println("Employee Details Saved - ID: " + id + ", Name: " + name + ", Total Salary: " + totalSalary);
    }
}
