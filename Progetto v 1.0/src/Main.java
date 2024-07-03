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
                System.out.println("Cosa si vuole fare?\n1)modifica film\n2)modifica costo biglietti\n");
                int scelta = Stdinput.nextInt();
                switch (scelta){
                    case 1:
                        System.out.println("Cosa si vuole fare?\n1)aggiungi film\n2)rimuovi film\n");
                        scelta=Stdinput.nextInt();
                        switch (scelta){
                            case 1:
                                System.out.println("\nInserire titolo\n");
                                String titolo = Stdinput.nextLine();
                                System.out.println("\nInserire categoria\n");
                                String categoria = Stdinput.nextLine();
                                System.out.println("\nInserire durata\n");
                                double durata = Stdinput.nextDouble();
                                Film film = new Film(titolo,categoria,durata);
                                film.saveDB();
                                break;
                            case 2:
                                /*TODO*/
                                break;
                            default:
                                throw new Selezione_errata(scelta);

                        }
                    break;

                    default:
                        throw new Selezione_errata(scelta);
                }
                break;


            default:
                throw new Selezione_errata(who);

        }



        
        }

    }
