import java.sql.*;

public class SelectSql_cliente extends Sql{
    private static Connection connection;


    public SelectSql_cliente() {
        super();

    }

    @Override
    public void set_connection() throws SQLException {
        this.connection=super.openConnection();
    }

    public Cliente getCliente(String email, String Password) throws DBmiss {
        Cliente cliente = null;
        String query = "SELECT * FROM `Utente` WHERE `email` = ? AND `Password` = ? AND `Tipo` = 0";
        int num=0;
        try {
            this.set_connection();
        } catch (SQLException e) {
                e.printStackTrace();}
        try{
             PreparedStatement preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, email );
                preparedStatement.setString(2, Password );
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    num++;
                    String nome = resultSet.getString("Nome");
                    String cognome = resultSet.getString("Cognome");
                    String password = resultSet.getString("Password");
                    String email_utente = resultSet.getString("Email");
                    String nr_carta = resultSet.getString("nr_carta");
                    cliente = new Cliente(nome,cognome,password,email_utente,0,nr_carta);
                }

            }   catch (SQLException e) {
                    e.printStackTrace();
        }

        try {
            super.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(num==0){
            throw  new DBmiss();
        }
        return cliente;
    }
}
