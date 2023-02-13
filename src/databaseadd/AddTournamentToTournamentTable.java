package databaseadd;

import jdbc.JdbcConnection;

import java.sql.Connection;
import java.sql.Statement;

public class AddTournamentToTournamentTable implements AddToTable {
    private String tournamentName;

    public AddTournamentToTournamentTable(String tournamentName){
        this.tournamentName = tournamentName;
    }
    @Override
    public void add() {
        JdbcConnection jdbc = new JdbcConnection();
        Connection connection = jdbc.getConnection();
        if(connection!=null){
            Statement statement;
            try{
                statement = connection.createStatement();
                String sqlCommandToCreateTournamentTable = "INSERT INTO Tournaments (Name) VALUES ('" + tournamentName + "')";
                try {
                    statement.executeUpdate(sqlCommandToCreateTournamentTable);
                }
                catch (Exception e){
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
    }
}
