import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrintBiglietti_Sql extends Sql{
    private Connection connection;

    public PrintBiglietti_Sql(){
        super();
    }
    @Override
    public void set_connection() throws SQLException {
        this.connection=super.openConnection();
    }

    public void print_biglietti(String email, PrintWriter out) {
        try {
            set_connection();
            String query = "SELECT * FROM `Biglietti` WHERE `email_utente` = ? ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (!resultSet.next()) {
                        throw new DBmiss("Nessun Biglietto trovato");
                    } else {
                        do {
                            String filmTitolo = resultSet.getString("titolo_film");
                            int nr_posti = resultSet.getInt("nr_posti");
                            out.println("test");
                            out.println("Titolo: " + filmTitolo+"    nr posti acquistati: "+nr_posti);
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
