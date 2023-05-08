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


@WebServlet("/deleteVehicle")
public class DeleteVehicle extends HttpServlet {
	
	protected VehicleDao vehicleDao;
	protected UserDao userDao;

	
	@Override
	public void init() throws ServletException {
		vehicleDao = VehicleDao.getInstance();
		userDao = UserDao.getInstance();
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        String username = (String) req.getSession().getAttribute("username");

        try {
            int vehicleId = Integer.parseInt(req.getParameter("vehicleId"));

            vehicleDao.delete(new Vehicle(vehicleId));
            
            User user = userDao.getUserByUserName(username);
            List<Vehicle> vehicles = vehicleDao.getVehicleByUserId(user.getUserid());
            
            req.setAttribute("vehicles", vehicles);
            
            }
            
            catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
    	    } catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        
        req.getRequestDispatcher("/ViewVehicles.jsp").forward(req, resp);
    }
	
}



