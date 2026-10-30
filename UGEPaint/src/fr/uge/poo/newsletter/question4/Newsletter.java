package fr.uge.poo.newsletter.question4;

import com.evilcorp.eemailer.EEMailer;
import fr.uge.poo.newsletter.User;

import java.util.HashMap;
import java.util.Objects;

public class Newsletter {

    private final String name;
    private final UserValidator validator;
    private final HashMap<String, User> suscribers = new HashMap<>();
    private final Mailer mailer;

    public Newsletter(String name, Mailer mailer) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(mailer);
        this.name = name;
        this.mailer = mailer;
        this.validator = (user -> true);
    }

    public Newsletter(String name, Mailer mailer, UserValidator validator) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(mailer);
        Objects.requireNonNull(validator);
        this.name = name;
        this.mailer = mailer;
        this.validator = validator;
    }

    public void suscribe(User user) {
        Objects.requireNonNull(user);
        if (validator.isValid(user)) {
            suscribers.putIfAbsent(user.email(), user);
        } else {
            System.err.println("[" + name + "] could not register user " + user.name() + "<" + user.email() + ">");
        }
    }

    public void unsuscribe(User user) {
        Objects.requireNonNull(user);
        suscribers.remove(user.email());
    }

    public void sendMessage(String title, String content) {
        var subject = "[" + name + "] " + title;
        for (var email : suscribers.keySet()) {
            mailer.send(email, subject, content);
        }
    }

    static void main() {
        var newsletter1 = new Newsletter("Harry 4ever", new EEMailerAdapter(), new UserValidatorBuilder().nationality(User.Nationality.BRITISH).ageMin(18).build());
        var newsletter2 = new Newsletter("Java 4ever", new GMailerAdapter(), new UserValidatorBuilder().nationality(User.Nationality.BRITISH, User.Nationality.FRENCH).ageMin(21).build());
        var newsletter3 = new Newsletter("Why me!", new EEMailerAdapter(), (user -> user.age() % 2 == 0 && user.email().endsWith("@univ-eiffel.fr")));

        var user1 = new User("Theo", "theo.constant@univ-eiffe.fr", 21, User.Nationality.BRITISH);

        newsletter2.suscribe(user1);
        newsletter2.sendMessage("Remi Forax is the GOAT", "Everything is said in the title. I mean... isn't it obvious? :D");
    }
}
