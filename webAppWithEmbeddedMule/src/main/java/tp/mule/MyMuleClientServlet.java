package tp.mule;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mule.api.MuleContext;
import org.mule.api.MuleMessage;
import org.mule.api.client.MuleClient;
import org.mule.api.config.MuleProperties;
import org.mule.client.DefaultLocalMuleClient;

/**
 * Servlet implementation class MyMuleClientServlet
 */
public class MyMuleClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyMuleClientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			ServletContext servletContext = this.getServletContext();
			MuleContext muleContext = (MuleContext)servletContext.getAttribute(MuleProperties.MULE_CONTEXT_PROPERTY);
			MuleClient client = new DefaultLocalMuleClient(muleContext);
			Double[] soapArgs = new Double[]{5.0, 6.0};
			MuleMessage msgResult = client.send
			        ("cxf:http://localhost:8081/tx-java-mule/StdCalculateurPort?method=add", soapArgs, null);
			out.println("<html><body>");
			System.out.println("result:"+msgResult.getPayloadAsString());
			out.println("result:"+msgResult.getPayloadAsString());
			out.println("</body></html>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
