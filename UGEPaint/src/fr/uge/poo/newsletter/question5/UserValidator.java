package fr.uge.poo.newsletter.question5;

import fr.uge.poo.newsletter.User;

@FunctionalInterface
    public interface UserValidator {
        boolean isValid(User user);
    }