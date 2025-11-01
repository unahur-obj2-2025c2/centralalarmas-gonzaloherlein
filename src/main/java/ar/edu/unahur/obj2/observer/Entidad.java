package ar.edu.unahur.obj2.observer;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.observer.riesgos.Criterio;
import ar.edu.unahur.obj2.observer.riesgos.RiesgoCritico;

public class Entidad implements IObservador{

    private final String nombre;
    private List<Alerta> alertasRecibidas = new ArrayList<>();
    private Criterio criterioRiesgo  = new RiesgoCritico();

    public Entidad(String nombre) {
        this.nombre = nombre;
    }

    public Double riesgo(){
        return alertasRecibidas.isEmpty() ? 0.0 : criterioRiesgo.obtenerRiesgo(alertasRecibidas);
    }

    @Override
    public void actualizar(Alerta alerta) {
        alertasRecibidas.add(alerta);
    }

    public void setCriterioRiesgo(Criterio criterioRiesgo) {
        this.criterioRiesgo = criterioRiesgo;
    }

    public List<Alerta> getAlertasRecibidas() {
        return alertasRecibidas;
    }

    public Criterio getCriterioRiesgo() {
        return criterioRiesgo;
    }

    
}
