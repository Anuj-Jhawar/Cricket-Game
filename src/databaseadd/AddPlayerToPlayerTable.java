package databaseadd;

import databasequery.FindPlayerId;
import jdbc.JdbcConnection;
import player.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddPlayerToPlayerTable implements AddToTable {
    private Player player;
    public AddPlayerToPlayerTable(Player player){
        this.player = player;
    }
    @Override
    public void add() {
        JdbcConnection jdbc = new JdbcConnection();
        Connection connection = jdbc.getConnection();
        FindPlayerId findPlayerId = new FindPlayerId();
        if(findPlayerId.find(player.getName(),connection)!=0)
            return;
        if(connection!=null){
            PreparedStatement statement;
            try{
                String sqlCommandToInsertTeamInTeamTable = "INSERT INTO Players(Name,Age) VALUES (?,?)";
                statement = connection.prepareStatement(sqlCommandToInsertTeamInTeamTable);
                statement.setString(1, player.getName());
                statement.setInt(2,1);
                try {
                    statement.executeUpdate();
                }
                catch (Exception e){
                    System.out.println(e);
                    System.out.println("Query not completed in databaseadd.AddPlayerToPlayerTable.");
                }

            }
            catch (Exception e){
                System.out.println("Statement not created in databaseadd.AddPlayerToPlayerTable.");
            }
            finally {
                try{
                    //connection.close();
                }
                catch (Exception e){
                    System.out.println("Connection not closed in databaseadd.AddPlayerToPlayerTable.");
                }

            }

        }
        else{
            System.out.println("Connection not established in databaseadd.AddPlayerToPlayerTable.");
        }
    }
}
