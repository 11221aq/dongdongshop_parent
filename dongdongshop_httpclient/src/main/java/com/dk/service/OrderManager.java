package com.dk.service;

public interface OrderManager {
    void sendMail(String userEmail,String password);

    void holdEmail(String userEmail);
}