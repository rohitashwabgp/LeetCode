package DSA;

public class ProducerConsumer<T> {

    private final Object[] buffer;
    private int count = 0;
    private int putIndex = 0;
    private int takeIndex = 0;

    public ProducerConsumer(int capacity) {
        buffer = new Object[capacity];
    }


    private synchronized void producer(T item) throws InterruptedException {
        while (count == buffer.length) {
            wait();
        }
        System.out.println("producer running - before dropping message ");
        buffer[putIndex] = item;
        putIndex = (putIndex + 1) % buffer.length;
        count++;
        notifyAll();
        System.out.println("producer completing - after dropping message - val " + item);

    }

    private synchronized Object consumer() throws InterruptedException {
        while (count == 0) {
            wait();
        }
        System.out.println("consumer running - before dropping message ");
        T item = (T) buffer[takeIndex];
        buffer[takeIndex] = null;
        takeIndex = (takeIndex + 1) % buffer.length;
        count--;

        notifyAll(); // wake producers
        return item;
    }


    public static void main(String[] args) {

    }
}
