package DSA;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class Token {
    double currentState;
    long lastUpdateTimeStamp;
    int fillRate;

    public Token(int fillRate, long lastUpdateTimeStamp) {
        this.fillRate = fillRate;
        this.lastUpdateTimeStamp = System.nanoTime();
        this.currentState = 100;
    }
}

public class TokeBucket {

    Map<String, Token> store = new ConcurrentHashMap<>();


    boolean allowRequest(String user) {
        long timeNow = System.nanoTime();
        Token token = store.computeIfAbsent(user, ignore -> new Token(100, timeNow));
        synchronized (token) {
            double elapsedTimeStamp = timeNow - token.lastUpdateTimeStamp;
            double tokensToAdd = (elapsedTimeStamp / 1000_000_000.0) * token.fillRate;
            token.currentState = Math.min(100, token.currentState + tokensToAdd);
            token.lastUpdateTimeStamp = timeNow;
            if (token.currentState >= 1) {
                token.currentState--;
                return true;
            }
            return false;
        }
    }
}
