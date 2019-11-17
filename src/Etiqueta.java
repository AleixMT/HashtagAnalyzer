import java.util.Objects;

public class Etiqueta implements Comparable<Etiqueta>{
    private String etiqueta;
    private TADLlistaGenerica<Piulada> piulades;

    public Etiqueta(String etiqueta, TADLlistaGenerica<Piulada> piulades) {
        this.etiqueta = etiqueta;
        this.piulades = piulades;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public TADLlistaGenerica<Piulada> getPiulades() {
        return piulades;
    }

    public void setPiulades(TADLlistaGenerica<Piulada> piulades) {
        this.piulades = piulades;
    }

    @Override
    public int compareTo(Etiqueta o) {
        return this.etiqueta.compareTo(o.getEtiqueta());
    }


    @Override
    public String toString() {
        String s = "";
        s += "\n" + this.etiqueta;
        s += this.piulades + "\n";
        return s;
    }
}
