package main.java;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import main.java.com.ssa.util.FileUtils;

public class HelloJetty extends AbstractHandler {
	public static long MSG_ID = 0;
	@Override
	public void handle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)

			throws IOException, ServletException {
		if (target.equals("/MsgBox")) 
		{
			System.out.println("MsgBox Handle Entered");
			
			response.setContentType("text/html; charset=utf-8");

			// Declare response status code
			response.setStatus(HttpServletResponse.SC_OK);

			// Write back response
			String fName = ".//src//main//webapp//msg.html";
			response.getWriter().println(FileUtils.readFile(fName));

			// Inform jetty that this request has now been handled
			baseRequest.setHandled(true);
		}
		else if (target.equals("/")){
			
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
		else if(target.equals("/SaveMSG"))
		{
			//System.out.println(request.getQueryString());
			String userName = request.getParameter("User_Name");
			ArrayList<String> newMsg = new ArrayList<>();
			
			//Extract old messages, and add to ArrayList
			JSONParser parser = new JSONParser();
			 
	        try {
	 
	            Object obj = parser.parse(new FileReader(".//src//main//resources//JSON_File1.txt"));
	 
	            JSONObject jsonObject = (JSONObject) obj;
	            JSONArray messageList = (JSONArray) jsonObject.get("Message_List");
	            for (Object object : messageList) {
	            	newMsg.add(object.toString().substring(4).trim());
				}
	        }
	        catch(Exception e)
	        {
	        	System.out.println("Could not write to JSON file");
	        }
			
			newMsg.add(request.getParameter("Msg"));
			
			//long newMsgID = MSG_ID++;
			
			generateJSON(userName, newMsg);
			
			response.setContentType("text/html; charset=utf-8");

			// Declare response status code
			response.setStatus(HttpServletResponse.SC_OK);

			// Write back response
			String fName = ".//src//main//webapp//msg.html";
			response.getWriter().println(FileUtils.readFile(fName));

			// Inform jetty that this request has now been handled
			baseRequest.setHandled(true);
		}
		else
		{
			response.setContentType("text/html; charset=utf-8");

			// Declare response status code
			response.setStatus(HttpServletResponse.SC_OK);

			// Write back response
			//String fName = ".//src//main//webapp//msg.html";
			//response.getWriter().println(FileUtils.readFile(fName));

			// Inform jetty that this request has now been handled
			//baseRequest.setHandled(true);
		}
		System.out.println("target: " + target);
		System.out.println("Http Servlet Request: " + request);
	}

	private void generateJSON(String userName, ArrayList<String> newMsgList) {
		JSONObject obj = new JSONObject();
		obj.put("User_Name", userName);

		JSONArray msgList = new JSONArray();
		for (String msg : newMsgList) {
			msgList.add("Msg:" + msg);

		}
		obj.put("Message_List", msgList);

		// try-with-resources statement based on post comment below :)
		try (FileWriter file = new FileWriter(
				".//src//main//resources//JSON_File1.txt")) {
			file.write(obj.toJSONString());
			System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("\nJSON Object: " + obj);
			file.close();
		} catch (Exception e) {
			System.out.println("Could write to file.");
		}
	}

	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
		// server.setHandler(new HelloJetty());

		ResourceHandler resource_handler = new ResourceHandler();
		// Configure the ResourceHandler. Setting the resource base indicates
		// where the files should be served out of.
		// In this example it is the current directory but it can be configured
		// to anything that the jvm has access to.
		resource_handler.setDirectoriesListed(true);
		// resource_handler.setWelcomeFiles(new String[]{ "index.html" });
		resource_handler.setResourceBase("./src/main/");

		HandlerList handlers = new HandlerList();

		handlers.setHandlers(new Handler[]{new HelloJetty(), resource_handler});
		server.setHandler(handlers);
		// server.setHandler(resource_handler);

		server.start();
		server.join();

	}

}