package javadatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by 79001 on 2017/7/24.
 */
public class Snnipets {
    public static void main (String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3360/src", "user1", "37934bit");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from tbstudent");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
