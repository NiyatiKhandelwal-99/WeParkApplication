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



@WebServlet("/regularpassbuybutton")
public class RegularPassBuyButton extends HttpServlet {
	
	
	@Override
	public void init() throws ServletException {
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        //Just render the JSP.   
        req.getRequestDispatcher("/BuyRegularPass.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
	        // Exercise: parse the input for StatusLevel.
	        int parkingLotId = Integer.parseInt(req.getParameter("parkingLotId"));
            float price = Float.parseFloat(req.getParameter("price"));
            
            req.setAttribute("parkingLotId", parkingLotId);
            req.setAttribute("price", price);
            
	    

	  
        
        req.getRequestDispatcher("/RegularPass.jsp").forward(req, resp);
    }

}



