import io.reactivex.Observable;
import java.util.*;
import io.reactivex.subjects.*;
class Hangman {
    String secret = "";
    String discovered = "";
    Set<String> guess = new HashSet<>();
    Set<String> misses = new HashSet<>();
    List<Part> parts = new ArrayList<>();
    Status status = Status.PLAYING;
    String throwString = "";
    ReplaySubject<Output> subject;

    public void emptyAll() {
        secret = "";
        discovered = "";
        guess = new HashSet<>();
        misses = new HashSet<>();
        parts = new ArrayList<>();
        status = Status.PLAYING;
    }
    public void emit() {
        if (!throwString.isEmpty()) {
            subject.onError(new IllegalArgumentException(throwString));
            subject.onComplete();
        }
        Output newOut = new Output(
                secret,
                discovered,
                guess,
                misses,
                parts,
                status);
        subject.onNext(newOut);
    }

    public Observable<Output> play(Observable<String> word, Observable<String> letters) {
        subject = ReplaySubject.create();

        word.subscribe(this::setSecret);
        letters.subscribe(
                this::letter,
                System.err::println,
                () -> {subject.onComplete();}
        );
        return subject;
    }
    public void setSecret(String secret) {
        this.secret = secret;
        discovered = "_".repeat(secret.length());
        emit();
    }
    public void letter(String c) {
        if (guess.contains(c) || misses.contains(c)) {
            throwString = "Letter " + c + " was already played";
        }
        StringBuilder result = new StringBuilder();
        boolean found = false;
        boolean win = true;
        for (int i = 0; i < secret.length(); i++) {
            if (discovered.charAt(i) != '_') {
                result.append(discovered.charAt(i));
                continue;
            }
            if (secret.charAt(i) == c.charAt(0)) {
                result.append(c.charAt(0));
                guess.add(c);
                found = true;
                continue;
            }
            win = false;
            result.append("_");
        }
        if (!found) {
            misses.add(c);
            if (status != Status.LOSS) {
                parts.add(Part.values()[parts.size()]);

                if (parts.size() > 5) {
                    status = Status.LOSS;
                }
            }
        }
        if (win) {
            status = Status.WIN;
        }
        discovered = result.toString();
        emit();

        if (status == Status.WIN || status == Status.LOSS) {
            emptyAll();
        }
    }
}