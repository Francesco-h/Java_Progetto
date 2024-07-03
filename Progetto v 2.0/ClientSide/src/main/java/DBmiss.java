public class DBmiss  extends Exception{

    public DBmiss(){
        super("Password e/o Email errati");
    }
    public DBmiss(String err){
        super(err);
    }
}
