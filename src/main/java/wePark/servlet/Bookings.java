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

import wePark.dal.UserDao;
import wePark.dal.VehicleDao;
import wePark.model.User;
import wePark.model.Vehicle;


@WebServlet("/Bookings")
public class Bookings extends HttpServlet {
	
	protected UserDao userDao;
	protected VehicleDao vehicleDao;
	
	
	@Override
	public void init() throws ServletException {
		userDao = UserDao.getInstance();
		vehicleDao = VehicleDao.getInstance();
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

        // Retrieve the username from the session
        
        int parkingLotId = Integer.parseInt(req.getParameter("parkingLotId"));
        try {
            String username = (String) req.getSession().getAttribute("username");
            User user = userDao.getUserByUserName(username);
            List<Vehicle> vehicles = vehicleDao.getVehicleByUserId(user.getUserid());
            req.setAttribute("vehicles", vehicles);
            req.setAttribute("parkingLotId", parkingLotId);
            }
            
            catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
    	    } catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        
        req.getRequestDispatcher("/Booking.jsp").forward(req, resp);
    }
	
}



