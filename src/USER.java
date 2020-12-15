

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
		
		final String email=request.getParameter("email");
		final String name=request.getParameter("name");
		
			try {
				Connection con = DatabaseComminInterface.getConnection();
				
				insertEmp(out,con,email,name);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(out);
				e.printStackTrace(System.out);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(out);
				e.printStackTrace(System.out);
			}
		
			
			
		
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/JSP/tousenCmp.jsp");
		rd.forward(request, response);
	}
	
private void insertEmp(PrintWriter out, Connection con,String email,String name) throws SQLException {
		
		
		
		PreparedStatement pstmt2 = con.prepareStatement("insert into USERX(user_name,user_pass) values(?,?)");
		
		pstmt2.setString(1, email);
		pstmt2.setString(2, name);
		pstmt2.executeUpdate();
	}
}

