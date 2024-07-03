import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrintSql_film extends Sql {
    private Connection connection;

    public PrintSql_film() {
        super();
    }

    @Override
    public void set_connection() throws SQLException {
        this.connection = super.openConnection();
    }

    public void print_film() {
        try {
            set_connection();
            String query = "SELECT * FROM `Film`";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {


                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (!resultSet.next()) {
                        throw new DBmiss("Film non trovato");
                    } else {
                        do {
                            String filmTitolo = resultSet.getString("titolo");
                            String filmcat = resultSet.getString("categoria");
                            Double durata = resultSet.getDouble("durata");
                            System.out.println("\n\nTitolo: " + filmTitolo+"\ncategoria: "+ filmcat +"\ndurata: "+ durata);
                        } while (resultSet.next());
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (DBmiss e) {
                throw new RuntimeException(e);
            } finally {
                if (connection != null) {
                    super.closeConnection(connection);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
