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


@WebServlet("/viewvehicle")
public class ViewVehicle extends HttpServlet {
	
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
	        List<Vehicle> vehicles = vehicleDao.getVehicleByUserId(user.getUserid());
	    	
	        // Set the Contact object as a request attribute
	        req.setAttribute("vehicles", vehicles);
	    } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        req.getRequestDispatcher("/ViewVehicles.jsp").forward(req, resp);
    }
}
