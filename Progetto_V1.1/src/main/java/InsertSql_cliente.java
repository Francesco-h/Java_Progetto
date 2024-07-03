import java.sql.*;

public class InsertSql_cliente extends Sql {

    private static Connection connection;

    public InsertSql_cliente() {
        super();

    }
    @Override
    public void set_connection() throws SQLException {
        this.connection=super.openConnection();
    }

    // Metodo per eseguire una query di selezione
    public void insert_clienti(String Email , String Nome , String Cognome, String Password_utente, int tipo, String nr_carta) {
        try{
            this.set_connection();
        }catch (SQLException e){
            e.printStackTrace();
        }
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            String query = "INSERT INTO `Utente`(`Nome`, `Password`, `Tipo`, `Cognome`, `email`,`nr_carta`) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(5, Email);  // Set value for the first ?
            preparedStatement.setString(1, Nome);          // Set value for the second ?
            preparedStatement.setString(4, Cognome);  // Set value for the third ?
            preparedStatement.setInt(3, tipo);  // Set value for the third ?
            preparedStatement.setString(2, Password_utente);  // Set value for the third ?
            preparedStatement.setString(6, nr_carta);
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

