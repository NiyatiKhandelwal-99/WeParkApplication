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
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/updateBookingPage")
public class updateBookingPage extends HttpServlet {
	
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
        int bookingId = Integer.parseInt(req.getParameter("bookingId"));

	    try {
	        // Exercise: parse the input for StatusLevel.
	        User user = userDao.getUserByUserName(username);
	        List<Vehicle> vehicles = vehicleDao.getVehicleByUserId(user.getUserid());
	        Booking booking = bookingDao.getBookingByBookingId(bookingId);
            req.setAttribute("vehicles", vehicles);
            req.setAttribute("booking", booking);
	       
	     
	    } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	    req.getRequestDispatcher("/UpdateBooking.jsp").include(req, resp);
        //req.getRequestDispatcher("/BookingList.jsp").forward(req, resp);
    }
}
