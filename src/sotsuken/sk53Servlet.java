package sotsuken;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class sample01
 */
@WebServlet("/sk5_3")
public class sk53Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sk53Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("iname", request.getParameterValues("iname"));
		request.getSession().setAttribute("choiceno", request.getParameterValues("choiceno"));
		int itemno = (int)request.getSession().getAttribute("itemno");
		String[] formatno = new String[itemno];
		String[] format = new String[itemno];
		for(int i=1;i<=itemno;i++) {
			formatno[i-1]="format"+i;
			format[i-1]=request.getParameter(formatno[i-1]);
		}
		request.getSession().setAttribute("format", format);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/sotsuken/sk5-3.jsp");
		rd.forward(request, response);
	}

}
