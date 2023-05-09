package ba.unsa.etf.ppis.service;

import ba.unsa.etf.ppis.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    protected JavaMailSender javaMailSender;

    @Override
    public void sendTestEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nowhiringdev@gmail.com");
        message.setTo("begovicami5@gmail.com");
        message.setSubject("Test mail");
        message.setText("Test text");
        javaMailSender.send(message);
    }

    @Override
    public void sendEmail(String subject, String text, UserEntity userFor) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nowhiringdev@gmail.com");
        message.setTo(userFor.getEmail());
        message.setSubject("New notification");
        message.setText(addFooter(text, userFor));
        javaMailSender.send(message);
    }

    private String addFooter(String text, UserEntity userFor) {
        return "Dear " + userFor.getName() + ", \n\n" + text + "\n\nSincerely,\nThe team of Skenderija EVENTS";
    }
}
