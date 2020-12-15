package sotsuken;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

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
		String[] taskname = (String[])request.getSession().getAttribute("taskname");
		String tname = "";
		for(int i=0;i<taskname.length;i++) {
			tname = request.getParameter(taskname[i]);
			if(tname!=null) {
				break;
			}
		}
		
		System.out.print(tname);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/sotsuken/sk5-1.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String[]> taskList = (ArrayList<String[]>)request.getSession().getAttribute("taskList");
		String tname1 = "";
		String tname2 = "";
		for(int i=0;i<taskList.size();i++) {
			String[] task = taskList.get(i);
			tname1 = request.getParameter("work"+task[0]);
			tname2 = request.getParameter("rep"+task[0]);
			if(tname1!=null) {
				request.getSession().setAttribute("tname", tname1);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/sotsuken/sk5-1.jsp");
				rd.forward(request, response);
			}else if(tname2!=null){
				request.getSession().setAttribute("tname", tname2);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/sotsuken/sk9.jsp");
				rd.forward(request, response);
			}
		}
		
	}

}
