package edu.mum.cs.cs525.labs.exercises.project.framework;

public interface Observable {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
