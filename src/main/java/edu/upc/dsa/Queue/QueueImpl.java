package edu.upc.dsa.Queue;

public class QueueImpl<E> implements Queue<E> {
    private E[] data;
    private int p;
    public QueueImpl(int length){
        this.data = (E[])new Object[length];
        this.p=0;
    }
    public void push(E element) throws FullQueueException {
        if(this.p != this.data.length){
            this.data[this.p] = element;
            this.p++;
        }
        else{
            throw new FullQueueException();
        }
    }

    /**
     *
     * @return
     * @throws EmptyQueueException
     */
    public E pop() throws EmptyQueueException {
        E element = this.data[0];
        if(this.p == 0){
            throw new EmptyQueueException();
        }
        else{
            if(this.data[1] != null){
                for(int i = 0; i < this.data.length -1; i++){
                    this.data[i] = this.data[i+1];
                }
            }
            else{
                this.data[0] = this.data[1];
            }
            this.p--;
        }

        return element;
    }
    public int size(){
        return this.data.length;
    }

    public int num(){return this.p;}
}

