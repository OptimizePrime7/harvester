/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harvester;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author ReneZ
 */
public class Database {
        
    
    
        public void insertDataToDB(double h, double t) {
        try {

            //establish connection with database
            Class.forName("org.sqlite.JDBC");
            try (Connection con = DriverManager.getConnection("jdbc:sqlite:D:\\sqlite3\\SQLiteStudio\\db.db"); Statement st = con.createStatement()) {
                            
                st.executeUpdate("insert into messdaten values(null,"+h+","+t+")");
                try (ResultSet rs = st.executeQuery("select * from messdaten")) {
                    while (rs.next()) {
                        System.out.println(rs.getString("name") + " " + rs.getString("age"));
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
    }
}

