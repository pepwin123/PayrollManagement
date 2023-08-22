import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
@WebServlet("/AddSchedule")

public class AddSchedule extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public AddSchedule() {
		super();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		PrintWriter pw = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String days = request.getParameter("date");
		int hours = Integer.parseInt(request.getParameter("hours"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Payrolldata","root","mysql");
			System.out.println("Connected "+con);
			String sql = "INSERT INTO employeeschedules VALUES(?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, days);
			ps.setInt(4, hours);
			
			ps.executeUpdate();
			con.close();
//			response.getWriter();
//			DriverManager.println("Inserted successfully");
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		doGet(req, response);
	}
}