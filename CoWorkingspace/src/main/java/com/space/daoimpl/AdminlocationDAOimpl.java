package com.space.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.space.bean.Branch;
import com.space.bean.Location;
import com.space.dao.AdminlocationDAO;
import com.space.db.SpaceDBUtil;
/**
 * @author J51 Batch
 * This is the Admin LocationDAO Implementation class.
 * Here implementation is provided for the unimplemented methods provided by AdminlocationDAO interface
 */
public class AdminlocationDAOimpl implements AdminlocationDAO{


	Connection con=SpaceDBUtil.getConnection();
	final static Logger logger=Logger.getLogger(AdminlocationDAOimpl.class);
	PreparedStatement ps=null;
	
	public int addlocation(Location location) {
		int n=0;
		try {
			logger.info("Add location method called");
			ps=con.prepareStatement("insert into location(locationname) values(?)");
			ps.setString(1, location.getLocationname());
			n=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			e.printStackTrace();
		}
		return n;
		
	}
	/**
	 * This is the viewLocation method.It displays the particular branch information based on locationid.
	 */
	public Location viewLocation(int locationid) {
		Location l= new Location();
		try {
			logger.info("view location method called");
			ps=con.prepareStatement("select * form location where locationid=?");
			ps.setInt(1, locationid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				l.setLocationid(rs.getInt(1));
				l.setLocationname(rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			e.printStackTrace();
		}
		return l;
	}
	/**
	 * This is the viewAllLocation method.It is used to display the list of Location information.
	 */
	public List<Location> viewAlllocations() {
		List<Location> locations=new ArrayList<Location>();
		try {
			logger.info("View all location method called");
			ps=con.prepareStatement("select * from location");
			ResultSet rs=ps.executeQuery();
			Location location=null;
			while(rs.next()) {
				location=new Location();
				location.setLocationid(rs.getInt(1));
				location.setLocationname(rs.getString(2));
				locations.add(location);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			e.printStackTrace();
		}
		
		return locations;
	}

	public int updateLocation(int locationid, Location location) {
		int n=0;
		try {
			logger.info("Update location method called");
			ps=con.prepareStatement("update location set locationname=? where locationid=?");
			ps.setString(1, location.getLocationname());
			ps.setInt(2,locationid);
			n=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			e.printStackTrace();
		}
		return n;
	}

	public int deleteLocation(int locationid) {
		int n=0;
		try {
			logger.info("Delete location method called");
			ps=con.prepareStatement("delete from location where locationid=?");
			ps.setInt(1, locationid);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			e.printStackTrace();
		}
		return n;
	}

	
}
