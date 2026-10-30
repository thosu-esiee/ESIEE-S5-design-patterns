package fr.uge.poo.newsletter.question4;

import fr.uge.poo.newsletter.User;

@FunctionalInterface
    public interface UserValidator {
        boolean isValid(User user);
    }