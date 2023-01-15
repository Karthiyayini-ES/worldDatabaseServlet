package WorldDatabase;

import java.beans.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;
@WebServlet("/country")
public class SelectCityInCountry extends HttpServlet {
	public void service(HttpServletRequest req ,HttpServletResponse res) throws ServletException, IOException {
		HttpSession sesobj= req.getSession();
		
		String ipAddress = req.getParameter("ipAddress");
		String name = req.getParameter("name");
		String password =req. getParameter("password");
		
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://"+ipAddress+":3306/world", name, password);
        java.sql.Statement stateobj =  con.createStatement();
        String query = "select Name from country";
        ResultSet rs = stateobj.executeQuery(query);
        ResultSetMetaData rsMetaData =  rs.getMetaData();
        int count =  rsMetaData.getColumnCount();
        ArrayList arrlisobj = new ArrayList();
       
        while (rs.next()) {
            for (int i = 1; i <=count; i++) {
            	arrlisobj.add(rs.getObject(i));
//                System.out.print(rs.getObject(i));
//                System.out.print("|");
            }
//            System.out.println();
        }
        req.setAttribute("ArraytlistObject",arrlisobj);
//        System.out.println(arrlisobj);
    	
    	sesobj.setAttribute("ArraytlistObject", arrlisobj);
    	sesobj.setAttribute("ip", ipAddress);
    	sesobj.setAttribute("name", name);
    	sesobj.setAttribute("pass", password);
   
}
	catch (Exception ex){
        System.out.println(ex.getMessage());


    }

	 RequestDispatcher rdobj = req.getRequestDispatcher("/Cities.jsp");
	 rdobj.forward(req, res);

}
}
