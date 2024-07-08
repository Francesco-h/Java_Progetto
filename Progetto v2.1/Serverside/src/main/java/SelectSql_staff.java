import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectSql_staff extends Sql{
    Connection connection;
    @Override
    public void set_connection() throws SQLException {
        this.connection=super.openConnection();
    }

    public Staff getStaff(String email, String Password) throws DBmiss{
        Staff staff = null;
        String query = "SELECT * FROM `Utente` WHERE `email` = ? AND `Password` = ? AND `Tipo` = 1";
        int num=0;
        try {
            this.set_connection();
        } catch (SQLException e) {
            e.printStackTrace();}
        try{
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);

            preparedStatement.setString(1, email );
            preparedStatement.setString(2, Password );
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                num++;
                String nome = resultSet.getString("Nome");
                String cognome = resultSet.getString("Cognome");
                String password = resultSet.getString("Password");
                String email_utente = resultSet.getString("Email");
                staff = new Staff(nome,cognome,password,email_utente,0);

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
            throw new DBmiss();
        }
        return staff;
    }
}
