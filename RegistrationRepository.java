package july13;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistrationRepository {

	private static final String CREATE_QUERY = "create table sam(name character varying(40) NOT NULL, passWord character varying(40) NOT NULL, gender character varying(40) NOT NULL, age character varying(40) NOT NULL, email character varying(40) NOT NULL)";
	private static final String INSERT_QUERY = "INSERT INTO  sam(name, passWord, gender, age, email) values(?,?,?,?,?)";
	// private static final String UPDATE_QUERY = "UPDATE sam SET name=?,
	// password=?, gender=?, age=?, email=?WHERE name=?;";

	private Connection con = null;

	private void getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/Ali", "postgres", "yourpassword");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Couldn't able to connect 1");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Couldn't able to connect 2");
		}
	}

	public boolean createRegistration() {
		boolean result = false;
		PreparedStatement ps = null;
		Statement st = null;
		getConnection();
		try {
			st = con.createStatement();

			boolean istableexist = st
					.execute("SELECT EXISTS (SELECT 1 FROM pg_tables where schemaname='public' AND tablename='sam')");
			if (istableexist) {
				System.out.println("Table already created...skiping it");
			} else {
				ps = con.prepareStatement(CREATE_QUERY);
				result = ps.execute();
				System.out.println("successfully created");
			}

		} catch (SQLException e) {
			System.out.println("exception in creation");
			e.printStackTrace();
		}
		return result;

	}

	public int insertCustInfo(CustInfo info) {
		int result = 0;
		PreparedStatement ps = null;
		getConnection();
		try {
			ps = con.prepareStatement(INSERT_QUERY);
			ps.setString(1, info.getName());
			ps.setString(2, info.getPwd());
			ps.setString(3, info.getGender());
			ps.setInt(4, info.getAge());
			ps.setString(5, info.getEmail());

			result = ps.executeUpdate();
			System.out.println("inserted successfully");
		} catch (SQLException e) {
			System.out.println("exception in insertion");
			e.printStackTrace();
		} finally {

			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}

	public int updateInfo(String name,String updField,String updValue){
			final String UPDATE_QUERY = "UPDATE sam SET "+updField+"="+"'"+updValue+"'"+" WHERE name="+"'"+name+"';";
			//System.out.println(UPDATE_QUERY);
			int result2 = 0;
			PreparedStatement pus = null;
			getConnection();
			try {
				pus = con.prepareStatement(UPDATE_QUERY);
				result2 = pus.executeUpdate();
				System.out.println("Updated successfully");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Exception in updating block");
				e.printStackTrace();
			}
			finally{
			
					try {
						pus.close();
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			return result2;
			
	}
	public int updateInfo(String name,String updField,int updValue){
		final String UPDATE_QUERY = "UPDATE sam SET "+updField+"="+"'"+updValue+"'"+" WHERE name="+"'"+name+"';";
		int result1 = 0;
		PreparedStatement pas = null;
		getConnection();
		try {
			pas = con.prepareStatement(UPDATE_QUERY);
			result1 = pas.executeUpdate();
			System.out.println("Updated successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception in updating block");
			e.printStackTrace();
		}
		finally{
		
				try {
					pas.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		return result1;
		
		
}

	public int deleteInfo(String field,String value){
		final String deleteQuery = "DELETE FROM sam WHERE "+field+"='"+value+"';";
		
		int resultdel = 0;
		PreparedStatement pds = null;
		getConnection();
		try {
			pds = con.prepareStatement(deleteQuery);
			resultdel = pds.executeUpdate();
			System.out.println("Updated successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception in updating block");
			e.printStackTrace();
		}
		finally{
		
				try {
					pds.close();
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
		}
		return resultdel;
		
	}

	public int deleteInfo(String field,int value){
		final String deleteQuery = "DELETE FROM sam WHERE "+field+"='"+value+"';";
		
		int resultdel = 0;
		PreparedStatement pds = null;
		getConnection();
		try {
			pds = con.prepareStatement(deleteQuery);
			resultdel = pds.executeUpdate();
			System.out.println("Updated successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception in updating block");
			e.printStackTrace();
		}
		finally{
		
				try {
					pds.close();
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
		}
		return resultdel;
		
	}
	public int login(String uName,String uPass){
		final String loginQuery = "SELECT password FROM sam WHERE name="+"'"+uName+"';";
		int loginResult = 0 ;
		PreparedStatement pdlogin = null;
		getConnection();
		int status =0;
		try {
			pdlogin = con.prepareStatement(loginQuery);
			ResultSet rs = pdlogin.executeQuery();
			while(rs.next()){
				String dbpass = rs.getString(1);
				if(dbpass.equals(uPass)){
					loginResult=1;
				}
				else{
					loginResult=0;
				}
			}
			status=1;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception in login block");
			e.printStackTrace();
		}
		finally{
		
				try {
					if(status==1){
					pdlogin.close();
					con.close();
					}
				}catch(SQLException e){
					e.printStackTrace();
				}
		}
		return loginResult;
	}
	public String userName;
	public String userGender;
	public int userAge;
	public String userEmail;
	
	public void userDetails(String name){
		final String detQuery = "SELECT * FROM sam WHERE name="+"'"+name+"';";
		PreparedStatement udls = null;
		getConnection();
		try{
			udls = con.prepareStatement(detQuery);
			ResultSet rs = udls.executeQuery();
			while(rs.next()){
				userName=rs.getString(1);
				userGender = rs.getString(3);
				userAge = rs.getInt(4);
				userEmail = rs.getString(5);
			}
		} catch(SQLException e){
			System.out.println("Exception in userDetails block");
			e.printStackTrace();
		}
	}
	
	
	
}