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


@WebServlet("/creditcards")
public class ViewCreditCards extends HttpServlet {
	
	protected UserDao userDao;
	protected CreditCardsDao creditCardDao;
	
	@Override
	public void init() throws ServletException {
		userDao = UserDao.getInstance();
		creditCardDao = CreditCardsDao.getInstance();
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
	        List<CreditCards> creditcards = creditCardDao.getCreditCardsByUserId(user.getUserid());
	    	
	        // Set the Contact object as a request attribute
	        req.setAttribute("creditcards", creditcards);
	    } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        req.getRequestDispatcher("/CreditCards.jsp").forward(req, resp);
    }
}
