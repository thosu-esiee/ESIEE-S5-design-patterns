package fr.uge.poo.newsletter.question5;

import java.util.List;

public interface Mailer {
    void send(String receiver, String subject, String content);
    void sendBulk(List<String> receivers, String subject, String content);
}
