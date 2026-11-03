package fr.uge.poo.newsletter.question5;

import fr.uge.poo.newsletter.User;

public interface NewsletterObserver {
    default void onSuscribeSuccess(Newsletter newsletter, User user) {}
    default void onSuscribeFailAlreadyExists(Newsletter newsletter, User user) {}
    default void onSuscribeFailInvalid(Newsletter newsletter, User user) {}
    default void onUnsuscribe(Newsletter newsletter, User user) {}
    default void onSend(Newsletter newsletter) {}
}
