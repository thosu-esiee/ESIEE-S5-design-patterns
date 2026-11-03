package fr.uge.poo.newsletter.question5;

import com.goodcorp.gmailer.GMailer;

import java.util.List;

public class GMailerAdapter implements Mailer {
    private final GMailer mailer = new GMailer();

    @Override
    public void send(String receiver, String subject, String content) {
        mailer.send(receiver, new GMailer.Mail(subject, content));
    }

    @Override
    public void sendBulk(List<String> receivers, String subject, String content) {
        mailer.sendBulk(receivers, new GMailer.Mail(subject, content));
    }
}
