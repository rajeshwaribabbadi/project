package com.space.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.space.bean.Branch;
import com.space.dao.AdminbranchDAO;
import com.space.db.SpaceDBUtil;
/**
* @author J51 Batch
* This is the Admin BranchDAO Implementation class.
* Here implementation is provided for the unimplemented methods  provided by AdminbranchDAO interface
*
*/
public class AdminbranchDAOimpl implements AdminbranchDAO {

	Connection con=SpaceDBUtil.getConnection();
	final static Logger logger=Logger.getLogger(AdminbranchDAOimpl.class);
	PreparedStatement ps=null;
	/**
	 * This is the addbranch method.Here we add the data to the fields provided by the bean class.
	 * Exceptions are used for the normal flow of execution.
	 */
	public int addbranch(Branch branch) {
		int n=0;
		try {
			logger.info("Add branches method called");
			ps=con.prepareStatement("insert into branch(branchname,locationid) values(?,?)");
			ps.setString(1, branch.getBranchname());
			ps.setInt(2,branch.getLocationid());
			n=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			e.printStackTrace();
		}
		return n;
	}
	/**
	 * This is the viewBranch method.It displays the particular branch information based on branchid.
	 */
	public Branch viewBranch(int branchid) {
		Branch b= new Branch();
		try {
			logger.info("view branches method called");
			ps=con.prepareStatement("select * form branch where branchid=?");
			ps.setInt(1, branchid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				b.setBranchid(rs.getInt(1));
				b.setBranchname(rs.getString(2));
				b.setLocationid(rs.getInt(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			e.printStackTrace();
		}
		return b;
	}
	/**
	 * This is the viewAllBranch method.It is used to display the list of Branch information.
	 */
	public List<Branch> viewAllBranch() {
		List<Branch> branches=new ArrayList<Branch>();
		try {
			logger.info("view All branches method called");
			ps=con.prepareStatement("select * from branch");
			ResultSet rs=ps.executeQuery();
			Branch branch=null;
			while(rs.next()) {
				branch=new Branch();
				branch.setBranchid(rs.getInt(1));
				branch.setBranchname(rs.getString(2));
				branch.setLocationid(rs.getInt(3));
				branches.add(branch);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			e.printStackTrace();
		}
		
		return branches;
	}
	/**
	 * This is the updateBranch method.It updates the branch information based on branchid.
	 */
	public int updateBranch(int branchid, Branch branch) {
		int n=0;
		try {
			logger.info("Update branches method called");
			ps=con.prepareStatement("update branch set branchname=?,locationid=? where branchid=?");
			ps.setString(1, branch.getBranchname());
			ps.setInt(2, branch.getLocationid() );
			ps.setInt(2, branchid);
			n=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			e.printStackTrace();
		}
		return n;
	}
	/**
	 * This is the deleteBranch method.It deletes the particular branch data based on branchid.
	 * It doesn't deletes the data from the database.It shows the status Inactive.
	 */
	public int deleteBranch(int branchid) {
		
		int n=0;
		try {
			logger.info("Delete branches method called");
			ps=con.prepareStatement("delete from branch where branchid=?");
			ps.setInt(1, branchid);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			e.printStackTrace();
		}
		return n;
	}
	
}
