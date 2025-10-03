package unoeste.fipp.mercadofipp.services.Obserever;

public interface SujeitoRepository {
    void addObserver(ObserverRepository observer);
    void removerObserver(ObserverRepository observer);
    void notifyObservers();
}
