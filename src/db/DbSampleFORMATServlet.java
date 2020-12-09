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
@WebServlet("/dbSample12")
public class DbSampleFORMATServlet extends HttpServlet implements DatabaseComminInterface{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DbSampleFORMATServlet() {
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
			
			PreparedStatement pstmt = con.prepareStatement("select * from FORMAT");
			ResultSet rs = pstmt.executeQuery();
			
			ArrayList<String[]> resultList = new ArrayList<>();
			
			while(rs.next() == true) {
				String[] ss = new String[2];
				ss[0]=rs.getString("form_id");
				ss[1]=rs.getString("form_name");
				
				
				resultList.add(ss);
			}
			request.setAttribute("resultList", resultList);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/dbSample2.jsp");
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
