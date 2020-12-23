package sotsuken;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class sample01
 */
@WebServlet("/sk5_2")
public class sk52Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sk52Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] membervalues = request.getParameterValues("membervalues");
		ArrayList<String[]> username = (ArrayList<String[]>)request.getSession().getAttribute("username");
		ArrayList<String[]> workmember = new ArrayList<String[]>();
		for(int i=0;i<membervalues.length;i++) {
			workmember.add(username.get(Integer.parseInt(membervalues[i])));
		}
		request.getSession().setAttribute("workmember", workmember);
		request.getSession().setAttribute("itemno", Integer.parseInt(request.getParameter("itemno")));
		request.getSession().setAttribute("wname", request.getParameter("wname"));
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/sotsuken/sk5-2.jsp");
		rd.forward(request, response);
	}

}
