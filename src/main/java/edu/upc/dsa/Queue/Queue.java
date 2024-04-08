package edu.upc.dsa.Queue;

public interface Queue<E> {
    /**
     * Mètode que permet afegir/encuar un nou element a la cua
     * @param e l'element a "encuar"
     * @throws FullQueueException llença aquesta excepció en el cas que
     * la cua estigui plena
     */
    public void push(E e) throws FullQueueException;

    public E pop() throws EmptyQueueException;
    public int size();

    public int num();
}