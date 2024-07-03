public class Cliente extends Utente {

    private String nr_carta;

    public Cliente(String nome, String cognome, String password, String Email, int tipo, String nr_carta) {
        super(nome, cognome, password, Email, tipo);
        this.nr_carta = nr_carta;
    }

    public String getNr_carta(){
        return this.nr_carta;
    }

    public void setNr_carta(String nr_carta){
        this.nr_carta=nr_carta;
    }

        @Override
        public void toDB() {
            InsertSql_cliente inserimento = new InsertSql_cliente();
            int tipo=super.getTipo();
            String email = super.getEmail();
            String password = super.getPassword();
            String nome= super.getNome();
            String cognome = super.getCognome();
            inserimento.insert_clienti(email, nome, cognome,password, tipo,this.nr_carta);
        }

        }



