abstract class Utente {
    private String Password;
    private String cognome;
    private String nome;
    private String email;
    private int tipo;


    public Utente(String nome, String cognome, String password, String Email, int tipo) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = Email;
        this.Password = password;
        this.tipo = tipo;
    }

    //metodo getter
    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {

        return Password;
    }

    public int getTipo() {

        return tipo;
    }

    //metodi setter
    public void setPassword(String password) {
        this.Password = password;
    }

    public void setNome(String name) {
        this.nome = name;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public abstract void toDB();
}