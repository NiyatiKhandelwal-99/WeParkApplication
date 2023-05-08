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


@WebServlet("/addvehicle")
public class AddVehicle extends HttpServlet {
	
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
        req.getRequestDispatcher("/AddVehicle.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve the username from the session
        String username = (String) req.getSession().getAttribute("username");
        String brand = req.getParameter("brand");
        String year = req.getParameter("year");
        String platenumber = req.getParameter("platenumber");
	    try {
	        // Exercise: parse the input for StatusLevel.
	        User user = userDao.getUserByUserName(username);
	        Vehicle vehicle = new Vehicle(-1, user.getUserid(), brand, year, platenumber);
	        vehicleDao.create(vehicle);
	        
	        // Retrieve the list of vehicles for the current user again
	        List<Vehicle> vehicles = vehicleDao.getVehicleByUserId(user.getUserid());

	        // Set the updated list of vehicles as a request attribute
	        req.setAttribute("vehicles", vehicles);

	        // Forward the updated list to the ViewVehicles.jsp page
	        req.getRequestDispatcher("/ViewVehicles.jsp").forward(req, resp);
	    } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        

    }
}
