package databasequery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class FindTournamentId implements QueryDatabase{
    @Override
    public int find(String tournamentName, Connection connection) {
        if(connection!=null){
            PreparedStatement statement;
            try{
                String sqlCommandToGetTournamentId = "SELECT * FROM Tournaments WHERE Name = ?";
                statement = connection.prepareStatement(sqlCommandToGetTournamentId);
                statement.setString(1,tournamentName);
                try {
                    ResultSet resultSet = statement.executeQuery();
                    if(resultSet.next())
                    return resultSet.getInt("id");
                    else
                        return 0;
                }
                catch (Exception e){
                    System.out.println(e);
                    System.out.println("Query not completed. FindTournaments");
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
