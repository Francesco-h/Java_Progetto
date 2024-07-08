import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String args []){
        int nr_client = 0;
        int port = 12345;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Parser conf = new Parser();
            System.out.println(conf.getString("Name"));
            System.out.println(conf.getString("Indirizzo"));
            System.out.println("\nServer avviato sulla porta: " + port);

            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Nuovo client connesso nr " + nr_client);
                    nr_client=nr_client+1;
                    Clienthandler clientHandler = new Clienthandler(clientSocket,nr_client);
                    clientHandler.start();
                } catch (IOException e) {
                    System.err.println("Errore client " +(nr_client-1) +" : " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Errore server: " + e.getMessage());
        }
    }
}
