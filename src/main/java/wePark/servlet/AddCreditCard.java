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


@WebServlet("/addcreditcard")
public class AddCreditCard extends HttpServlet {
	
	protected UserDao userDao;
	protected CreditCardsDao creditCardsDao;
	
	@Override
	public void init() throws ServletException {
		userDao = UserDao.getInstance();
		creditCardsDao = CreditCardsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/AddCreditCard.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve the username from the session
        String username = (String) req.getSession().getAttribute("username");
        String cardnumber = req.getParameter("cardnumber");
        // exiprationdate must be in the format yyyy-mm-dd.
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	String exiprationdateStr = req.getParameter("exiprationdate");
    	Date exiprationdateUtil = new Date();
    	try {
    		exiprationdateUtil = dateFormat.parse(exiprationdateStr);
    	} catch (ParseException e) {
    		e.printStackTrace();
			throw new IOException(e);
    	}
    	
    	// Convert java.util.Date to java.sql.Date
        java.sql.Date exiprationdate = new java.sql.Date(exiprationdateUtil.getTime());

	    try {
	        // Exercise: parse the input for StatusLevel.
	        User user = userDao.getUserByUserName(username);
	        CreditCards creditCard = new CreditCards(cardnumber, user.getUserid(), exiprationdate);
	        creditCardsDao.create(creditCard);
	        
	        // Retrieve the list of vehicles for the current user again
	        List<CreditCards> creditCards = creditCardsDao.getCreditCardsByUserId(user.getUserid());

	        // Set the updated list of vehicles as a request attribute
	        req.setAttribute("creditcards", creditCards);

	        // Forward the updated list to the ViewVehicles.jsp page
	        req.getRequestDispatcher("/CreditCards.jsp").forward(req, resp);
	    } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        

    }
}
