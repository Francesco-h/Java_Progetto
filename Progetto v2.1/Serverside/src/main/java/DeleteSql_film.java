import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteSql_film extends Sql{
        private static Connection connection;

        public DeleteSql_film() {
            super();

        }
        @Override
        public void set_connection() throws SQLException {
            this.connection=super.openConnection();
        }

        public void delete_film(String titolo) {
            try{
                this.set_connection();
            }catch (SQLException e){
                e.printStackTrace();
            }

            try {
                String query = "DELETE FROM `Film` WHERE `titolo` = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                System.out.println("titolo: "+ titolo);
                preparedStatement.setString(1, titolo);

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Insert effettuata con successo");
                }
                else
                    throw new DBmiss("non trovato");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (DBmiss e) {
                throw new RuntimeException(e);
            }
            try{
                super.closeConnection(connection);
            }catch(SQLException e){
                e.printStackTrace();
            }

        }

    }


