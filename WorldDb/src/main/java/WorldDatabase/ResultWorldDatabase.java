package WorldDatabase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/getcountry")
public class ResultWorldDatabase extends HttpServlet {
	public void service(HttpServletRequest req ,HttpServletResponse res) throws ServletException, IOException {
		HttpSession sesobj= req.getSession();

		String ipAddress = (String) sesobj.getAttribute("ip");
		String name = (String) sesobj.getAttribute("name");
		String password =(String) sesobj.getAttribute("pass");
		System.out.println(ipAddress);
	
	try {
		String coun = req.getParameter("country");
		Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://"+ipAddress+":3306/world", name, password);
        java.sql.Statement stateobj =  con.createStatement();
        String query = "select district from city where countrycode = (select code from country where name='"+coun+"') group by district";
        ResultSet rs = stateobj.executeQuery(query);
        ResultSetMetaData rsMetaData =  rs.getMetaData();
        int count =  rsMetaData.getColumnCount();
        ArrayList obj = new ArrayList();
        
        while (rs.next()) {
            for (int i = 1; i <=count; i++) {
            	obj.add(rs.getObject(i));
//                System.out.print(rs.getObject(i));
//                System.out.print("|");
            }
//            System.out.println();
        }
        req.setAttribute("ArrayObject",obj);
        ArrayList b = (ArrayList) sesobj.getAttribute("ArraytlistObject");
        req.setAttribute("ArraytlistObject", b);
        System.out.println(obj);
   
}
	catch (Exception ex){
        System.out.println(ex.getMessage());


    }
	 RequestDispatcher rdobj = req.getRequestDispatcher("/Cities.jsp");
	 rdobj.forward(req, res);

	}

}
