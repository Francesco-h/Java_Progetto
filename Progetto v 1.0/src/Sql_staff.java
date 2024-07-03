import java.sql.*;

public class Sql_staff extends Sql {

    private static Connection connection;

    private String url;
    public  Sql_staff() {
        super();

    }
    @Override
    public void set_connection() throws SQLException {
        this.connection=super.openConnection();
    }


    // Metodo per chiudere la connessione al database


    // Metodo per eseguire una query di selezione
    public void Insert_staff(String Email ,String Nome , String Cognome, String Password_utente, int tipo) {
        try{
            this.set_connection();
        }catch (SQLException e){
            e.printStackTrace();
        }
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            String query = "INSERT INTO `Utente`(`Nome`, `Password`, `Tipo`, `Cognome`, `email`) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(5, Email);  // Set value for the first ?
            preparedStatement.setString(1, Nome);          // Set value for the second ?
            preparedStatement.setString(4, Cognome);  // Set value for the third ?
            preparedStatement.setInt(3, tipo);  // Set value for the third ?
            preparedStatement.setString(2, Password_utente);  // Set value for the third ?
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

