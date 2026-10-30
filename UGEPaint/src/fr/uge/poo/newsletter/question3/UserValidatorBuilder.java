package fr.uge.poo.newsletter.question3;

import fr.uge.poo.newsletter.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

public class UserValidatorBuilder {

    private final ArrayList<UserValidator> validators = new ArrayList<>();

    public UserValidatorBuilder validator(UserValidator validator) {
        Objects.requireNonNull(validator);
        validators.add(validator);
        return this;
    }

    public UserValidatorBuilder ageMin(int age) {
        validators.add(user -> user.age() >= age);
        return this;
    }

    public UserValidatorBuilder ageMax(int age) {
        validators.add(user -> user.age() <= age);
        return this;
    }

    public UserValidatorBuilder nationality(User.Nationality... nationalities) {
        validators.add(user -> Arrays.stream(nationalities).anyMatch(nationality -> nationality == user.nationality()));
        return this;
    }

    public UserValidator build() {
        return user -> {
            for (var validator : validators) {
                if (!validator.isValid(user)) {
                    return false;
                }
            }
            return true;
        };
    }
}
