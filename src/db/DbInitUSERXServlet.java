package db;


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

/**
 * Servlet implementation class DbInitServlet
 */
@WebServlet("/dbInit4")
public class DbInitUSERXServlet extends HttpServlet implements DatabaseComminInterface {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DbInitUSERXServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = response.getWriter();
		

			try {
				Connection con = DatabaseComminInterface.getConnection();
				
				//dropEmp(out, con);
				//deleteEmp(out,con);
				//createEmp(out, con);
				//insertEmp(out,con);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(out);
				e.printStackTrace(System.out);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(out);
				e.printStackTrace(System.out);
			}
		
	}



	private void createEmp(PrintWriter out, Connection con) throws SQLException {
		PreparedStatement pstmt = con.prepareStatement("create table USERX(user_id int IDENTITY(1,1) NOT NULL PRIMARY KEY,user_name nvarchar(100))");
		pstmt.executeUpdate();
		out.println("table 'USERX' created.");
		
	}
	
	private void insertEmp(PrintWriter out, Connection con) throws SQLException {
		
		
		PreparedStatement pstmt2 = con.prepareStatement("insert into USERX(user_name) values('B‚³‚ñ')");
		pstmt2.executeUpdate();
		
		PreparedStatement pstmt3 = con.prepareStatement("insert into USERX(user_name) values('C‚³‚ñ')");
		pstmt3.executeUpdate();
		PreparedStatement pstmt4 = con.prepareStatement("insert into USERX(user_name) values('D‚³‚ñ')");
		pstmt4.executeUpdate();
		
		
	}
	
	private void dropEmp(PrintWriter out, Connection con)  {
		try {
		PreparedStatement pstmt = con.prepareStatement("drop table USERX");
		pstmt.executeUpdate();
		out.println("table 'USERX' dropped.");
		} catch (SQLException e) {}
	}
	
private void deleteEmp(PrintWriter out, Connection con) throws SQLException {
		
		PreparedStatement pstmt5 = con.prepareStatement("delete from USERX where user_name='B‚³‚ñ'");
		pstmt5.executeUpdate();
		
		
		
	}

}
