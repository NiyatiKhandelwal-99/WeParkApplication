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
import wePark.dal.RegularPassDao;
import wePark.dal.UserDao;
import wePark.model.CreditCards;
import wePark.model.User;


@WebServlet("/cancelregularpass")
public class CancelRegularPass extends HttpServlet {
	
	protected RegularPassDao regularPassDao;
	protected UserDao userDao;

	
	@Override
	public void init() throws ServletException {
		
		userDao = UserDao.getInstance();
		regularPassDao = RegularPassDao.getInstance();
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        String username = (String) req.getSession().getAttribute("username");

        try {

        	int passid = Integer.parseInt(req.getParameter("passid"));
            regularPassDao.deleteRegularPassByRegularPassId(passid);
 
          
            User user = userDao.getUserByUserName(username);
            List<wePark.model.RegularPass> regularPasses = regularPassDao.getRegularPassByUserId(user.getUserid());
            
            req.setAttribute("regularPasses", regularPasses);
            
            }
            
            catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
    	    } catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        
        req.getRequestDispatcher("/BuyRegularPass.jsp").forward(req, resp);
    }
	
}



