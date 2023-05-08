package wePark.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wePark.dal.BookingDao;
import wePark.dal.ContactDao;
import wePark.dal.ParkinglotDao;
import wePark.dal.ParkingslotDao;
import wePark.dal.RegularPassDao;
import wePark.dal.UserDao;
import wePark.model.Booking;
import wePark.model.Contact;
import wePark.model.User;



@WebServlet("/regularpass")
public class RegularPass extends HttpServlet {
	
	protected UserDao userDao;
	protected RegularPassDao regularPassDao;
	
	@Override
	public void init() throws ServletException {
		userDao = UserDao.getInstance();
		regularPassDao = RegularPassDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        //Just render the JSP.   
        req.getRequestDispatcher("/Homepage.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        
        // Retrieve the username from the session
        String username = (String) req.getSession().getAttribute("username");

	    try {
	        // Exercise: parse the input for StatusLevel.
	        User user = userDao.getUserByUserName(username);
	        List<wePark.model.RegularPass> regularPasses = regularPassDao.getRegularPassByUserId(user.getUserid());
	        
	    	
	     // Set the Contact object as a request attribute
	        req.setAttribute("regularPasses", regularPasses);
	    } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	  
        
        req.getRequestDispatcher("/BuyRegularPass.jsp").forward(req, resp);
    }

}



