package ba.unsa.etf.ppis.service;

import ba.unsa.etf.ppis.entity.UserEntity;

public interface EmailService {

    void sendTestEmail();

    void sendEmail(String subject, String text, UserEntity userFor);
}
