package fr.uge.poo.newsletter.question5;

import fr.uge.poo.newsletter.User;

public class SuscribeFailObserver implements NewsletterObserver {

    private final Mailer mailer = new GMailerAdapter();

    @Override
    public void onSuscribeFail(Newsletter newsletter, User user) {
        mailer.send("support@goodcorp.com", "...", "Fail to suscribe " + user.email() + " in " + newsletter.name());
    }
}
