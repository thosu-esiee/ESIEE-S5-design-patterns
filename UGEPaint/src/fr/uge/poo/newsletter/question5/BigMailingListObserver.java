package fr.uge.poo.newsletter.question5;

import fr.uge.poo.newsletter.User;

import java.util.ArrayList;

public class BigMailingListObserver implements NewsletterObserver {

    private final int max;
    private final ArrayList<User> users = new ArrayList<>();
    private boolean hasExceeded;

    private final Mailer mailer = new GMailerAdapter();

    public BigMailingListObserver(int max) {
        this.max = max;
    }

    @Override
    public void onSuscribeSuccess(Newsletter newsletter, User user) {
        users.add(user);
        if (!hasExceeded && users.size() == 1) {
            hasExceeded = true;
            var usersString = users.stream().map(u -> u.email() + ", ").reduce(String::concat).orElseThrow(); // TODO: a better string with stream
            mailer.send(
                    "sales@goodcorp.com",
                    "Mailing list is becoming big",
                    "The mailing list " + newsletter.name() + "is becoming big : " + usersString);
        }
    }
}
