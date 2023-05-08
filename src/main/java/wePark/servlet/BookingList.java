package wePark.servlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wePark.dal.BookingDao;
import wePark.dal.UserDao;
import wePark.model.*;
import wePark.model.User;

@WebServlet("/bookingList")
public class BookingList extends HttpServlet {
	protected BookingDao bookingDao;
	protected UserDao userDao;
	
	@Override
	public void init() throws ServletException {
		bookingDao = BookingDao.getInstance();
		userDao = UserDao.getInstance();
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
	        List<Booking> bookings = bookingDao.getBookingByUserId(user.getUserid());

	        req.setAttribute("bookings", bookings);
	    } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        req.getRequestDispatcher("/ViewBookings.jsp").forward(req, resp);
    }
	
	
}