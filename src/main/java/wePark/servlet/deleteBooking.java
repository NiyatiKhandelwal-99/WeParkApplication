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
import wePark.dal.ParkingslotDao;
import wePark.dal.UserDao;
import wePark.model.Booking;
import wePark.model.User;


@WebServlet("/deleteBooking")
public class deleteBooking extends HttpServlet {
	
	protected BookingDao bookingDao;
	protected UserDao userDao;
	protected ParkingslotDao parkingslotDao;
	
	
	
	@Override
	public void init() throws ServletException {
		bookingDao = BookingDao.getInstance();
		userDao = UserDao.getInstance();
		parkingslotDao = ParkingslotDao.getInstance();
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        String username = (String) req.getSession().getAttribute("username");

        try {
            int bookingId = Integer.parseInt(req.getParameter("bookingId"));
            int parkignSlotId = Integer.parseInt(req.getParameter("parkingSlotId"));
           
            parkingslotDao.updateBookingId(parkignSlotId, 0);
            bookingDao.delete(new Booking(bookingId));
            
            User user = userDao.getUserByUserName(username);
            List<Booking> bookings = bookingDao.getBookingByUserId(user.getUserid());
            
            req.setAttribute("bookings", bookings);
            
            }
            
            catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
    	    } catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        
        req.getRequestDispatcher("/ViewBookings.jsp").forward(req, resp);
    }
	
}



