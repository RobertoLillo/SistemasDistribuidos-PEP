package Distribuidos.PEP1;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(UUID id, String mail, String name, String validFrom, String validUntil) {

        String idString = id.toString();
        String from = validFrom.substring(0, 16);
        String until = validUntil.substring(0, 16);

        String body = "Hola " + name + ".\n\n El identificador de tu certificado es: " + idString + ".\n\n Tu certificado es válido desde: " + from + ".\n\n Y es válido hasta: " + until + ".\n";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("noresponder@certificado.cl");
        simpleMailMessage.setTo(mail);
        simpleMailMessage.setSubject("Certificado temporal.");
        simpleMailMessage.setText(body);
        javaMailSender.send(simpleMailMessage);
    }
}
