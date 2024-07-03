//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Selezione_errata , DBmiss{
        Scanner Stdinput = new Scanner(System.in);
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        System.out.println("\t***MULTI SALA CORRIERI***\n\nCosa si vuole fare?\n1)Nuova registrazione\n2)Accedi");
        int x = Stdinput.nextInt();
        int who=0;
        switch(x){
            case 1:
            {
                System.out.println("Creazione nuovo utente inserire i dati\n\nNome:");
                Stdinput.nextLine();
                String nome_utente = Stdinput.nextLine();
                System.out.println("\nCognome: ");
                String Cognome_utente = Stdinput.nextLine();
                System.out.println("\nEmail: ");
                String email_utente = Stdinput.nextLine();
                System.out.println("\nPassword: ");
                String password_utente = Stdinput.nextLine();
                System.out.println("\nChi si sta registrando\n1)Cliente\n2)staff ");
                int scelta = Stdinput.nextInt();
                int tipo;
                String nr_carta;
                switch(scelta) {
                    case 1: {
                        Stdinput.nextLine(); //simil fflush
                        tipo = 0;
                        System.out.println("\nnr carta di credito: ");
                        nr_carta = Stdinput.nextLine();
                        Cliente basic = new Cliente(nome_utente, Cognome_utente, password_utente, email_utente, tipo, nr_carta);
                        basic.toDB();
                        break;
                    }
                    case 2: {
                        tipo = 1;
                        Staff basic = new Staff(nome_utente, Cognome_utente, password_utente, email_utente, tipo);
                        basic.toDB();
                        break;
                    }
                    default:
                        throw new Selezione_errata(scelta);
                }
                break;
            }



            case 2:
                Stdinput.nextLine();
                System.out.println("Inserire email");
                String email_utente = Stdinput.nextLine();
                System.out.println("Inserire password");
                String password_utente = Stdinput.nextLine();
                System.out.println("\nChi vuole accedere?\n1)Admin\n2)Utente");
                int selezione = Stdinput.nextInt();
                switch(selezione) {
                    case 1:
                        SelectSql_staff selection_staff = new SelectSql_staff();
                        Staff staff = selection_staff.getStaff(email_utente,password_utente);
                        who=1;
                        break;
                    case 2:
                        SelectSql_cliente selection = new SelectSql_cliente();
                        Cliente cliente = selection.getCliente(email_utente,password_utente);
                        who=2;
                        break;

                    default:
                        throw new Selezione_errata(x);
                }
                break;
            default:
                throw new Selezione_errata(x);
        }

        switch(who){
            case 1:  //staff

                        System.out.println("Cosa si vuole fare?\n1)aggiungi film\n2)rimuovi film\n3)stampa film");
                        int scelta=Stdinput.nextInt();
                        switch (scelta){
                            case 1:
                                Stdinput.nextLine();
                                System.out.println("\nInserire titolo: ");
                                String titolo = Stdinput.nextLine();
                                System.out.println("\nInserire categoria: ");
                                String categoria = Stdinput.nextLine();
                                System.out.println("\nInserire durata: ");
                                double durata = Stdinput.nextDouble();
                                Film film = new Film(titolo,categoria,durata);
                                film.saveDB();
                                break;
                            case 2:
                                Stdinput.nextLine();
                                System.out.println("\nInserire titolo da rimuovere: ");
                                String titolo_r = Stdinput.nextLine();
                                DeleteSql_film film_r = new DeleteSql_film();
                                film_r.delete_film(titolo_r);
                                break;
                            case 3:
                                PrintSql_film film_p = new PrintSql_film();
                                film_p.print_film();
                                break;
                            default:
                                throw new Selezione_errata(scelta);

                        }
                    break;


            case 2: //clienti
                System.out.println("Cosa si vuole fare?\n1)acquista film\n2)stampa film\n3)visualizza acquisti");
                int scelta1=Stdinput.nextInt();
                switch (scelta1){
                    case 1:
                        Stdinput.nextLine();
                        System.out.println("\nInserire titolo: ");
                        String titolo = Stdinput.nextLine();
                        System.out.println("\ninserire nr posti (max 4): ");
                        int nr_posti = Stdinput.nextInt();
                        Stdinput.nextLine();
                        while(nr_posti > 4){
                            System.out.println("Troppi posti selezionati, max 4");
                            nr_posti = Stdinput.nextInt();
                        }
                        System.out.println("\nInserire la propria email: ");
                        String email = Stdinput.nextLine();
                        InsertBiglietto_Sql inserimento = new InsertBiglietto_Sql();
                        inserimento.insert_biglietto(titolo,email,nr_posti);
                        break;
                    case 2:
                        PrintSql_film film_p = new PrintSql_film();
                        film_p.print_film();
                        break;
                    case 3:
                        Stdinput.nextLine();
                        System.out.println("\nInserire la propria email: ");
                        String email_utente = Stdinput.nextLine();
                        PrintBiglietti_Sql printBigliettiSql = new PrintBiglietti_Sql();
                        printBigliettiSql.print_biglietti(email_utente);
                        break;
                    default:
                        throw new Selezione_errata(scelta1);

                }
                break;


            default:
                throw new Selezione_errata(who);

        }

    }

}
