package unoeste.fipp.mercadofipp.services.obserever;

public interface Subjects {
    void addObserver(Observers observer);
    void removerObserver(Observers observer);
    void notifyObservers();
}
