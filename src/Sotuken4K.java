

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
 * Servlet implementation class NewUserServlet
 */
@WebServlet("/Sotuken4")
public class Sotuken4K extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sotuken4K() {
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
		request.getSession().setAttribute("pro_partno", Integer.parseInt(request.getParameter("pro_partno")));
		int c = Integer.parseInt(request.getParameter("pro_partno"));
		
		
	
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			Connection con = DatabaseComminInterface.getConnection();
			PreparedStatement promen1 = con.prepareStatement("insert into PROMEN(user_id, leader_f) values(1,1)");
			ResultSet rs1 = promen1.executeQuery();
			rs1.next();
			
			for(int i=2; i>=c; i++) {
				PreparedStatement promen2 = con.prepareStatement("insert into PROMEN(user_id, leader_f) values(?,0)");
				promen2.setInt(1, i);
				ResultSet rs2 = promen2.executeQuery();
				rs2.next();	
			}
			
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
