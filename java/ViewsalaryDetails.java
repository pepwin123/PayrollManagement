import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
@WebServlet("/ViewDetails")

public class ViewsalaryDetails extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public ViewsalaryDetails() {
		super();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter pw = response.getWriter();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Payrolldata","root","mysql");
			System.out.println("Connected "+con);
			String sql = "SELECT * FROM employees";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			pw.println("LIST OF EMPLOYEE SALARY DETAILS");
			pw.println();
			while(rs.next()) {
				pw.println("ID: "+rs.getInt(1));
				pw.println("Name: "+rs.getString(2));
				pw.println("Salary: "+rs.getLong(3));
				pw.println();
			}
			con.close();

		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		doGet(req, response);
	}
}