package db;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DbSampleServlet
 */
@WebServlet("/dbSample8")
public class DbSampleTASKITEMServlet extends HttpServlet implements DatabaseComminInterface{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DbSampleTASKITEMServlet() {
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
			Connection con = DatabaseComminInterface.getConnection();
			
			PreparedStatement pstmt = con.prepareStatement("select * from TASKITEM");
			ResultSet rs = pstmt.executeQuery();
			
			ArrayList<String[]> resultList = new ArrayList<>();
			
			while(rs.next() == true) {
				String[] ss = new String[6];
				ss[0]=rs.getString("pro_id");
				ss[1]=rs.getString("task_id");
				ss[2]=rs.getString("item_id");
				ss[3]=rs.getString("form_id");
				ss[4]=rs.getString("con_no");
				ss[5]=rs.getString("item_name");
				
				
				
				resultList.add(ss);
			}
			request.setAttribute("resultList", resultList);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/dbSample6.jsp");
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
