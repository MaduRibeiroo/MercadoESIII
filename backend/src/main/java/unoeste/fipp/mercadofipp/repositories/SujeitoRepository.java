package unoeste.fipp.mercadofipp.repositories;

public interface SujeitoRepository {
    void addObserver(ObserverRepository observer);
    void removerObserver(ObserverRepository observer);
    void notifyObservers();
}
