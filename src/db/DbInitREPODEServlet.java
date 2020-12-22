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
@WebServlet("/dbInit11")
public class DbInitREPODEServlet extends HttpServlet implements DatabaseComminInterface {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DbInitREPODEServlet() {
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
		PreparedStatement pstmt = con.prepareStatement("create table REPODE(pro_id int NOT NULL ,task_id int NOT NULL,user_id int NOT NULL,repo_date datetime,item_id int NOT NULL,con_id int NOT NULL,repo_con nvarchar(200),PRIMARY KEY(pro_id,task_id,user_id,repo_date,item_id,con_id),FOREIGN KEY(pro_id,task_id,user_id,repo_date) REFERENCES REPO(pro_id,task_id,user_id,repo_date),FOREIGN KEY(pro_id,task_id,item_id,con_id) REFERENCES TASKITEMDE(pro_id,task_id,item_id,con_id))");
		pstmt.executeUpdate();
		out.println("table 'REPODE' created.");
		
	}
	
	private void insertEmp(PrintWriter out, Connection con) throws SQLException {
		
		PreparedStatement pstmt2 = con.prepareStatement("insert into REPODE(pro_id,task_id,user_id,repo_date,item_id,con_id,repo_con) values(1,1,1,'2020-12-09 05:19:01.577',1,1,'‘I‘ð')");
		pstmt2.executeUpdate();
		PreparedStatement pstmt3 = con.prepareStatement("insert into REPODE(pro_id,task_id,user_id,repo_date,item_id,con_id,repo_con) values(1,1,1,'2020-12-09 05:19:01.577',1,2,'”ñ‘I‘ð')");
		pstmt3.executeUpdate();
		PreparedStatement pstmt4 = con.prepareStatement("insert into REPODE(pro_id,task_id,user_id,repo_date,item_id,con_id,repo_con) values(1,1,1,'2020-12-09 05:19:01.577',1,3,'”ñ‘I‘ð')");
		pstmt4.executeUpdate();
		
	}
	
	private void dropEmp(PrintWriter out, Connection con)  {
		try {
		PreparedStatement pstmt = con.prepareStatement("drop table REPODE");
		pstmt.executeUpdate();
		out.println("table 'REPODE' dropped.");
		} catch (SQLException e) {}
	}

}
