package wePark.dal;

import wePark.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class FloorDao {
  protected ConnectionManager connectionManager;

  // Single pattern: instantiation is limited to one object.
  private static FloorDao instance = null;
  protected FloorDao() {
    connectionManager = new ConnectionManager();
  }
  public static FloorDao getInstance() {
    if(instance == null) {
      instance = new FloorDao();
    }
    return instance;
  }

  private boolean validateInput(String input) {
    if (input.contains(";")) {
      return false;
    }
    return true;
  }

  public Floor create(Floor floor) throws Exception {
    String insertFloor = "INSERT INTO Floor(parkinglotid,floor_number,max_height_in_inch)"
        + " VALUES(?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertFloor, Statement.RETURN_GENERATED_KEYS);

      //validate input
      if (!validateInput(Integer.toString(floor.getParkinglotid()))
          || !validateInput(Integer.toString(floor.getFloor_number()))
          || !validateInput(Integer.toString(floor.getMax_height_in_inch()))){
        throw new Exception("Invalid input, try again");
      }

      insertStmt.setInt(1, floor.getParkinglotid());
      insertStmt.setInt(2, floor.getFloor_number());
      insertStmt.setInt(3, floor.getMax_height_in_inch());

      insertStmt.executeUpdate();

      resultKey = insertStmt.getGeneratedKeys();
      int floorid = -1;
      if(resultKey.next()) {
        floorid = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      floor.setFloorid(floorid);
      return floor;
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(insertStmt != null) {
        insertStmt.close();
      }
    }
  }

  public Floor getFloorByFloorId(int floorid) throws Exception {
    String selectFloor = "SELECT floorid, parkinglotid, floor_number, max_height_in_inch"
        + " FROM Floor WHERE floorid=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {

      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectFloor);
      selectStmt.setInt(1, floorid);

      results = selectStmt.executeQuery();

      if(results.next()) {
        int resultFloorid = results.getInt("floorid");
        int parkinglotid = results.getInt("parkinglotid");
        int floor_number = results.getInt("floor_number");
        int max_height_in_inch = results.getInt("max_height_in_inch");
        Floor floor = new Floor(resultFloorid, parkinglotid, floor_number, max_height_in_inch);
        return floor;
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(selectStmt != null) {
        selectStmt.close();
      }
      if(results != null) {
        results.close();
      }
    }
    return null;
  }


  public List<Floor> getFloorByParkingLotId(int parkinglotid) throws SQLException {
    List<Floor> floors = new ArrayList<Floor>();
    String selectFloor = "SELECT floorid, parkinglotid, floor_number, max_height_in_inch"
        + " FROM Floor WHERE parkinglotid=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectFloor);
      selectStmt.setInt(1, parkinglotid);
      results = selectStmt.executeQuery();
      while(results.next()) {
        int floorid = results.getInt("floorid");
        int resultParkingLotId = results.getInt("parkinglotid");
        int floor_number = results.getInt("floor_number");
        int max_height_in_inch = results.getInt("max_height_in_inch");
        Floor floor = new Floor(floorid, resultParkingLotId, floor_number, max_height_in_inch);
        floors.add(floor);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(selectStmt != null) {
        selectStmt.close();
      }
      if(results != null) {
        results.close();
      }
    }
    return floors;
  }

  public Floor updateFloorNumber(Floor floor, int newfloor_number) throws Exception {
    String updateFloorNumber= "UPDATE Floor SET floor_number=? WHERE floorid=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      //validate input

      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateFloorNumber);
      updateStmt.setInt(1, newfloor_number);
      updateStmt.setInt(2, floor.getFloorid());
      updateStmt.executeUpdate();

      floor.setFloor_number(newfloor_number);
      return floor;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(updateStmt != null) {
        updateStmt.close();
      }
    }
  }


}