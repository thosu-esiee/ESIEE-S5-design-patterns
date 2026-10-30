package fr.uge.poo.newsletter.question4;

import com.evilcorp.eemailer.EEMailer;

import java.util.List;

public class EEMailerAdapter implements Mailer {
    private final EEMailer mailer = new EEMailer();

    @Override
    public void send(String receiver, String subject, String content) {
        mailer.send(new EEMailer.Mail(receiver, subject, content));
    }

    @Override
    public void sendBulk(List<String> receivers, String subject, String content) {
        for (var receiver : receivers) {
            mailer.send(new EEMailer.Mail(receiver, subject, content));
        }
    }
}
