package databasequery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class FindPlayerId implements QueryDatabase{
    @Override
    public int find(String playerName, Connection connection) {
        if(connection!=null){
            PreparedStatement statement;
            try{
                String sqlCommandToGetPlayerId = "SELECT * FROM Players WHERE Name = ?";
                statement = connection.prepareStatement(sqlCommandToGetPlayerId);
                statement.setString(1,playerName);
                try {
                    ResultSet resultSet = statement.executeQuery();
                    if(resultSet.next())
                    return resultSet.getInt("id");
                    else return 0;
                }
                catch (Exception e){
                    System.out.println(e + "FindPlayer");
                    System.out.println("Query not completed.");
                }

            }
            catch (Exception e){
                System.out.println("Statement not created.");
            }
            finally {
                try{
                    //connection.close();
                }
                catch (Exception e){
                    System.out.println("Connection not closed.");
                }

            }

        }
        else{
            System.out.println("Connection not established");
        }
        return 1;
    }
}
