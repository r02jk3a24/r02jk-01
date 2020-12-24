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

import db.DatabaseComminInterface;

/**
 * Servlet implementation class sample01
 */
@WebServlet("/sk5_5")
public class sk55Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sk55Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String pro_id = (String)request.getSession().getAttribute("pro_id");
		String taskname = (String)request.getSession().getAttribute("wname");
		int taskpart_no = (int)request.getSession().getAttribute("itemno");
		ArrayList<String[]> workmember = (ArrayList<String[]>)request.getSession().getAttribute("workmember");
		ArrayList<String[]> taskList = (ArrayList<String[]>)request.getSession().getAttribute("taskList");
		String[] form_id = (String[])request.getSession().getAttribute("format");
		String[] con_no = (String[])request.getSession().getAttribute("choiceno");
		String[] item_name = (String[])request.getSession().getAttribute("iname");
		String[] contents = (String[])request.getSession().getAttribute("contents");
		
		try {
			Connection con = DatabaseComminInterface.getConnection();
			PreparedStatement pstmt1 = con.prepareStatement("insert into TASK(pro_id,task_name,task_partno,item_no) values(?,?,?,?)");
			pstmt1.setString(1, pro_id);
			pstmt1.setString(2, taskname);
			pstmt1.setInt(3, workmember.size());
			pstmt1.setInt(4, taskpart_no);
			pstmt1.executeUpdate();
			
			PreparedStatement pstmts1 = con.prepareStatement("select task_id from TASK where pro_id = ? and task_name = ?");
			pstmts1.setString(1, pro_id);
			pstmts1.setString(2, taskname);
			ResultSet rs1 = pstmts1.executeQuery();
			rs1.next();
			String task_id = rs1.getString("task_id");
			
			for(int i=0;i<workmember.size();i++) {
				String[] member = workmember.get(i);
				insertTaskmen(out,con,pro_id,task_id,member[0]);
			}
				
			for(int i=0;i<taskpart_no;i++) {
				insertTaskitem(out,con,pro_id,task_id,form_id[i],con_no[i],item_name[i]);
			}
			
			PreparedStatement pstmts2 = con.prepareStatement("select min(item_id)as item from TASKITEM where pro_id = ? and task_name = ?");
			pstmts2.setString(1, pro_id);
			pstmts2.setString(2, taskname);
			ResultSet rs2 = pstmts2.executeQuery();
			rs2.next();
			int item_id = rs2.getInt("item");
			int count = 0;
			for(int i=0;i<taskpart_no;i++) {
				
				for(int j=0;j<Integer.parseInt(con_no[i]);j++) {
					int id = item_id+i;
					if(form_id[i].equals("3")) {
						insertTaskitemde(out,con,pro_id,task_id,id,null);
					}else {
						insertTaskitemde(out,con,pro_id,task_id,id,contents[count]);
						count+=1;
					}
				}
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/sotsuken/sk5-5.jsp");
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
	
	private void insertTaskmen(PrintWriter out, Connection con,String pro_id,String task_id,String user_id) throws SQLException {
		PreparedStatement pstmt2 = con.prepareStatement("insert into TASKMEN(pro_id,task_id,user_id) values(?,?,?)");
		pstmt2.setString(1, pro_id);
		pstmt2.setString(2, task_id);
		pstmt2.setString(3, user_id);
		pstmt2.executeUpdate();
		
	}
	
	private void insertTaskitem(PrintWriter out, Connection con,String pro_id,String task_id,String form_id,String con_no,String item_name) throws SQLException {
		PreparedStatement pstmt3 = con.prepareStatement("insert into TASKITEM(pro_id,task_id,form_id,con_no,item_name) values(?,?,?,?,?)");
		pstmt3.setString(1, pro_id);
		pstmt3.setString(2, task_id);
		pstmt3.setString(3, form_id);
		pstmt3.setString(4, con_no);
		pstmt3.setString(5, item_name);
		pstmt3.executeUpdate();
		
	}
	
	private void insertTaskitemde(PrintWriter out, Connection con,String pro_id,String task_id,int item_id,String con_name) throws SQLException {
		PreparedStatement pstmt4 = con.prepareStatement("insert into TASKITEMDE(pro_id,task_id,item_id,con_name) values(?,?,?,?)");
		pstmt4.setString(1, pro_id);
		pstmt4.setString(2, task_id);
		pstmt4.setInt(3, item_id);
		pstmt4.setString(4, con_name);
		pstmt4.executeUpdate();
		
	}

}
