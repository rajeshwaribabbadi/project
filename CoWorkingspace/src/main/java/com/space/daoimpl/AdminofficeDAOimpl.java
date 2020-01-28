package com.space.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.space.bean.Office;
import com.space.dao.AdminofficeDAO;
import com.space.db.SpaceDBUtil;

public class AdminofficeDAOimpl implements AdminofficeDAO {
	 Connection con = SpaceDBUtil.getConnection();
	 final static Logger logger=Logger.getLogger(AdminofficeDAOimpl.class);
	    PreparedStatement ps = null;
	
	
	public int addOffice(Office office) {
		int n=0;
		try {
			logger.info("Add Office method called");
			ps=con.prepareStatement("insert into office(officename,officeprice,branchid,seats) values(?,?,?,?)");
			ps.setString(1, office.getOfficename());
			ps.setDouble(2, office.getOfficeprice());
			ps.setInt(3, office.getBranchid());
			ps.setInt(4, office.getSeats());
			n=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			e.printStackTrace();
		}
		return n;
		
	}

	public Office viewOffices(int officeid) {
	       Office o =new Office();
	       try {
	    	   logger.info("View office method called");
			ps=con.prepareStatement("select * form office where officeid=?");
			   ps.setInt(1, officeid);
			   ResultSet rs =ps.executeQuery();
			   while(rs.next()) {
				   o.setOfficeid(rs.getInt(1));
				   o.setOfficename(rs.getString(2));
				   o.setOfficeprice(rs.getDouble(3));
				   o.setBranchid(rs.getInt(4));
				   o.setSeats(rs.getInt(5));
			   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			e.printStackTrace();
		}
	       
		return o;
	}

	public List<Office> viewAllOffices() {
		List<Office> office =new ArrayList<Office>();
		Office o= new Office();
		try {
			logger.info("view all offices method called");
			ps=con.prepareStatement("select * form office");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				   o.setOfficeid(rs.getInt(1));
				   o.setOfficename(rs.getString(2));
				   o.setOfficeprice(rs.getDouble(3));
				   o.setBranchid(rs.getInt(4));
				   o.setSeats(rs.getInt(5));
			   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			e.printStackTrace();
		}
		return office;
	}

	public int updateOffice(int officeid, Office office) {
		int n=0;
		try {
			logger.info("Update office method called");
			ps=con.prepareStatement("update office set officename=?,officeprice=?,branchid=?,seats=? where officeid=?");
			ps.setString(1, office.getOfficename());
			ps.setDouble(2, office.getOfficeprice());
			ps.setInt(3, office.getBranchid());
			ps.setInt(4, office.getSeats());
			ps.setInt(5, officeid);
			n=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			e.printStackTrace();
		}
		return n;
		
		
	}

	public int deleteOffice(int officeid) {
	      int n=0;
	      try {
	    	  logger.info("delete office method called");
			ps=con.prepareStatement("detele form office where officeid=?");
			  ps.setInt(1, officeid);
			  n=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			e.printStackTrace();
		}
	      return n;
	      
		
	}
	

}