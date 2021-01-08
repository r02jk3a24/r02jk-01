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
@WebServlet("/sk8")
public class sk8Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sk8Servlet() {
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
			
			String pro_id = "1";
			String task_id = "1";
			
			request.setAttribute("pro_name", "ƒeƒXƒg‰Û‘è");
			
			PreparedStatement pstmt = con.prepareStatement("select task_name,task_partno,item_no from TASK where pro_id = ? and task_id = ?");
			pstmt.setString(1, pro_id);
			pstmt.setString(2, task_id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String task_name  = rs.getString("task_name");
			int task_partno = rs.getInt("task_partno");
			int item_no = rs.getInt("item_no");
			
			request.setAttribute("task_name", task_name);
			request.setAttribute("task_partno", task_partno);
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
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/sotsuken/sk8.jsp");
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
		ArrayList<String[]> taskList = (ArrayList<String[]>)request.getSession().getAttribute("taskList");
		String tname1 = "";
		String tname2 = "";
		try {
			Connection con = DatabaseComminInterface.getConnection();
			for(int i=0;i<taskList.size();i++) {
				String[] task = taskList.get(i);
				tname1 = request.getParameter("work"+task[0]);
				tname2 = request.getParameter("rep"+task[0]);
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
				}else {
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
