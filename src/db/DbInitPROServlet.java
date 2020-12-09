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
@WebServlet("/dbInit3")
public class DbInitPROServlet extends HttpServlet implements DatabaseComminInterface {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DbInitPROServlet() {
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
		PreparedStatement pstmt = con.prepareStatement("create table PRO(pro_id int IDENTITY(1,1) NOT NULL PRIMARY KEY,pro_name nvarchar(100),pro_partno INT,pro_date datetime)");
		pstmt.executeUpdate();
		out.println("table 'PRO' created.");
		
	}
	
	private void insertEmp(PrintWriter out, Connection con) throws SQLException {
		
		PreparedStatement pstmt2 = con.prepareStatement("insert into PRO(pro_name,pro_partno,pro_date) values('ƒeƒXƒg‰Û‘è',4,GETDATE())");
		pstmt2.executeUpdate();
		
	}
	
	private void dropEmp(PrintWriter out, Connection con)  {
		try {
		PreparedStatement pstmt = con.prepareStatement("drop table PRO");
		pstmt.executeUpdate();
		out.println("table 'PRO' dropped.");
		} catch (SQLException e) {}
	}

}
