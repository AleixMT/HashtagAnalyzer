public class Piulada implements Comparable<Piulada>{
    private String missatge;
    private Data data;
    private String codiUsuari;

    public Piulada(String missatge, Data data, String codiUsuari) {
        this.missatge = missatge;
        this.data = data;
        this.codiUsuari = codiUsuari;
    }

    public String getMissatge() {
        return missatge;
    }

    public void setMissatge(String missatge) {
        this.missatge = missatge;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getCodiUsuari() {
        return codiUsuari;
    }

    public void setCodiUsuari(String codiUsuari) {
        this.codiUsuari = codiUsuari;
    }

    public int compareTo(Piulada p) {
        return (this.data.getDia() + this.data.getMes() + this.data.getAny() + this.codiUsuari + this.missatge).compareTo(p.getData().getDia()+p.getData().getMes()+p.getData().getAny()+p.getCodiUsuari()+p.getMissatge());
    }

    @Override
    public String toString() {
        String s = "";
        s += "\n" + this.data.getDia() + "/" + this.data.getMes() + "/" + this.data.getAny() + " " + this.codiUsuari + " " + this.missatge;
        return s;
    }
}
