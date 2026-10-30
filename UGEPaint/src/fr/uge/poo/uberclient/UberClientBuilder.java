package fr.uge.poo.uberclient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class UberClientBuilder {
    private Long uid;
    private String firstname;
    private String lastname;
    private ArrayList<Integer> grades = new ArrayList<>();
    private ArrayList<String> emails = new ArrayList<>();
    private ArrayList<String> phoneNumbers = new ArrayList<>();

    public UberClient build() {
        if (uid == null) {
            throw new IllegalStateException("An UberClient needs an uid");
        }
        if (firstname == null) {
            throw new IllegalStateException("An UberClient needs a firstname");
        }
        if (lastname == null) {
            throw new IllegalStateException("An UberClient needs a lastname");
        }
        if (grades.size() == 0) {
            throw new IllegalStateException("An UberClient needs at list one grade");
        }
        for(var grade : grades){
            if (grade < 1 || grade > 5) {
                throw new IllegalStateException("All grades must be between 1 and 5");
            }
        }
        if (emails.size() == 0 && phoneNumbers.size() == 0) {
            throw new IllegalStateException("An UberClient needs at list an email or a phone number");
        }

        return new UberClient(firstname, lastname, uid, grades, emails, phoneNumbers);
    }

    public UberClientBuilder uid(long uid) {
        this.uid = uid;
        return this;
    }

    public UberClientBuilder randomUid() {
        this.uid = ThreadLocalRandom.current().nextLong(0,Long.MAX_VALUE);
        return this;
    }

    public UberClientBuilder randomUid(ThreadLocalRandom threadLocalRandom) {
        this.uid = threadLocalRandom.nextLong(0,Long.MAX_VALUE);
        return this;
    }

    public UberClientBuilder firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public UberClientBuilder lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public UberClientBuilder grade(int grade) {
        this.grades.add(grade);
        return this;
    }

    public UberClientBuilder grades(List<Integer> grades) {
        this.grades.addAll(grades);
        return this;
    }

    public UberClientBuilder email(String email) {
        this.emails.add(email);
        return this;
    }

    public UberClientBuilder phoneNumber(String phoneNumber) {
        this.phoneNumbers.add(phoneNumber);
        return this;
    }
}
