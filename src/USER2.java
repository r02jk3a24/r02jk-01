

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
@WebServlet("/USER2")
public class USER2 extends HttpServlet implements DatabaseComminInterface{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public USER2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		//final String email=session.getAttribute("email");
		String email="113@jc-21.jp";
		int h=0;
		String id=null;
		String name=null;
		
			try {
				Connection con = DatabaseComminInterface.getConnection();
				
				h=selectEmp(out,con,email);
				if(h==1) {
					RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/user.jsp");
					rd.forward(request, response);
				}
				
				else {
					id=selectId(out,con,email);
					name=selectName(out,con,email);
					request.getSession().setAttribute("userid",id);
					request.getSession().setAttribute("name",name);
					RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/hello.jsp");
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
	
private int selectEmp(PrintWriter out, Connection con,String email) throws SQLException {
		
		int h=0;
		
		PreparedStatement pstmt2 = con.prepareStatement("select user_id from USERX where user_mail =?");
		
		pstmt2.setString(1, email);
		ResultSet rs = pstmt2.executeQuery();
		if(rs.next()==false) {
			h=1;
		}
		out.println(h);
		
		
		return h;
	}

private String selectId(PrintWriter out, Connection con,String email) throws SQLException {
	
	String id=null;
	
	PreparedStatement pstmt4 = con.prepareStatement("select user_id from USERX where user_mail=?");
	
	pstmt4.setString(1, email);
	ResultSet ss = pstmt4.executeQuery();
	while(ss.next()==true) {
		id=ss.getString("user_id");
	}
	out.println(id);
	return id;
}
private String selectName(PrintWriter out, Connection con,String email) throws SQLException {
	
	String name=null;
	
	PreparedStatement pstmt5 = con.prepareStatement("select user_name from USERX where user_mail=?");
	
	pstmt5.setString(1, email);
	ResultSet sf = pstmt5.executeQuery();
	while(sf.next()==true) {
		name=sf.getString("user_name");
	}
	out.println(name);
	return name;
}
}

