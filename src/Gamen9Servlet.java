

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
 * Servlet implementation class Gamen9Servlet
 */
@WebServlet("/Gamen9")
public class Gamen9Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Gamen9Servlet() {
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
			/*String PRO_id = (String)request.getAttribute("PRO_id");*/
			request.setCharacterEncoding("UTF-8");
			Connection con = DatabaseComminInterface.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT　distinct REPO.repo_date as repodate　, PRO.pro_name as pname ,  USERX.user_name as uname ,TASK.task_name as tname , USERX.user_id as uid ,REPO.pro_id as pid, REPO.task_id as tid  FROM PRO　"
					+ "JOIN　REPO on PRO.pro_id  = REPO.pro_id "
					+ "JOIN PROMEN on PRO.pro_id　= PROMEN.pro_id "
					+ "JOIN TASK on PRO.pro_id　= TASK.pro_id "
					+ "JOIN USERX on REPO.user_id　= USERX.user_id " /*userid 全表示*/
					+ "where REPO.pro_id = ? order by USERX.user_id asc");
			
			/*参加者の報告を表示するのに必要な情報を抽出するsql*/ 
			
			pstmt.setString(1, "1");
			ResultSet rs = pstmt.executeQuery();
			ArrayList<String[]> resultList = new ArrayList<>();
			
			while(rs.next() == true) {
				String[] ss = new String[7];
				ss[0]=rs.getString("pname");
				ss[1]=rs.getString("repodate");
				ss[2]=rs.getString("uname");
				ss[3]=rs.getString("tname");
				ss[4]=rs.getString("uid");
				ss[5]=rs.getString("pid");
				ss[6]=rs.getString("tid");
				resultList.add(ss);
			}
			request.setAttribute("resultList", resultList);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/gamen9.jsp");
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
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			/*String PRO_id = (String)request.getAttribute("PRO_id");*/
			request.setCharacterEncoding("UTF-8");
			Connection con = DatabaseComminInterface.getConnection();
			
			String pro_id = (String) request.getAttribute("pro_id");
			
			PreparedStatement pstmt = con.prepareStatement("SELECT　pro_name from PRO where pro_id = ?");
			
			pstmt.setString(1, pro_id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String pname = rs.getString("pro_name");
			request.setAttribute("pro_name", pname);
			request.setAttribute("pro_id", pro_id);
			
			PreparedStatement pstmt2 = con.prepareStatement("SELECT　REPO.task_id as tid,task_name,REPO.user_id as uid,user_name,repo_date from REPO "
					+ "join TASK on REPO.task_id = TASK.task_id "
					+ "join USERX on REPO.user_id = USERX.user_id "
					+ "where REPO.pro_id = ? order by REPO.task_id,repo_date");
			
			pstmt2.setString(1, pro_id);
			ResultSet rs2 = pstmt2.executeQuery();
			
			ArrayList<String[]> resultList = new ArrayList<>();
			
			while(rs2.next() == true) {
				String[] ss = new String[5];
				ss[0]=rs2.getString("tid");
				ss[1]=rs2.getString("task_name");
				ss[2]=rs2.getString("uid");
				ss[3]=rs2.getString("user_name");
				ss[4]=rs2.getString("repo_date");
				resultList.add(ss);
			}
			request.getSession().setAttribute("resultList", resultList);
			
			PreparedStatement pstmt3 = con.prepareStatement("SELECT　task_name from TASK where pro_id = ? order by task_id");
			
			pstmt3.setString(1, pro_id);
			ResultSet rs3 = pstmt3.executeQuery();
			ArrayList<String> tname = new ArrayList<>();
			while(rs3.next()==true) {
				tname.add(rs3.getString("task_name"));
			}
			request.setAttribute("task_name", tname);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/sotsuken/sk9.jsp");
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
