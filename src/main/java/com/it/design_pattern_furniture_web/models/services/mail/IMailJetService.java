package com.it.design_pattern_furniture_web.models.services.mail;

public interface IMailJetService {
    boolean sendMail(String name, String email, String content, String title);
}
