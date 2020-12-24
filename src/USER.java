

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DatabaseComminInterface;

/**
 * Servlet implementation class USER
 */
@WebServlet("/USER")
public class USER extends HttpServlet implements DatabaseComminInterface{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public USER() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		
		final String email=request.getParameter("email");
		
		final String name=request.getParameter("name");
		int s=0;
		String id=null;
			try {
				Connection con = DatabaseComminInterface.getConnection();
				
				s=selectEmp(out,con,name);
				if(s==0) {
				insertEmp(out,con,email,name);
				id=selectId(out,con,name);
				request.getSession().setAttribute("userid",id);
				request.getSession().setAttribute("name",name);
				RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/hello.jsp");
				rd.forward(request, response);
				}
				else {
					RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/erroruser.jsp");
					rd.forward(request, response);
				}
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
	
private void insertEmp(PrintWriter out, Connection con,String email,String name) throws SQLException {
		
		
		
		PreparedStatement pstmt2 = con.prepareStatement("insert into USERX(user_name,user_mail) values(?,?)");
		
		pstmt2.setString(1, name);
		pstmt2.setString(2, email);
		pstmt2.executeUpdate();
	}

private int selectEmp(PrintWriter out, Connection con,String name) throws SQLException {
	
	int s=0;
	
	PreparedStatement pstmt3 = con.prepareStatement("select user_name from USERX where user_name=?");
	
	pstmt3.setString(1, name);
	ResultSet ss = pstmt3.executeQuery();
	if(ss.next()==true) {
		s=1;
	}
	return s;
}

private String selectId(PrintWriter out, Connection con,String name) throws SQLException {
	
	String id=null;
	
	PreparedStatement pstmt4 = con.prepareStatement("select user_id from USERX where user_name=?");
	
	pstmt4.setString(1, name);
	ResultSet ss = pstmt4.executeQuery();
	while(ss.next()==true) {
		id=ss.getString("user_id");
	}
	return id;
}
}

