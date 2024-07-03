import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Selezione_errata, DBmiss {
        String hostName = "localhost";
        int port = 12345;

        try (Socket socket = new Socket(hostName, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            System.out.println("***BENVENUTO***\n\n\nCosa si desidera fare?\n1)registrarsi\n2)accedere\n");
            Scanner scanner = new Scanner(System.in);
            int select = scanner.nextInt();
            out.println(select);
            scanner.nextLine(); //fflush
            switch(select) {

                //Registrazione
                case 1:
                System.out.print("inserire il nome: ");
                String firstName = scanner.nextLine();
                System.out.print("inserire cognome: ");
                String lastName = scanner.nextLine();
                System.out.print("inserire email: ");
                String email = scanner.nextLine();
                System.out.print("inserire password: ");
                String Password = scanner.nextLine();
                System.out.print("selezionare il tipo:\n1)cliente\n2)staff");
                int tipo = scanner.nextInt();
                scanner.nextLine();
                String nr_carta = "0";
                if (tipo == 1) {
                    tipo = 0;
                    System.out.print("inserire nr_carta : ");
                    nr_carta = scanner.nextLine();
                }

                //invio dati
                out.println(firstName);
                out.println(lastName);
                out.println(email);
                out.println(Password);
                out.println(tipo);
                if (tipo == 0)
                    out.println(nr_carta);
                break;
                case 2:
                    //accesso
                    int who =-1;
                    System.out.println("Inserire email");
                    String email_utente = scanner.nextLine();
                    System.out.println("Inserire password");
                    String password_utente = scanner.nextLine();
                    System.out.println("\nChi vuole accedere?\n1)Staff\n2)Cliente\n");
                    tipo = scanner.nextInt();
                    scanner.nextLine();
                    out.println(email_utente);
                    out.println(password_utente);
                    out.println(tipo);
                    boolean valid = Boolean.parseBoolean(in.readLine());
                    if(valid){
                        System.out.println("accesso fatto\n");
                        who = tipo;
                    }
                    else
                        System.out.println("accesso non fatto\n");

                        switch(who){
                            case 1: //accesso fatto staff
                                out.println(who);
                                System.out.println("Cosa si vuole fare?\n1)aggiungi film\n2)rimuovi film\n3)stampa film");
                                int scelta=scanner.nextInt();
                                switch (scelta){
                                    case 1:
                                        out.println(scelta);
                                        scanner.nextLine();
                                        System.out.println("\nInserire titolo: ");
                                        String titolo = scanner.nextLine();
                                        System.out.println("\nInserire categoria: ");
                                        String categoria = scanner.nextLine();
                                        System.out.println("\nInserire durata: ");
                                        double durata = scanner.nextDouble();
                                        out.println(titolo);
                                        out.println(categoria);
                                        out.println(durata);
                                        break;
                                    case 2:
                                        out.println(scelta);
                                        scanner.nextLine();
                                        System.out.println("\nInserire titolo da rimuovere: ");
                                        String titolo_r = scanner.nextLine();
                                        out.println(titolo_r);
                                        break;
                                    case 3:
                                        out.println(scelta);
                                        break;
                                    default:
                                        throw new Selezione_errata(scelta);

                                }
                                break;
                            case 2: //accesso fatto cliente
                                out.println(who);
                                System.out.println("Cosa si vuole fare?\n1)acquista film\n2)visualizza acquisti\n3)stampa film");
                                int scelta1=scanner.nextInt();
                                switch (scelta1){
                                    case 1:
                                        out.println(scelta1);
                                        scanner.nextLine();
                                        System.out.println("\nInserire titolo: ");
                                        String titolo = scanner.nextLine();
                                        System.out.println("\ninserire nr posti (max 4): ");
                                        int nr_posti = scanner.nextInt();
                                        scanner.nextLine();
                                        while(nr_posti > 4){
                                            System.out.println("Troppi posti selezionati, max 4");
                                            nr_posti = scanner.nextInt();
                                        }
                                        System.out.println("\nInserire la propria email: ");
                                        String email_user = scanner.nextLine();
                                        out.println(titolo);
                                        out.println(nr_posti);
                                        out.println(email_user);

                                        break;
                                    case 2:
                                        out.println(scelta1);
                                        scanner.nextLine();
                                        System.out.println("\nInserire la propria email: ");
                                        String emails = scanner.nextLine();
                                        out.println(emails);

                                        break;
                                    case 3:
                                        out.println(scelta1);
                                        break;
                                    default:
                                        throw new Selezione_errata(scelta1);
                                }
                                break;
                            default:
                                throw new DBmiss();
                        }


                    break;
                default:
                    throw new Selezione_errata(select);
            }

        } catch (IOException e) {
            System.err.println("Impossibile connettersi al server: " + e.getMessage());
        }
    }
}
