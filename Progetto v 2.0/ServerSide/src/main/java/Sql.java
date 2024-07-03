import java.sql.*;
public abstract  class Sql {
    private final String url = "jdbc:mysql://ciccionasrpi.webredirect.org:3306/Java_Cinema";
    private final String username = "root";
    private final String password = "2587";
    private static Connection connection;

    public String getUrl() {
        return url;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public abstract void set_connection() throws SQLException;

    public Connection openConnection() throws SQLException {
        try {
            // Caricare il driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Aprire la connessione
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connessione al database stabilita con successo.");

        } catch (ClassNotFoundException e) {
            System.err.println("Errore: Driver JDBC non trovato.");
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(Connection connection1) throws SQLException {
        if (connection1 != null && !connection1.isClosed()) {
            connection1.close();
            System.out.println("Connessione al database chiusa con successo.");
        }
    }
}
