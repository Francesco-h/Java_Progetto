public class DBmiss  extends Exception{

    public DBmiss(){
        super("Password e/o Email errati");
    }

    public DBmiss(String x){
        super("Elemento non trovato");
    }
}
