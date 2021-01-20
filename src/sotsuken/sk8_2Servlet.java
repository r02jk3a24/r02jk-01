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
@WebServlet("/sk8_2")
public class sk8_2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sk8_2Servlet() {
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
			
			String pro_id = "2";
			String task_id = "9";
			
			request.setAttribute("pro_name", "test2");
			
			PreparedStatement pstmt = con.prepareStatement("select task_name,item_no from TASK where pro_id = ? and task_id = ?");
			pstmt.setString(1, pro_id);
			pstmt.setString(2, task_id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String task_name  = rs.getString("task_name");
			int task_partno = rs.getInt("task_partno");
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
		String pro_id = (String)request.getSession().getAttribute("pro_id");
		String task_id = (String)request.getSession().getAttribute("task_id");
		String user_id = (String)request.getSession().getAttribute("userid");
		int item_no = (int)request.getAttribute("item_no");
		ArrayList<String[]> taskitem = (ArrayList<String[]>) request.getAttribute("taskitem");
		
		
		
		
		
		try {
			Connection con = DatabaseComminInterface.getConnection();
			PreparedStatement pstmt1 = con.prepareStatement("insert into REPO(pro_id,task_id,user_id,repo_date) values(?,?,?,GETDATE())");
			pstmt1.setString(1, pro_id);
			pstmt1.setString(2, task_id);
			pstmt1.setString(3, user_id);
			pstmt1.executeUpdate();
			
			PreparedStatement pstmts1 = con.prepareStatement("select max(repo_date) as date from REPO where pro_id = ? and task_id = ? and uer_id = ?");
			pstmts1.setString(1, pro_id);
			pstmts1.setString(2, task_id);
			pstmts1.setString(3, user_id);
			ResultSet rs1 = pstmts1.executeQuery();
			rs1.next();
			String repo_date = rs1.getString("date");
			
			String status = "”ñ‘I‘ð";
			String repocon1 = "";
			String repocon2 = "";
			String repocon3 = "";
			
			for(int i=0;i<item_no;i++){
				String[] task = taskitem.get(i);
				repocon1 = request.getParameter("radio"+task[0]);
				repocon2 = request.getParameter("checkbox"+task[0]);
				repocon3 = request.getParameter("text"+task[0]);
				
				for(int j=0;j<Integer.parseInt(task[2]);j++) {
					if(repocon1!=null&&Integer.parseInt(repocon1)==j) {
						status = "‘I‘ð";
					}else if(repocon2!=null&&Integer.parseInt(repocon2)==j){
						status = "‘I‘ð";
					}else if(repocon3!=null){
						status = repocon3;
					}
					insertRepode(out,con,pro_id,task_id,user_id,repo_date,(i+1),(j+1),status);
				}
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/sotsuken/sk8_2.jsp");
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
	
	private void insertRepode(PrintWriter out, Connection con,String pro_id,String task_id,String user_id,String repo_date,int item_id,int con_id,String repo_con) throws SQLException {
		PreparedStatement pstmt2 = con.prepareStatement("insert into REPODE(pro_id,task_id,user_id,repo_date,item_id,con_id,repo_con)) values(?,?,?,?,?,?,?)");
		pstmt2.setString(1, pro_id);
		pstmt2.setString(2, task_id);
		pstmt2.setString(3, user_id);
		pstmt2.setString(4, repo_date);
		pstmt2.setInt(5, item_id);
		pstmt2.setInt(6, con_id);
		pstmt2.setString(7, repo_con);
		pstmt2.executeUpdate();
		
	}

}
