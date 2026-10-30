package fr.uge.poo.newsletter.question1;

import com.evilcorp.eemailer.EEMailer;
import fr.uge.poo.newsletter.User;

import java.util.HashMap;
import java.util.Objects;

public class Newsletter {
    private final String name;
    private final HashMap<String, User> users = new HashMap<>();
    private final EEMailer mailer = new EEMailer();

    public Newsletter(String name) {
        Objects.requireNonNull(name);
        this.name = name;
    }

    public void suscribe(User user) {
        Objects.requireNonNull(user);
        users.putIfAbsent(user.email(), user);
    }

    public void unsuscribe(User user) {
        Objects.requireNonNull(user);
        users.remove(user.email());
    }

    public void sendMessage(String title, String content) {
        var subject = "[" + name + "] " + title;
        for (var email : users.keySet()) {
            mailer.send(new EEMailer.Mail(email, subject, content));
        }
    }
}
