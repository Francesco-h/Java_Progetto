import java.sql.*;

public class InsertSql_film extends Sql{
    private static Connection connection;

    public InsertSql_film() {
        super();

    }
    @Override
    public void set_connection() throws SQLException {
        this.connection=super.openConnection();
    }

    public void insert_film(String titolo , String categoria , double durata) {
        try{
            this.set_connection();
        }catch (SQLException e){
            e.printStackTrace();
        }

        try {
            String query = "INSERT INTO `Film`(`titolo`, `categoria`, `durata`) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, titolo);          // Set value for the second ?
            preparedStatement.setString(2, categoria);  // Set value for the third ?
            preparedStatement.setDouble(3, durata);
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
