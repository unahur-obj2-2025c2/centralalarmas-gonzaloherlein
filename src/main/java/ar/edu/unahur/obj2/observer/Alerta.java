package ar.edu.unahur.obj2.observer;

public class Alerta {
    private final String tipo;
    private final Integer nivel;

    public Alerta(String tipo, Integer nivel) {
        this.tipo = tipo;
        this.nivel = nivel;
    }

    public Boolean esCritica(){
        return Boolean.valueOf(nivel >= 8);
    }

    public String getTipo() {
        return tipo;
    }

    public Integer getNivel() {
        return nivel;
    }

    
}
