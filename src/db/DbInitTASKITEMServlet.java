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
@WebServlet("/dbInit8")
public class DbInitTASKITEMServlet extends HttpServlet implements DatabaseComminInterface {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DbInitTASKITEMServlet() {
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
		PreparedStatement pstmt = con.prepareStatement("create table TASKITEM(pro_id int NOT NULL ,task_id int NOT NULL,item_id int NOT NULL,form_id INT,con_no INT,item_name nvarchar(100),PRIMARY KEY(pro_id,task_id,item_id),FOREIGN KEY(pro_id,task_id) REFERENCES TASK(pro_id,task_id),FOREIGN KEY(form_id) REFERENCES FORMAT(form_id))");
		pstmt.executeUpdate();
		out.println("table 'TASKITEM' created.");
		
	}
	
	private void insertEmp(PrintWriter out, Connection con) throws SQLException {
		
		PreparedStatement pstmt2 = con.prepareStatement("insert into TASKITEM(pro_id,task_id,item_id,form_id,con_no,item_name) values(1,1,1,1,3,'ÉeÉXÉgçÄñ⁄')");
		pstmt2.executeUpdate();
		
	}
	
	private void dropEmp(PrintWriter out, Connection con)  {
		try {
		PreparedStatement pstmt = con.prepareStatement("drop table TASKITEM");
		pstmt.executeUpdate();
		out.println("table 'TASKITEM' dropped.");
		} catch (SQLException e) {}
	}

}
