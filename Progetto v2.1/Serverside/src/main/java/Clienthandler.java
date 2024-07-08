import java.io.*;
import java.net.Socket;

public class Clienthandler extends Thread {
    private Socket clientSocket;
    private int nr_client;
    public Clienthandler(Socket clientSocket,int nr_client) {
        this.clientSocket = clientSocket;
        this.nr_client =nr_client;
    }

    @Override
    public void run(){
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            int scelta = Integer.parseInt(in.readLine());
            switch (scelta) {
                case 1:
                    // registrazione cliente/staff
                    String Nome = in.readLine();
                    String Cognome = in.readLine();
                    String email = in.readLine();
                    String Password = in.readLine();
                    String nr_carta = "0";
                    int tipo = Integer.parseInt(in.readLine());
                    if (tipo == 0) {
                        nr_carta = in.readLine();
                        Cliente basic = new Cliente(Nome, Cognome, Password, email, tipo, nr_carta);
                        basic.toDB();
                    } else if (tipo == 1) {
                        Staff basic = new Staff(Nome, Cognome, Password, email, tipo);
                        basic.toDB();
                    }
                    break;
                case 2:
                    // accesso cliente/staff
                    email = in.readLine();
                    Password = in.readLine();
                    boolean test;
                    tipo = Integer.parseInt(in.readLine());
                    if (tipo == 1) {
                        System.out.println("\nTento di fare l'accesso come staff per il client : "+nr_client+"\n");
                        SelectSql_staff selection_staff = new SelectSql_staff();
                        Staff staff = selection_staff.getStaff(email, Password);
                        if (staff != null) {
                            test = true;
                            out.println(test);
                        } else {
                            test = false;
                            out.println(test);
                        }
                    } else if (tipo == 2) {
                        System.out.println("\nTento di fare l'accesso come cliente per il client : "+nr_client+"\n");
                        SelectSql_cliente selection = new SelectSql_cliente();
                        Cliente cliente = selection.getCliente(email, Password);
                        if (cliente != null) {
                            test = true;
                            out.println(test);
                        } else {
                            test = false;
                            out.println(test);
                        }
                    } else {
                        test = false;
                        out.println(test);
                    }
                    break;
                default:
                    throw new Selezione_errata(scelta);
            }

            int accesso = Integer.parseInt(in.readLine());

            switch (accesso) {
                case 1: // accesso staff
                    int scelta1 = Integer.parseInt(in.readLine());

                    switch (scelta1) {
                        case 1: // aggiungi film
                            String titolo = in.readLine();
                            String categoria = in.readLine();
                            double durata = Double.parseDouble(in.readLine());
                            Film film = new Film(titolo, categoria, durata);
                            film.saveDB();
                            break;
                        case 2:
                            String titolo_r = in.readLine();
                            DeleteSql_film film_r = new DeleteSql_film();
                            film_r.delete_film(titolo_r);
                            break;
                        case 3:
                            PrintSql_film film_p = new PrintSql_film();
                            film_p.print_film(out);
                            break;
                        default:
                            throw new Selezione_errata(scelta1);
                    }
                    break;
                case 2: // accesso cliente
                    int scelta2 = Integer.parseInt(in.readLine());
                    switch (scelta2) {
                        case 1:
                            String titolo = in.readLine();
                            String email = in.readLine();
                            int nr_posti = Integer.parseInt(in.readLine());
                            InsertBiglietto_Sql inserimento = new InsertBiglietto_Sql();
                            inserimento.insert_biglietto(titolo, email, nr_posti);
                            break;
                        case 2:
                            String email_utente = in.readLine();
                            PrintBiglietti_Sql printBigliettiSql = new PrintBiglietti_Sql();
                            printBigliettiSql.print_biglietti(email_utente,out);
                            break;
                        case 3:
                            PrintSql_film film_p = new PrintSql_film();
                            film_p.print_film(out);
                            break;
                        default:
                            throw new Selezione_errata(scelta2);
                    }
                    break;
                default:
                    throw new Selezione_errata(accesso);
            }

        } catch (IOException | Selezione_errata | DBmiss e) {
            System.err.println("Errore client nr "+nr_client+": " + e.getMessage());
        }finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Errore chiusura sockert: " + e.getMessage());
            }
        }
    }
}
