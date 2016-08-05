package main.java;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;

import main.java.com.ssa.util.FileUtils;

public class HelloJetty extends AbstractHandler {

	@Override
	public void handle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)

			throws IOException, ServletException {
		if (target.equals("MsgBox")) 
		{
			JOptionPane.showMessageDialog(null, "Blah", "InfoBox: " , JOptionPane.INFORMATION_MESSAGE);
		}
		else if (target.equals("/")){
			System.out.println("target: " + target);
			// Declare response encoding and types
			
			response.setContentType("text/html; charset=utf-8");

			// Declare response status code
			response.setStatus(HttpServletResponse.SC_OK);

			// Write back response
			String fName = ".//src//main//webapp//msg.html";
			response.getWriter().println(FileUtils.readFile(fName));

			// Inform jetty that this request has now been handled
			baseRequest.setHandled(true);
		}

	}

	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
		//server.setHandler(new HelloJetty());

        ResourceHandler resource_handler = new ResourceHandler();
        // Configure the ResourceHandler. Setting the resource base indicates where the files should be served out of.
        // In this example it is the current directory but it can be configured to anything that the jvm has access to.
        resource_handler.setDirectoriesListed(true);
        //resource_handler.setWelcomeFiles(new String[]{ "index.html" });
        resource_handler.setResourceBase("./src/main/");
        
        HandlerList handlers = new HandlerList();
        
        handlers.setHandlers(new Handler[] {  new HelloJetty(),resource_handler });
        server.setHandler(handlers);
        //server.setHandler(resource_handler);
        
		server.start();
		server.join();

	}

}