public non-sealed class  Staff extends Utente{

    public Staff(String nome, String cognome, String password, String Email, int tipo){
        super(nome, cognome, password, Email, tipo);
    }

    @Override
    public void toDB() {
        Sql_staff inserimento = new Sql_staff();
        int tipo=super.getTipo();
        String email = super.getEmail();
        String password = super.getPassword();
        String nome= super.getNome();
        String cognome = super.getCognome();
       inserimento.Insert_staff(email, nome, cognome,password, tipo);
    }


}
