package ar.edu.unahur.obj2.observer;

public interface Observable {
    void agregarObservador(IObservador observador);
    void quitarObservador(IObservador observador);
    void notificar(Alerta alerta);
}
