package fr.uge.poo.newsletter.question5;

import fr.uge.poo.newsletter.User;

public class WelcomeObserver implements NewsletterObserver {

    private final String title;
    private final String content;
    private final Mailer mailer = new GMailerAdapter();

    public WelcomeObserver(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public void onSuscribeSuccess(Newsletter newsletter, User user) {
        mailer.send(user.email(), title, content);
    }
}
