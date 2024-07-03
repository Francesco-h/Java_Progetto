import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertBiglietto_Sql extends Sql{
    private static Connection connection;
    public InsertBiglietto_Sql(){
        super();
    }
    @Override
    public void set_connection() throws SQLException {
        this.connection=super.openConnection();
    }

    public void insert_biglietto(String titolo,String email , int nr_biglietti) {
        try{
            this.set_connection();
        }catch (SQLException e){
            e.printStackTrace();
        }

        try {
            String query = "INSERT INTO `Biglietti`(`nr_posti`,`email_utente`,`titolo_film`) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, nr_biglietti);          // Set value for the second ?
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, titolo);// Set value for the third ?

            // Executing the insert
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Insert effettuata con successo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            super.closeConnection(connection);
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

}
