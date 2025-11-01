package ar.edu.unahur.obj2.observer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.observer.riesgos.RiesgoPromedio;

public class CentralTest {
    private Central central;
    private Entidad e1;
    private Entidad e2;
    @BeforeEach
    void setUp(){
        central = new Central();
        e1 = new Entidad("Hospital Aleman");
        e2 = new Entidad("Hospital Italiano");
        central.agregarObservador(e1);
        central.agregarObservador(e2);
    }

    @Test
    void dadoElSetUp_alAgregarAlertas_SeVerificaCorretamenteLasAlertasRecibidasYElRiesgo() {
        central.emitirAlerta("calor",6);
        central.emitirAlerta("lluvia",8);
        assertEquals(10,e1.riesgo());
        assertEquals(10,e2.riesgo());

        assertEquals("calor", e1.getAlertasRecibidas().getFirst().getTipo());
        assertEquals("lluvia", e1.getAlertasRecibidas().getLast().getTipo());
        assertEquals(6, e1.getAlertasRecibidas().getFirst().getNivel());
        assertEquals(8, e1.getAlertasRecibidas().getLast().getNivel());

        assertEquals("calor", e2.getAlertasRecibidas().getFirst().getTipo());
        assertEquals("lluvia", e2.getAlertasRecibidas().getLast().getTipo());
        assertEquals(6, e2.getAlertasRecibidas().getFirst().getNivel());
        assertEquals(8, e2.getAlertasRecibidas().getLast().getNivel());
    }

    @Test
    void dadoElSetUp_alCambiarElComportamientoYAgregarAlertas_SeVerificaCorretamenteLasAlertasRecibidasYElRiesgo(){
        e1.setCriterioRiesgo(new RiesgoPromedio());
        central.emitirAlerta("calor",6);
        central.emitirAlerta("lluvia",8);

        assertEquals("calor", e1.getAlertasRecibidas().getFirst().getTipo());
        assertEquals("lluvia", e1.getAlertasRecibidas().getLast().getTipo());
        assertEquals(6, e1.getAlertasRecibidas().getFirst().getNivel());
        assertEquals(8, e1.getAlertasRecibidas().getLast().getNivel());

        assertEquals("calor", e2.getAlertasRecibidas().getFirst().getTipo());
        assertEquals("lluvia", e2.getAlertasRecibidas().getLast().getTipo());
        assertEquals(6, e2.getAlertasRecibidas().getFirst().getNivel());
        assertEquals(8, e2.getAlertasRecibidas().getLast().getNivel());

        assertEquals(7,e1.riesgo());
        assertEquals(10,e2.riesgo());
    }

    @Test
    void dadoElSetUp_alAgregarAlertasQuitarUnaEntidadYAgregaNuevaAlerta_SeVerificaCorretamenteLasAlertasRecibidasYElRiesgo(){
        central.emitirAlerta("calor",6);
        central.emitirAlerta("lluvia",8);
        central.quitarObservador(e1);
        central.emitirAlerta("granizo",7);

        assertEquals(2,e1.getAlertasRecibidas().size());
        assertEquals(10,e1.riesgo());

        assertEquals(3,e2.getAlertasRecibidas().size());
        assertEquals(7,e2.riesgo());

        assertEquals(5, central.cantidadDeNotificacionesEnviadas());
    }

    @Test
    void Excepción(){
        
        NivelDeAlertaIncorrectoException exception = Assertions.assertThrows(NivelDeAlertaIncorrectoException.class, () -> {
                central.emitirAlerta("calor",0);
            });
        assertEquals("Nivel de alerta incorrecto", exception.getMessage());
    }
}
