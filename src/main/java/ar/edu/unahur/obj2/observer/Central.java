package ar.edu.unahur.obj2.observer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Central implements Observable{

    private Map<Alerta, Integer> registroDeAlertas = new HashMap<>();
    private Set<IObservador> entidades = new HashSet<>();

    public void emitirAlerta(String tipo, Integer nivel){
        if(nivel < 1 || nivel > 10){
            throw new NivelDeAlertaIncorrectoException("Nivel de alerta incorrecto");
        }
        Alerta alerta = new Alerta(tipo,nivel);
        registroDeAlertas.put(alerta, entidades.size());
        this.notificar(alerta);
    }

    public Integer cantidadDeNotificacionesEnviadas(){
        return registroDeAlertas.values().stream().mapToInt(Integer::intValue).sum();
    }

    @Override
    public void agregarObservador(IObservador observador) {
        entidades.add(observador);
    }

    @Override
    public void quitarObservador(IObservador observador) {
        entidades.remove(observador);
    }

    @Override
    public void notificar(Alerta alerta) {
        entidades.forEach(o -> o.actualizar(alerta));
    }

    public Map<Alerta, Integer> getRegistroDeAlertas() {
        return registroDeAlertas;
    }

    
}
