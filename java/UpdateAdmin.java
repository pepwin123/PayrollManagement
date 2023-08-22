import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
@WebServlet("/UpdateEmployee")

public class UpdateAdmin extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public UpdateAdmin() {
		super();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		PrintWriter pw = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		long salary = Long.parseLong(request.getParameter("salary"));
		long cont = Long.parseLong(request.getParameter("cont"));
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Payrolldata","root","mysql");
			System.out.println("Connected "+con);
			String sql = "UPDATE employees set name=?, cont=?, salary=? where id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setLong(2, cont);
			ps.setLong(3, salary);
			ps.setInt(4, id);
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