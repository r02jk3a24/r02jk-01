
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			Connection con = DatabaseComminInterface.getConnection();

			String user_id = (String) request.getSession().getAttribute("userid");
			String pro_id = (String) request.getAttribute("pro_id");

			PreparedStatement pstmt0 = con.prepareStatement(
					"select pro_id,pro_name,TASK.task_id,task_name from PRO join TASK on PRO.pro_id = TASK.pro_id "
							+ "join TASKMEN on PRO.pro_id = TASKMEN.pro_id and TASK.task_id = TASKMEN.task_id where user_id = ? and REPO.pro_id =?");
			pstmt0.setString(1, user_id);
			pstmt0.setString(2, pro_id);
			ResultSet rs0 = pstmt0.executeQuery();
			ArrayList<String[]> ptaskList = new ArrayList<>();
			while (rs0.next() == true) {
				String[] ss = new String[4];
				ss[0] = rs0.getString("pro_id");
				ss[1] = rs0.getString("pro_name");
				ss[2] = rs0.getString("task_id");
				ss[3] = rs0.getString("task_name");
				ptaskList.add(ss);
			}
			request.getSession().setAttribute("ptaskList", ptaskList);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/sotsuken/sk6.jsp");
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/*
	 * protected void doPost(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException {
	 * 
	 * response.setContentType("text/html; charset=UTF-8"); PrintWriter out =
	 * response.getWriter(); try { request.setCharacterEncoding("UTF-8"); String
	 * TUser_id = request.getParameter("TUserid");
	 * 
	 * Connection con = DatabaseComminInterface.getConnection(); PreparedStatement
	 * pstmt = con.
	 * prepareStatement("SELECT　TASK.task_name as Tname , PRO.pro_name as Pname FROM TASKMEN　"
	 * + "JOIN　USERX on TASKMEN.user_id  = USERX.user_id " +
	 * "JOIN PRO on TASKMEN.pro_id　= PRO.pro_id " +
	 * "JOIN TASK on TASKMEN.task_id　= TASK.task_id " +
	 * "where TASKMEN.user_id = ? ");
	 * 
	 * 参加者の報告を表示するのに必要な情報を抽出するsql
	 * 
	 * pstmt.setString(1, TUser_id); ResultSet rs = pstmt.executeQuery();
	 * ArrayList<String[]> resultList = new ArrayList<>();
	 * 
	 * while(rs.next() == true) { String[] ss = new String[2];
	 * ss[0]=rs.getString("Tname"); ss[1]=rs.getString("Pname"); resultList.add(ss);
	 * } request.setAttribute("resultList", resultList); RequestDispatcher rd =
	 * request.getRequestDispatcher("/WEB-INF/jsp/gamen6.jsp"); rd.forward(request,
	 * response);
	 * 
	 * 
	 * } catch (ClassNotFoundException e) { e.printStackTrace(out);
	 * e.printStackTrace(System.out); } catch (SQLException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(out);
	 * e.printStackTrace(System.out); } }
	 */

}
