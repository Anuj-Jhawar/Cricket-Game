package databasequery;

import cricketgame.CricketGame;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class FindTeamId implements QueryDatabase{
    @Override
    public int find(String teamName, Connection connection) {
        if(connection!=null){
            Statement statement;
            try{
                statement = connection.createStatement();
                String sqlCommandToGetTeamId = "SELECT * FROM Teams WHERE Name = '" + teamName + "'";
                try {
                    ResultSet resultSet = statement.executeQuery(sqlCommandToGetTeamId);
                    if(resultSet.next())
                    return resultSet.getInt("id");
                    else
                        return 0;
                }
                catch (Exception e){
                    System.out.println(e);
                    System.out.println("Query not completed. FindTeams");
                }

            }
            catch (Exception e){
                System.out.println(e);
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
