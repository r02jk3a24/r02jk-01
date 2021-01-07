package jp.jc21.t.yoshizawa.authenticator;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * îFèÿÇ∑ÇÈ
 */
@WebServlet("/auth/index")
public class AuthIndexServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url=null;
		// for local test
		//url = getDevelopLoginUrl("bb16af26-015d-4dd4-8815-107e6d7b95c8");

		//for cloud server
		url = getProductionLoginUrl("5a76519d-15cb-46db-ac6e-8f9f67c5d8cb");
		
		request.setAttribute("url", url);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/hello0.jsp");
		rd.forward(request, response);
		//PrintWriter out = response.getWriter();
		//out.println(url);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/hello00.jsp");
		rd.forward(request, response);
	}
	
	private String getDevelopLoginUrl(String AzureAppIdLocal) {
		String url = null;
		if (AzureAppIdLocal != null) {
			url = "https://login.microsoftonline.com/organizations/oauth2/v2.0/authorize";
			url += "?client_id=" + AzureAppIdLocal;
			url += "&response_type=id_token";
			url += "&redirec_uri=http%3A%2F%2Flocalhost%3A8080%2Fauth%2Fmsredirect";
			url += "&response_mode=form_post";
			url += "&scope=openid%20profile";
			url += "&state=12345";
			url += "&nonce=678910";
		}
		return url;
	}

	private String getProductionLoginUrl(String AzureAppId) {
		String url = null;
		if (AzureAppId != null) {
			url = "https://login.microsoftonline.com/organizations/oauth2/v2.0/authorize";
			url += "?client_id=" + AzureAppId;
			url += "&response_type=id_token";
			url += "&redirec_uri=https%3A%2F%2Fr02jk-01.azurewebsites.net%2Fauth%2Fmsredirect";
			url += "&response_mode=form_post";
			url += "&scope=openid%20profile";
			url += "&state=12345";
			url += "&nonce=678910";
		}
		return url;
	}

}
