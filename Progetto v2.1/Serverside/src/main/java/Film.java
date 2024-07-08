public class Film{

    String titolo;
    String categoria;
    double durata;
    public Film(String titolo, String categoria, double durata){
        this.titolo=titolo;
        this.durata=durata;
        this.categoria=categoria;

    }


    public double getDurata() {
        return durata;
    }


    public String getTitolo() {
        return titolo;
    }


    public String getCategoria() {
        return categoria;
    }


    public void setCategoria(String categoria) {
        this.categoria=categoria;
    }


    public void setTitolo(String titolo) {
            this.titolo=titolo;
    }


    public void setDurata(double durata) {
            this.durata=durata;
    }
    @Override
    public String toString(){
        System.out.println("Titolo: "+titolo+"\ncategoria: "+categoria+"\ndurata :"+durata+"h");
        return null;
    }


    public void saveDB() {
        InsertSql_film inserimento = new InsertSql_film();
        String titolo = getTitolo();
        String categoria = getCategoria();
        double durata= getDurata();
        inserimento.insert_film(titolo,categoria,durata);
    }
}
