

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
 * Servlet implementation class NewUserServlet
 */
@WebServlet("/Sotuken4")
public class Sotuken4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sotuken4() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("pro_name", request.getParameter("pro_name"));
		request.getSession().setAttribute("pro_partno", Integer.parseInt(request.getParameter("pro_partno")));
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		try {
			Connection con = DatabaseComminInterface.getConnection();
			 PreparedStatement pstmt = con.prepareStatement("insert into PRO(pro_name, pro_partno, pro_date) values(?,?,getdate())");
			String pro_name = request.getParameter("pro_name");
			String pro_partno = request.getParameter("pro_partno");
			pstmt.setString(1,pro_name);
			pstmt.setString(2,pro_partno);
			pstmt.executeUpdate();
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/project-4.jsp");
			rd.forward(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace(out);
			e.printStackTrace(System.out);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(out);
			e.printStackTrace(System.out);
		}
		
	
	}

}
