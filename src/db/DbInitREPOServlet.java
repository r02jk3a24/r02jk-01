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
@WebServlet("/dbInit10")
public class DbInitREPOServlet extends HttpServlet implements DatabaseComminInterface {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DbInitREPOServlet() {
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
		PreparedStatement pstmt = con.prepareStatement("create table REPO(pro_id int NOT NULL ,task_id int NOT NULL,user_id int NOT NULL,repo_date datetime,PRIMARY KEY(pro_id,task_id,user_id,repo_date),FOREIGN KEY(pro_id,task_id,user_id) REFERENCES TASKMEN(pro_id,task_id,user_id))");
		pstmt.executeUpdate();
		out.println("table 'REPO' created.");
		
	}
	
	private void insertEmp(PrintWriter out, Connection con) throws SQLException {
		
		PreparedStatement pstmt2 = con.prepareStatement("insert into REPO(pro_id,task_id,user_id,repo_date) values(1,1,1,GETDATE())");
		pstmt2.executeUpdate();
		PreparedStatement pstmt3 = con.prepareStatement("insert into REPO(pro_id,task_id,user_id,repo_date) values(1,1,5,GETDATE())");
		pstmt3.executeUpdate();
		
	}
	
	private void dropEmp(PrintWriter out, Connection con)  {
		try {
		PreparedStatement pstmt = con.prepareStatement("drop table REPO");
		pstmt.executeUpdate();
		out.println("table 'REPO' dropped.");
		} catch (SQLException e) {}
	}

}
