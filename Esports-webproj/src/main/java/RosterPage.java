import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// Fetch data
/**
 * Servlet implementation class demo3
 */
@WebServlet("/RosterPage")
public class RosterPage extends HttpServlet {
    private static final long serialVersionUID = 1 ;

    String dns = "your sever DNS";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RosterPage() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String sql;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement statement1 = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        String keyword = request.getParameter("keyword");
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String title = "Team Roster Page";
        String docType =
            "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
        
        

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + dns + ":3306/esports", "username", "password");
        } catch (SQLException e2) {
            // TODO Auto-generated catch block
            System.out.println("Connection Failed!:\n" + e2.getMessage());
        }
        System.out.println("SUCCESS!!!! You made it, take control     your database now!");
        System.out.println("Creating statement...");
        
        
        //START OF HTML CODING 
        out.println("<body><html>"
        		+ "<head>\r\n"
        		+ "<meta charset=\"ISO-8859-1\">");
        
        out.println("<link rel=stylesheet type=text/css href=RosterPage.css media=screen>"
        		+ "</head>");
        
        //header HTML code
        out.println("<nav>\r\n"
        		+ "    <label class=\"logo\">eSports Website</label>\r\n"
        		+ "    <ul>\r\n"
        		+ "        <li><a class =\"active\" href=\"#\">Home</a></li>\r\n"
        		+ "        <li><a href=\"#\">Schedule</a></li>\r\n"
        		+ "        <li><a href=\"#\">Create Match</a></li>\r\n"
        		+ "        <li><a href=\"#\">Sign up</a></li>\r\n"
        		+ "    </ul>\r\n"
        		+ "</nav>");
        //end of header code
        
        
        //Fetching mySQL database information and displaying the table in HTML
        sql = "SELECT * FROM team";
        try {

            statement1 = connection.prepareStatement(sql);
            String theUserName = keyword;
            statement1.setString(1, theUserName);
     
        } catch (SQLException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        try {

            rs = statement1.executeQuery();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        out.println("<table border=1 align=center style=margin-top:200px; width=30% height=30%>");
        out.println("<tr><th>Players</th><th>Team ID</th><th>Org ID</th><th>League ID</th>");
        try {
            while (rs.next()) {
                //Retrieve by column name
            	int teamID = rs.getInt("team_id");
				int orgID = rs.getInt("org_id");
				int leagueID = rs.getInt("league_id");
				String teamRoster = rs.getString("team_roster");
                out.println("<tr><td style=text-align:center>" + teamRoster + "</td><td style=text-align:center>" + teamID + "</td><td style=text-align:center>" + orgID + "</td><td style=text-align:center>" + leagueID + "</td></tr>");
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
      //End of fetching and displaying mySQL data information
        
        
        out.println("</body></html>");
        //END OF HTML CODING
        
    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}