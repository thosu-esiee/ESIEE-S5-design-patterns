package fr.uge.poo.newsletter.question2;

import com.evilcorp.eemailer.EEMailer;
import fr.uge.poo.newsletter.User;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Newsletter {
    private final String name;
    private final Predicate<User> predicate;
    private final HashMap<String, User> suscribers = new HashMap<>();
    private final EEMailer mailer = new EEMailer();

    public Newsletter(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        this.predicate = (user -> true);
    }

    public Newsletter(String name, Predicate<User> predicate) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(predicate);
        this.name = name;
        this.predicate = predicate;
    }

    public void suscribe(User user) {
        Objects.requireNonNull(user);
        if (predicate.test(user)) {
            suscribers.putIfAbsent(user.email(), user);
        } else {
            IO.println("[" + name + "] could not register user " + user.name() + "<" + user.email() + ">");
        }
    }

    public void unsuscribe(User user) {
        Objects.requireNonNull(user);
        suscribers.remove(user.email());
    }

    public void sendMessage(String title, String content) {
        var subject = "[" + name + "] " + title;
        for (var email : suscribers.keySet()) {
            mailer.send(new EEMailer.Mail(email, subject, content));
        }
    }

    static void main() {
        var newsletter1 = new Newsletter("Harry 4ever", (user -> user.nationality() == User.Nationality.BRITISH && user.age() >= 18));
        var newsletter2 = new Newsletter("Java 4ever", (user -> List.of(User.Nationality.FRENCH, User.Nationality.BRITISH).contains(user.nationality()) && user.age() >= 21));
        var newsletter3 = new Newsletter("Why me!", (user -> user.age() % 2 == 0 && user.email().endsWith("@univ-eiffel.fr")));
    }
}
