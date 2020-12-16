

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
@WebServlet("/Sotuken1")
public class Sotuken1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sotuken1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			String userid = (String)request.getSession().getAttribute("userid");
			Connection con = DatabaseComminInterface.getConnection();
			
			PreparedStatement pstmt1 = con.prepareStatement("select user_name from USERX join PROMEN on (USERX.user_id = PROMEN.user_id) where USERX.user_id = ?");
			pstmt1.setString(1, "1");
			ResultSet rs1 = pstmt1.executeQuery();
			rs1.next();
			request.getSession().setAttribute("user_name", rs1.getString("user_name"));
			
			PreparedStatement pstmt2 = con.prepareStatement("select PRO.pro_id,pro_name,leader_f from PRO join PROMEN on(PRO.pro_id = PROMEN.pro_id) where PROMEN.user_id = ?");
			pstmt2.setString(1, "1");
			ResultSet rs2 = pstmt2.executeQuery();
			
			ArrayList<String[]> taskList = new ArrayList<>();
			
			while(rs2.next() == true) {
				String[] ss = new String[3];
				ss[0]=rs2.getString("pro_id");
				ss[1]=rs2.getString("pro_name");
				ss[2]=rs2.getString("leader_f");
				
				
				taskList.add(ss);
			}
			request.getSession().setAttribute("taskList", taskList);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/project-1.jsp");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
