

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
@WebServlet("/Sotuken4K")
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
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String pname = (String)request.getSession().getAttribute("pro_name");
		String[] uname = request.getParameterValues("uname");
		int c = Integer.parseInt(request.getParameter("PRON"));
		
		PrintWriter out = response.getWriter();
		try {
			Connection con = DatabaseComminInterface.getConnection();
			PreparedStatement pstmt0 = con.prepareStatement("select pro_id from PRO where pro_name = ?");
			pstmt0.setString(1,pname);
			ResultSet rs1 = pstmt0.executeQuery();
			rs1.next();
			
			
			for(int i=0; i<c; i++) {
				PreparedStatement pstmt1 = con.prepareStatement("select user_id from USERX where user_name = ?");
				pstmt1.setString(1,uname[i]);
				ResultSet rs2 = pstmt1.executeQuery();
				rs2.next();
				if(i==0) {
					PreparedStatement pstmt2 = con.prepareStatement("insert into PROMEN(pro_id, user_id, leader_f) values(?,?,1)");
					pstmt2.setInt(1,rs1.getInt("pro_id"));
					pstmt2.setInt(2, rs2.getInt("user_id"));
					pstmt2.executeUpdate();
				}else {
				PreparedStatement pstmt3 = con.prepareStatement("insert into PROMEN(pro_id, user_id, leader_f) values(?,?,0)");
				pstmt3.setInt(1,rs1.getInt("pro_id"));
				pstmt3.setInt(2, rs2.getInt("user_id"));
				pstmt3.executeUpdate();	
				}
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/project-5.jsp");
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
