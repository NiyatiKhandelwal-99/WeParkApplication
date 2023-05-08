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
import wePark.dal.ParkinglotDao;
import wePark.dal.ParkingslotDao;
import wePark.dal.RegularPassDao;
import wePark.dal.UserDao;
import wePark.model.Booking;
import wePark.model.User;



@WebServlet("/createregularpass")
public class CreateRegularPass extends HttpServlet {
	
	protected UserDao userDao;
	protected ParkinglotDao parkinglotDao;
	protected RegularPassDao regularPassDao;
	

	@Override
	public void init() throws ServletException {
		
		userDao = UserDao.getInstance();
		parkinglotDao = ParkinglotDao.getInstance();
		regularPassDao = RegularPassDao.getInstance();
		
	}
	
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        req.getRequestDispatcher("/BuyRegularPass.jsp").forward(req, resp);
		
	}
	
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        int parkingLotId = Integer.parseInt(req.getParameter("parkingLotId"));
        try {
            String username = (String) req.getSession().getAttribute("username");
            User user = userDao.getUserByUserName(username);
            
            String inputString = req.getParameter("start_date");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date javaDate = dateFormat.parse(inputString);
            java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());
                        
            
            int duration_in_days = Integer.parseInt(req.getParameter("duration_in_days"));
            float price = Float.parseFloat(req.getParameter("price"));
            
            wePark.model.RegularPass regularPass = new wePark.model.RegularPass(user.getUserid(), parkingLotId, new Date(System.currentTimeMillis()),sqlDate, duration_in_days, duration_in_days*price);
            regularPassDao.create(regularPass);
            
            List<wePark.model.RegularPass> regularPasses = regularPassDao.getRegularPassByUserId(user.getUserid());
            req.setAttribute("regularPasses", regularPasses);
            
            }
            
            catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
    	    } catch (Exception e) {
    			
    			e.printStackTrace();
    		}
        
        req.getRequestDispatcher("/BuyRegularPass.jsp").forward(req, resp);
    }

}



