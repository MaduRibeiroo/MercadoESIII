package unoeste.fipp.mercadofipp.repositories;

public interface SujeitoRepository {
    void addObserver(Observer observer);
    void removerObserver(Observer observer);
    void notifyObservers();
}
