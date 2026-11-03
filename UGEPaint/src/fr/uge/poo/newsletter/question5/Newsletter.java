package fr.uge.poo.newsletter.question5;

import fr.uge.poo.newsletter.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Newsletter {

    private final String name;
    private final UserValidator validator;
    private final Mailer mailer;

    private final HashMap<String, User> suscribers = new HashMap<>();
    private final List<NewsletterObserver> observers = new ArrayList<>();

    public Newsletter(String name, Mailer mailer) {
        this(name, mailer, _ -> true);
    }

    public Newsletter(String name, Mailer mailer, UserValidator validator) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(mailer);
        Objects.requireNonNull(validator);
        this.name = name;
        this.mailer = mailer;
        this.validator = validator;
    }

    public String name() {
        return name;
    }

    public void register(NewsletterObserver observer) {
        Objects.requireNonNull(observer);
        observers.add(observer);
    }

    public void unregister(NewsletterObserver observer) {
        Objects.requireNonNull(observer);
        var isRemoved = observers.remove(observer);
        if (!isRemoved) {
            throw new IllegalStateException("This observer is not registered");
        }
    }

    private void notifySuscribeSuccess(User user) {
        for (var observer : observers) {
            observer.onSuscribeSuccess(this, user);
        }
    }

    private void notifySuscribeFailAlreadyExists(User user) {
        for (var observer : observers) {
            observer.onSuscribeFailAlreadyExists(this, user);
        }
    }

    private void notifySuscribeFailInvalid(User user) {
        for (var observer : observers) {
            observer.onSuscribeFailInvalid(this, user);
        }
    }

    public void suscribe(User user) {
        Objects.requireNonNull(user);
        if (validator.isValid(user)) {
            var v = suscribers.putIfAbsent(user.email(), user);
            if (v == null) {
                notifySuscribeFailAlreadyExists(user);
            } else {
                notifySuscribeSuccess(user);
            }
        } else {
            notifySuscribeFailInvalid(user);
        }
    }

    public void unsuscribe(User user) {
        Objects.requireNonNull(user);
        var oldUser = suscribers.remove(user.email());
        if (oldUser == null) {
            throw new IllegalStateException("No " + user.email() + " is registered");
        }
    }

    public void sendMessage(String title, String content) {
        var subject = "[" + name + "] " + title;
        for (var email : suscribers.keySet()) {
            mailer.send(email, subject, content);
        }
    }

    static void main() {

        var newsletterHarryPotter = new Newsletter("Harry 4ever", new EEMailerAdapter(), new UserValidatorBuilder().nationality(User.Nationality.BRITISH).ageMin(18).build());
        var newsletterJava = new Newsletter("Java 4ever", new GMailerAdapter(), new UserValidatorBuilder().nationality(User.Nationality.BRITISH, User.Nationality.FRENCH).ageMin(21).build());
        var newsletterSalieri = new Newsletter("Why me!", new EEMailerAdapter(), (user -> user.age() % 2 == 0 && user.email().endsWith("@univ-eiffel.fr")));

        var user1 = new User("Theo", "theo.constant@univ-eiffe.fr", 21, User.Nationality.BRITISH);

        var newsletterJavaWelcomeObserver = new WelcomeObserver("Welcome to Java", "Remi Forax is happy to welcoming you to Java !");
        var newsletterJavaBigMailingListObserver = new BigMailingListObserver(1);
        var newsletterJavaSuscribeFailObserver = new SuscribeFailObserver();

        newsletterJava.register(newsletterJavaWelcomeObserver);
        newsletterJava.register(newsletterJavaBigMailingListObserver);
        newsletterJava.register(newsletterJavaSuscribeFailObserver);

        newsletterJava.suscribe(user1);
        newsletterJava.suscribe(user1);
        newsletterJava.sendMessage("Remi Forax is the GOAT", "Everything is said in the title. I mean... isn't it obvious? :D");
    }
}
