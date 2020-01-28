
package com.space.db;

	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.IOException;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;
	import java.util.Properties;

import org.apache.log4j.Logger;

	public class SpaceDBUtil {
		public static Connection con=null;
		final static Logger logger=Logger.getLogger(SpaceDBUtil.class);
		public static Connection getConnection(){
		try {
			logger.info("Connection Established Successfully");
			File f=new File("C:\\Users\\RAJESHWARI BABBADI\\Desktop\\CoWorkingspace\\src\\main\\resources\\db.properties");
			FileInputStream fis=new FileInputStream(f);
			Properties p=new Properties();
			p.load(fis);
			Class.forName(p.getProperty("driver"));
			con=DriverManager.getConnection(p.getProperty("url"),p.getProperty("user"),p.getProperty("pass"));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			e.printStackTrace();
		}
		return con;
		}

	}

