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
@WebServlet("/sk10")
public class sk10Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sk10Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			Connection con = DatabaseComminInterface.getConnection();
			
			String pro_id = "29";
			String task_id = "11";
			String user_id = "24";
			String repo_date = "2021-01-20 06:04:39.253";
			
			request.setAttribute("pro_name", "”­•\•]‰¿");
			
			PreparedStatement pstmt0 = con.prepareStatement("select user_name from USERX where user_id = ?");
			pstmt0.setString(1, user_id);
			ResultSet rs0 = pstmt0.executeQuery();
			rs0.next();
			String user  = rs0.getString("user_name");
			request.setAttribute("user", user);
			
			PreparedStatement pstmt = con.prepareStatement("select task_name,item_no from TASK where pro_id = ? and task_id = ?");
			pstmt.setString(1, pro_id);
			pstmt.setString(2, task_id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String task_name  = rs.getString("task_name");
			int item_no = rs.getInt("item_no");
			
			request.setAttribute("task_name", task_name);
			request.setAttribute("item_no", item_no);
			
			PreparedStatement pstmt2 = con.prepareStatement("select user_name from TASKMEN join USERX on(USERX.user_id = TASKMEN.user_id) where pro_id = ? and task_id = ?");
			pstmt2.setString(1, pro_id);
			pstmt2.setString(2, task_id);
			ResultSet rs2 = pstmt2.executeQuery();
			ArrayList<String> user_name = new ArrayList<>();
			while(rs2.next() == true) {
				user_name.add(rs2.getString("user_name"));
			}
			request.setAttribute("user_name", user_name);
			
			PreparedStatement pstmt3 = con.prepareStatement("select item_id,form_id,con_no,item_name from TASKITEM where pro_id = ? and task_id = ? order by item_id");
			pstmt3.setString(1, pro_id);
			pstmt3.setString(2, task_id);
			ResultSet rs3 = pstmt3.executeQuery();
			ArrayList<String[]> taskitem = new ArrayList<>();
			while(rs3.next() == true) {
				String[] ss = new String[4];
				ss[0]=rs3.getString("item_id");
				ss[1]=rs3.getString("form_id");
				ss[2]=rs3.getString("con_no");
				ss[3]=rs3.getString("item_name");
				
				taskitem.add(ss);
			}
			request.setAttribute("taskitem", taskitem);
			
			PreparedStatement pstmt4 = con.prepareStatement("select con_name from TASKITEMDE where pro_id = ? and task_id = ? order by item_id,con_id");
			pstmt4.setString(1, pro_id);
			pstmt4.setString(2, task_id);
			ResultSet rs4 = pstmt4.executeQuery();
			ArrayList<String> con_name = new ArrayList<>();
			while(rs4.next() == true) {
				con_name.add(rs4.getString("con_name"));
			}
			request.setAttribute("con_name", con_name);
			
			PreparedStatement pstmt5 = con.prepareStatement("select item_id,con_id,repo_con from REPODE where pro_id = ? and task_id = ? and user_id = ? and repo_date = ?");
			pstmt5.setString(1, pro_id);
			pstmt5.setString(2, task_id);
			pstmt5.setString(3, user_id);
			pstmt5.setString(4, repo_date);
			ResultSet rs5 = pstmt5.executeQuery();
			ArrayList<String[]> repo = new ArrayList<>();
			while(rs5.next() == true) {
				String[] ss = new String[3];
				ss[0]=rs5.getString("item_id");
				ss[1]=rs5.getString("con_id");
				ss[2]=rs5.getString("repo_con");
				repo.add(ss);
			}
			request.setAttribute("repo", repo);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/sotsuken/sk10.jsp");
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			Connection con = DatabaseComminInterface.getConnection();
			
			ArrayList<String> resultList = (ArrayList<String>) request.getSession().getAttribute("resultList");
			String[] repode = new String[4];
			for(int i=0;i<resultList.size();i++) {
				repode = request.getParameterValues("repode"+i);
				if(repode!=null) {
					break;
				}
			}
			
			String pro_id = repode[0];
			String task_id = repode[1];
			String user_id = repode[2];
			String repo_date = repode[3];
			
			PreparedStatement pstmt01 = con.prepareStatement("select pro_name from PRO where pro_id = ?");
			pstmt01.setString(1, pro_id);
			ResultSet rs01 = pstmt01.executeQuery();
			rs01.next();
			String pro_name  = rs01.getString("pro_name");
			request.setAttribute("pro_name", pro_name);
			
			PreparedStatement pstmt0 = con.prepareStatement("select user_name from USERX where user_id = ?");
			pstmt0.setString(1, user_id);
			ResultSet rs0 = pstmt0.executeQuery();
			rs0.next();
			String user  = rs0.getString("user_name");
			request.setAttribute("user", user);
			
			PreparedStatement pstmt = con.prepareStatement("select task_name,item_no from TASK where pro_id = ? and task_id = ?");
			pstmt.setString(1, pro_id);
			pstmt.setString(2, task_id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String task_name  = rs.getString("task_name");
			int item_no = rs.getInt("item_no");
			
			request.setAttribute("task_name", task_name);
			request.setAttribute("item_no", item_no);
			
			PreparedStatement pstmt2 = con.prepareStatement("select user_name from TASKMEN join USERX on(USERX.user_id = TASKMEN.user_id) where pro_id = ? and task_id = ?");
			pstmt2.setString(1, pro_id);
			pstmt2.setString(2, task_id);
			ResultSet rs2 = pstmt2.executeQuery();
			ArrayList<String> user_name = new ArrayList<>();
			while(rs2.next() == true) {
				user_name.add(rs2.getString("user_name"));
			}
			request.setAttribute("user_name", user_name);
			
			PreparedStatement pstmt3 = con.prepareStatement("select item_id,form_id,con_no,item_name from TASKITEM where pro_id = ? and task_id = ? order by item_id");
			pstmt3.setString(1, pro_id);
			pstmt3.setString(2, task_id);
			ResultSet rs3 = pstmt3.executeQuery();
			ArrayList<String[]> taskitem = new ArrayList<>();
			while(rs3.next() == true) {
				String[] ss = new String[4];
				ss[0]=rs3.getString("item_id");
				ss[1]=rs3.getString("form_id");
				ss[2]=rs3.getString("con_no");
				ss[3]=rs3.getString("item_name");
				
				taskitem.add(ss);
			}
			request.setAttribute("taskitem", taskitem);
			
			PreparedStatement pstmt4 = con.prepareStatement("select con_name from TASKITEMDE where pro_id = ? and task_id = ? order by item_id,con_id");
			pstmt4.setString(1, pro_id);
			pstmt4.setString(2, task_id);
			ResultSet rs4 = pstmt4.executeQuery();
			ArrayList<String> con_name = new ArrayList<>();
			while(rs4.next() == true) {
				con_name.add(rs4.getString("con_name"));
			}
			request.setAttribute("con_name", con_name);
			
			PreparedStatement pstmt5 = con.prepareStatement("select item_id,con_id,repo_con from REPODE where pro_id = ? and task_id = ? and user_id = ? and repo_date = ?");
			pstmt5.setString(1, pro_id);
			pstmt5.setString(2, task_id);
			pstmt5.setString(3, user_id);
			pstmt5.setString(4, repo_date);
			ResultSet rs5 = pstmt5.executeQuery();
			ArrayList<String[]> repo = new ArrayList<>();
			while(rs5.next() == true) {
				String[] ss = new String[3];
				ss[0]=rs5.getString("item_id");
				ss[1]=rs5.getString("con_id");
				ss[2]=rs5.getString("repo_con");
				repo.add(ss);
			}
			request.setAttribute("repo", repo);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/sotsuken/sk10.jsp");
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
