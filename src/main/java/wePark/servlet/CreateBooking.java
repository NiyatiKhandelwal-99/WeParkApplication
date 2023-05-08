package wePark.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wePark.dal.BookingDao;
import wePark.dal.ParkinglotDao;
import wePark.dal.ParkingslotDao;
import wePark.dal.UserDao;
import wePark.model.Booking;
import wePark.model.User;



@WebServlet("/createBooking")
public class CreateBooking extends HttpServlet {
	
	protected BookingDao bookingDao;
	protected UserDao userDao;
	protected ParkinglotDao parkinglotDao;
	protected ParkingslotDao parkingslotDao;
	

	@Override
	public void init() throws ServletException {
		bookingDao = BookingDao.getInstance();
		userDao = UserDao.getInstance();
		parkinglotDao = ParkinglotDao.getInstance();
		parkingslotDao = ParkingslotDao.getInstance();
	}
	
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        req.getRequestDispatcher("/FindParkinglots.jsp").forward(req, resp);
		
	}
	
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        int parkingLotId = Integer.parseInt(req.getParameter("parkingLotId"));
        try {
            String username = (String) req.getSession().getAttribute("username");
            User user = userDao.getUserByUserName(username);
            
            
            int vehicleid = Integer.parseInt(req.getParameter("vehicleid"));
            
            String inputString = req.getParameter("start_time");
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            java.util.Date utilDate = inputFormat.parse(inputString);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            
            int duration_in_min = Integer.parseInt(req.getParameter("duration_in_min"));
            
	        int parkingSlot = parkinglotDao.getAvailableParkingSlotByLotId(parkingLotId);
	        
	        Booking booking = new Booking(user.getUserid(),vehicleid,  sqlDate, duration_in_min, new Date(System.currentTimeMillis()), parkingSlot);
	        booking = bookingDao.create(booking);
	        parkingslotDao.updateBookingId(parkingSlot, booking.getBooking_id());

            }
            
            catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
    	    } catch (Exception e) {
    			
    			e.printStackTrace();
    		}
        
        req.getRequestDispatcher("/Booking.jsp").forward(req, resp);
    }

}



