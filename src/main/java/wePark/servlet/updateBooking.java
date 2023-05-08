package wePark.servlet;

import wePark.dal.*;
import wePark.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/updateBooking")
public class updateBooking extends HttpServlet {
	
	protected UserDao userDao;
	protected BookingDao bookingDao;
	protected VehicleDao vehicleDao;
	@Override
	public void init() throws ServletException {
		userDao = UserDao.getInstance();
		bookingDao = BookingDao.getInstance();
		vehicleDao = VehicleDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/UpdateBooking.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve the username from the session
        String username = (String) req.getSession().getAttribute("username");
        int bookingId = Integer.parseInt(req.getParameter("bookingid"));

	    try {
	        // Exercise: parse the input for StatusLevel.
	        User user = userDao.getUserByUserName(username);
	        
	        int vehicleid = Integer.parseInt(req.getParameter("vehicleid"));
            
            String inputString = req.getParameter("start_time");
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            java.util.Date utilDate = inputFormat.parse(inputString);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            
            int duration_in_min = Integer.parseInt(req.getParameter("duration_in_min"));

	        bookingDao.updateBooking(new Booking(
	        		bookingId,
	        		user.getUserid(),
	        		Integer.parseInt(req.getParameter("vehicleid")),
	        		sqlDate,
	        		duration_in_min,
	        		new java.sql.Date(System.currentTimeMillis()),
	        		0
	        		));
            
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
