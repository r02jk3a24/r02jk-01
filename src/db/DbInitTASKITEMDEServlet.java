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
@WebServlet("/dbInit9")
public class DbInitTASKITEMDEServlet extends HttpServlet implements DatabaseComminInterface {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DbInitTASKITEMDEServlet() {
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
		PreparedStatement pstmt = con.prepareStatement("create table TASKITEMDE(pro_id int NOT NULL ,task_id int NOT NULL,item_id int NOT NULL,con_id INT,con_name nvarchar(100),PRIMARY KEY(pro_id,task_id,item_id,con_id),FOREIGN KEY(pro_id,task_id,item_id) REFERENCES TASKITEM(pro_id,task_id,item_id))");
		pstmt.executeUpdate();
		out.println("table 'TASKITEMDE' created.");
		
	}
	
	private void insertEmp(PrintWriter out, Connection con) throws SQLException {
		
		PreparedStatement pstmt2 = con.prepareStatement("insert into TASKITEMDE(pro_id,task_id,item_id,con_id,con_name) values(1,1,1,1,'‘I‘ðŽˆA')");
		pstmt2.executeUpdate();
		PreparedStatement pstmt3 = con.prepareStatement("insert into TASKITEMDE(pro_id,task_id,item_id,con_id,con_name) values(1,1,1,2,'‘I‘ðŽˆB')");
		pstmt3.executeUpdate();
		PreparedStatement pstmt4 = con.prepareStatement("insert into TASKITEMDE(pro_id,task_id,item_id,con_id,con_name) values(1,1,1,3,'‘I‘ðŽˆC')");
		pstmt4.executeUpdate();
		
	}
	
	private void dropEmp(PrintWriter out, Connection con)  {
		try {
		PreparedStatement pstmt = con.prepareStatement("drop table TASKITEMDE");
		pstmt.executeUpdate();
		out.println("table 'TASKITEMDE' dropped.");
		} catch (SQLException e) {}
	}

}
