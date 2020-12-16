

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
 * Servlet implementation class Gamen6Servlet
 */
@WebServlet("/Gamen6")
public class Gamen6Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Gamen6Servlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String[]> list = new ArrayList<String[]>();
		int a=0;
		while(a<6) {
			String[] s=new String[2];
			s[0]="TaskName"+a;
			s[1]="WorkName"+a;
			list.add(s);
			a++;
		}
		String Wname="hoge";
		request.setAttribute("name", Wname);
		request.setAttribute("result", list);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/gamen6.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			String TUser_id = request.getParameter("TUserid");
			
			Connection con = DatabaseComminInterface.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT　TASK.task_name as Tname , PRO.pro_name as Pname FROM TASKMEN　"
					+ "JOIN　USERX on TASKMEN.user_id  = USERX.user_id "
					+ "JOIN PRO on TASKMEN.pro_id　= PRO.pro_id "
					+ "JOIN TASK on TASKMEN.task_id　= TASK.task_id "
					+ "where USER.user_id = ? ");
			
			/*参加者の報告を表示するのに必要な情報を抽出するsql*/
			
			pstmt.setString(1, TUser_id);
			ResultSet rs = pstmt.executeQuery();
			ArrayList<String[]> resultList = new ArrayList<>();
			
			while(rs.next() == true) {
				String[] ss = new String[2];
				ss[0]=rs.getString("Tname");
				ss[1]=rs.getString("Pname");
				resultList.add(ss);
			}
			request.setAttribute("resultList", resultList);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/gamen6.jsp");
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
