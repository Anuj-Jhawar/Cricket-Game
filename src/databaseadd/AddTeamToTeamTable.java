package databaseadd;

import cricketgame.Team;
import databasequery.FindTeamId;
import jdbc.JdbcConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddTeamToTeamTable implements AddToTable {
    private Team team;
    public AddTeamToTeamTable(Team team){
        this.team = team;
    }
    @Override
    public void add() {
        /*
            Add team to Teams table if team not in the database.
        */
        JdbcConnection jdbc = new JdbcConnection();
        Connection connection = jdbc.getConnection();
        FindTeamId findTeamId = new FindTeamId();
        if(findTeamId.find(team.getTeamName(),connection)!=0)
            return;
        if(connection!=null){
            Statement statement;
            try{
                statement = connection.createStatement();
                String sqlCommandToInsertTeamInTeamTable = "INSERT INTO Teams (Name) VALUES ('" + team.getTeamName() + "')";
                try {
                    statement.executeUpdate(sqlCommandToInsertTeamInTeamTable);
                }
                catch (Exception e){
                    System.out.println(e);
                    System.out.println("Query not completed in databaseadd.AddTeamToTeamTable.");
                }

            }
            catch (Exception e){
                System.out.println("Statement not created in databaseadd.AddTeamToTeamTable.");
            }
            finally {
                try{
                    //connection.close();
                }
                catch (Exception e){
                    System.out.println("Connection not closed in databaseadd.AddTeamToTeamTable.");
                }

            }

        }
        else{
            System.out.println("Connection not established in databaseadd.AddTeamToTeamTable.");
        }
    }
}
