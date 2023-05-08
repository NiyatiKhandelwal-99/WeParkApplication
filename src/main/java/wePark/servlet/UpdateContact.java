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
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/updatecontact")
public class UpdateContact extends HttpServlet {
	
	protected UserDao userDao;
	protected ContactDao contactDao;
	
	@Override
	public void init() throws ServletException {
		userDao = UserDao.getInstance();
		contactDao = ContactDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/MyAccount.jsp").forward(req, resp);
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
	        Contact contact = contactDao.getContactByUserId(user.getUserid());
	    	
	        // Update the contact information
	        String firstname = req.getParameter("firstname");
	        String lastname = req.getParameter("lastname");
	        String address1 = req.getParameter("address1");
	        String address2 = req.getParameter("address2");
	        String city = req.getParameter("city");
	        String state = req.getParameter("state");
	        String zipcode = req.getParameter("zipcode");
	        String email = req.getParameter("email");
	        String phone = req.getParameter("phone");
	        String driver_license = req.getParameter("driver_license");
	        String driver_license_state = req.getParameter("driver_license_state");
	        
	        contact = contactDao.updateContactInfo(contact, user.getUserid(), firstname, lastname, address1,
	        		address2, city, state, zipcode, email, phone, driver_license, driver_license_state);
	     // Set the Contact object as a request attribute
	        req.setAttribute("contact", contact);
	        messages.put("success", "Successfully update your contact information.");
	    } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        req.getRequestDispatcher("/MyAccount.jsp").forward(req, resp);
    }
}
