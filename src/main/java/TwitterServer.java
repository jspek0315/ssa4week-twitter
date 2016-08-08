//package main.java;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.ServletException;
//
//import java.io.IOException;
//
//import org.eclipse.jetty.server.Server;
//import org.eclipse.jetty.server.Handler;
//import org.eclipse.jetty.server.Request;
//import org.eclipse.jetty.server.handler.AbstractHandler;
//import org.eclipse.jetty.server.handler.ContextHandler;
//import org.eclipse.jetty.server.handler.HandlerCollection;
//import org.eclipse.jetty.server.handler.ResourceHandler;
//import org.eclipse.jetty.servlet.ServletContextHandler;
//import org.eclipse.jetty.servlet.ServletHolder;
//
//public class TwitterServer extends AbstractHandler {
//    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
//            throws IOException, ServletException {
//        response.setContentType("text/html;charset=utf-8");
//        response.setStatus(HttpServletResponse.SC_OK);
//        baseRequest.setHandled(true);
//        response.getWriter().println("<h1>" + getClass().getName() + "</h1>");
//    }
//
//    public static void main(String[] args) throws Exception {
//        Server server = new Server(8090);
//        server.setHandler(new TwitterServer());
//
//        // 1.Creating the resource handler
//        ResourceHandler resourceHandler = new ResourceHandler();
//        
//        // 2.Setting Resource Base
//        resourceHandler.setResourceBase("resources");
//
//        // 3.Enabling Directory Listing
//        resourceHandler.setDirectoriesListed(true);
//
//        // 4.Setting Context Source
//        ContextHandler resourceContext = new ContextHandler("/bootcamp");
//
//        // 5.Attaching Handlers
//        resourceContext.setHandler(resourceHandler);
//        
//        //server.setHandler(contextHandler);
//        
//        
//        
//         ServletContextHandler servletContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
//         servletContext.setContextPath("/servlet");
//         server.setHandler(servletContext);
//     
//         servletContext.addServlet(new ServletHolder(new TwitterServlet()),"/main.do");
//         servletContext.addServlet(new ServletHolder(new LoginServlet()),"/login.do");
//         servletContext.addServlet(new ServletHolder(new ViewFollowersServlet()),"/followers.do");
//         servletContext.addServlet(new ServletHolder(new ViewNewsFeedServlet()),"/news.do");
//         servletContext.addServlet(new ServletHolder(new ViewTweetsServlet()),"/tweets.do");
//         servletContext.addServlet(new ServletHolder(new UpdateUserServlet()),"/updateUser.do");
//         servletContext.addServlet(new ServletHolder(new UpdateUserServlet()),"/updateTweet.do");
//                
//         
//         HandlerCollection handlerCollection = new HandlerCollection();
//         handlerCollection.setHandlers(new Handler[] {servletContext, resourceContext});
//                 
//         server.setHandler(handlerCollection);
//        
//
//        server.start();
//        server.join();
//    }
//}``