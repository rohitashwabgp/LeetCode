package DSA;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class Bucket {
    int leakRate;
    long lastUpdate;
    double currentState;

    public Bucket(int leakRate, long lastUpdate, double currentState) {
        this.leakRate = leakRate;
        this.lastUpdate = lastUpdate;
        this.currentState = currentState;
    }


}

public class LeakyBucket {
    Map<String, Bucket> store = new ConcurrentHashMap<>();
    final double capacity = 10.0;

    boolean allowRequest(String user) {
        long timeNow = System.nanoTime();
        Bucket bucket = store.computeIfAbsent(user, (ignore) -> new Bucket(10, timeNow, 0));
        synchronized (bucket) {
            long timeElapsed = timeNow - bucket.lastUpdate;
            bucket.currentState = Math.max(0, bucket.currentState - ((timeElapsed / 1000_000_000.0) * bucket.leakRate));
            bucket.lastUpdate = timeNow;
            if (bucket.currentState < capacity) {
                bucket.currentState++;
                return true;
            }
            return false;
        }
    }
}
