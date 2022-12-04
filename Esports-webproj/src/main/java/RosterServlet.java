

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class demo3
 */
@WebServlet("/RosterServlet")
public class RosterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// include your DNS url
	 String dns="your sever DNS";
    Connection connection = null;
	 Statement statement = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RosterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 PrintWriter out = response.getWriter();
		 try {
		        Class.forName("com.mysql.jdbc.Driver");
		    } catch (ClassNotFoundException e) {
		        System.out.println("Where is your MySQL JDBC Driver?");
		        e.printStackTrace();
		        return;
		    }
	
  // Provide your username and password in place of admin1 and root.
  // Change your database name to your database in place of myDB1		 
	    try {
		        connection = DriverManager.getConnection("jdbc:mysql://"+ dns+":3306/esports", "username", "password");
		    } catch (SQLException e) {
		        System.out.println("Connection Failed!:\n" + e.getMessage());
		    }

		    if (connection != null) {
		        System.out.println("SUCCESS!!!! You made it, take control your database now!");
		        System.out.println("Creating statement...");
		        try {
					statement = connection.createStatement();
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
		        String sql;
		        
 // run the SQL query to extract table values from myTable
// include your table name in place of myTable		        
		        sql = "SELECT * FROM team";
		        ResultSet rs = null;
				try {
					rs = statement.executeQuery(sql);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

		        //STEP 5: Extract data from result set
		        try {
					while (rs.next()) {
					    //Retrieve by column name
						int teamID = rs.getInt("team_id");
						int orgID = rs.getInt("org_id");
						int leagueID = rs.getInt("league_id");
						String teamRoster = rs.getString("team_roster");
						    
						//Display values
						out.println("Team ID: " + teamID + ", ");
						out.println("Org ID: " + orgID + ", ");
						out.println("League ID: " + leagueID + ", ");
						out.println("Team Roster: " + teamRoster + ", ");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        //STEP 6: Clean-up environment
		        try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    } 
		   
		    else {
		        System.out.println("FAILURE! Failed to make connection!");
		    }
		    

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}