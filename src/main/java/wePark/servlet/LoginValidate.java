package wePark.servlet;

import wePark.dal.*;
import wePark.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class LoginValidate extends HttpServlet {
	
	protected UserDao userDao;
	
	@Override
	public void init() throws ServletException {
		userDao = UserDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/Login.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        } else {
        	// check the User.
        	String password = req.getParameter("password");

	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	User user = userDao.getUserByUserName(userName);
	        	if (user != null && user.getPassword().equals(password)) {
		        	messages.put("success", "Successfully login ");
		        	// Set the username as a session attribute
	                req.getSession().setAttribute("username", userName);
		        	resp.sendRedirect("Homepage.jsp");
	        	} else {
	        		messages.put("fail", "Wrong username or password, please try again.");
	        		req.getRequestDispatcher("/Login.jsp").forward(req, resp);
	        	}
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        
    }
}
