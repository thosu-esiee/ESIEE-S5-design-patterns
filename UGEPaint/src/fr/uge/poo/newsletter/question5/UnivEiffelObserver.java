package fr.uge.poo.newsletter.question5;

import fr.uge.poo.newsletter.User;

public class UnivEiffelObserver implements NewsletterObserver {

    private final Mailer mailer = new GMailerAdapter();

    @Override
    public void onSuscribeSuccess(Newsletter newsletter, User user) {
        if (user.email().endsWith("etud.univ-eiffel.fr")) {
            mailer.send("spy@nsa.org", "Univ eiffel student", "New univ eiffel student in newsletter: " + newsletter.name());

        }
    }
}
