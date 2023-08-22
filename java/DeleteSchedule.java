import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
@WebServlet("/DelSchedule")

public class DeleteSchedule extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public DeleteSchedule() {
		super();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		PrintWriter pw = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
//		String name = request.getParameter("name");
				
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Payrolldata","root","mysql");
			System.out.println("Connected "+con);
			String sql = "DELETE FROM employeeschedules where id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
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