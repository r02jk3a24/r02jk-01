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
@WebServlet("/dbInit12")
public class DbInitFORMATServlet extends HttpServlet implements DatabaseComminInterface {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DbInitFORMATServlet() {
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
				
				dropEmp(out, con);
				createEmp(out, con);
				insertEmp(out,con);
				
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
		PreparedStatement pstmt = con.prepareStatement("create table FORMAT(form_id int NOT NULL ,form_name nvarchar(100) NOT NULL,PRIMARY KEY(form_id))");
		pstmt.executeUpdate();
		out.println("table 'FORMAT' created.");
		
	}
	
	private void insertEmp(PrintWriter out, Connection con) throws SQLException {
		
		PreparedStatement pstmt2 = con.prepareStatement("insert into FORMAT(form_id,form_name) values(1,'ラジオボタン')");
		pstmt2.executeUpdate();
		PreparedStatement pstmt3 = con.prepareStatement("insert into FORMAT(form_id,form_name) values(2,'チェックボックス')");
		pstmt3.executeUpdate();
		PreparedStatement pstmt4 = con.prepareStatement("insert into FORMAT(form_id,form_name) values(3,'テキストボックス')");
		pstmt4.executeUpdate();
		
	}
	
	private void dropEmp(PrintWriter out, Connection con)  {
		try {
		PreparedStatement pstmt = con.prepareStatement("drop table FORMAT");
		pstmt.executeUpdate();
		out.println("table 'FORMAT' dropped.");
		} catch (SQLException e) {}
	}

}
