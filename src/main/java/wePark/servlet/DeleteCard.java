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

import wePark.dal.CreditCardsDao;
import wePark.dal.UserDao;
import wePark.model.CreditCards;
import wePark.model.User;


@WebServlet("/deleteCard")
public class DeleteCard extends HttpServlet {
	
	protected CreditCardsDao cardDao;
	protected UserDao userDao;

	
	@Override
	public void init() throws ServletException {
		cardDao = CreditCardsDao.getInstance();
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
            String cardNumber = req.getParameter("cardNumber");

            cardDao.delete(new CreditCards(cardNumber));
            
            User user = userDao.getUserByUserName(username);
            List<CreditCards> creditcards = cardDao.getCreditCardsByUserId(user.getUserid());
            
            req.setAttribute("creditcards", creditcards);
            
            }
            
            catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
    	    } catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        
        req.getRequestDispatcher("/CreditCards.jsp").forward(req, resp);
    }
	
}



