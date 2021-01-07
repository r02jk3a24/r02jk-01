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
@WebServlet("/dbInit5")
public class DbInitPROMENServlet extends HttpServlet implements DatabaseComminInterface {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DbInitPROMENServlet() {
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
		PreparedStatement pstmt = con.prepareStatement("create table PROMEN(pro_id int NOT NULL ,user_id int NOT NULL ,leader_f INT,PRIMARY KEY(pro_id,user_id),FOREIGN KEY(pro_id) REFERENCES PRO(pro_id),FOREIGN KEY(user_id) REFERENCES USERX(user_id))");
		pstmt.executeUpdate();
		out.println("table 'PROMEN' created.");
		
	}
	
	private void insertEmp(PrintWriter out, Connection con) throws SQLException {
		
		PreparedStatement pstmt2 = con.prepareStatement("insert into PROMEN(pro_id,user_id,leader_f) values(2,14,1)");
		pstmt2.executeUpdate();
		
		//PreparedStatement pstmt3 = con.prepareStatement("insert into PROMEN(pro_id,user_id,leader_f) values(1,6,0)");
		//pstmt3.executeUpdate();
		//PreparedStatement pstmt4 = con.prepareStatement("insert into PROMEN(pro_id,user_id,leader_f) values(1,7,0)");
		//pstmt4.executeUpdate();
		
	}
	
	private void dropEmp(PrintWriter out, Connection con)  {
		try {
		PreparedStatement pstmt = con.prepareStatement("drop table PROMEN");
		pstmt.executeUpdate();
		out.println("table 'PROMEN' dropped.");
		} catch (SQLException e) {}
	}

}
