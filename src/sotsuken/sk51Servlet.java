package sotsuken;

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

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import db.DatabaseComminInterface;

/**
 * Servlet implementation class sample01
 */
@WebServlet("/sk5_1")
public class sk51Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sk51Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		ArrayList<String[]> taskList = (ArrayList<String[]>)request.getSession().getAttribute("taskList");
		String tname1 = "";
		String tname2 = "";
		String tname3 = "";
		try {
			Connection con = DatabaseComminInterface.getConnection();
			for(int i=0;i<taskList.size();i++) {
				String[] task = taskList.get(i);
				tname1 = request.getParameter("work"+task[0]);
				tname2 = request.getParameter("rep"+task[0]);
				tname3 = request.getParameter("list"+task[0]);
				if(tname1!=null) {
					PreparedStatement pstmt1 = con.prepareStatement("select USERX.user_id,user_name from USERX join PROMEN on(USERX.user_id = PROMEN.user_id) where PROMEN.pro_id = ?");
					pstmt1.setString(1, tname1);
					ResultSet rs2 = pstmt1.executeQuery();
					
					ArrayList<String[]> username = new ArrayList<>();
					
					while(rs2.next() == true) {
						String[] ss = new String[2];
						ss[0]=rs2.getString("user_id");
						ss[1]=rs2.getString("user_name");
						
						username.add(ss);
					}
					request.getSession().setAttribute("pro_id", task[0]);
					request.getSession().setAttribute("username", username);
					request.getSession().setAttribute("tname", task[1]);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/sotsuken/sk5-1.jsp");
					rd.forward(request, response);
				}else if(tname2!=null){
					RequestDispatcher rd = request.getRequestDispatcher("/Gamen9");
					rd.forward(request, response);
				}else if(tname3!=null){
					request.setAttribute("pro_id", tname3);
					RequestDispatcher rd = request.getRequestDispatcher("/Gamen6");
					rd.forward(request, response);
				}
			}
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
