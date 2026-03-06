package DSA;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

class TokenBucket {

    private final double capacity;
    private final double refillRatePerSecond;

    private static class State {
        final double tokens;
        final long lastRefillTime;

        State(double tokens, long lastRefillTime) {
            this.tokens = tokens;
            this.lastRefillTime = lastRefillTime;
        }
    }

    private final AtomicReference<State> state;

    public TokenBucket(double capacity, double refillRatePerSecond) {
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
        this.state = new AtomicReference<>(
                new State(capacity, System.nanoTime())
        );
    }

    public boolean allowRequest() {
        while (true) {

            State current = state.get();
            long now = System.nanoTime();

            // Calculate refill
            double elapsedSeconds =
                    (now - current.lastRefillTime) / 1_000_000_000.0;

            double newTokens =
                    Math.min(capacity,
                            current.tokens + elapsedSeconds * refillRatePerSecond);

            if (newTokens < 1) {
                // Not enough tokens
                return false;
            }

            State newState =
                    new State(newTokens - 1, now);

            if (state.compareAndSet(current, newState)) {
                return true; // success
            }

            // else retry
        }
    }
}