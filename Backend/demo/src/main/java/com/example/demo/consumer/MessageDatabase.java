package com.example.demo.consumer;
import com.example.demo.Repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.Models.User;

@Component
public class MessageDatabase {
    @Autowired
    private UserRepo userRepo;

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageDatabase.class);



    @JmsListener(destination = "${activemq.queue.name}")
    @Transactional
    public void messageListener(User user) {
        userRepo.save(user);
        LOGGER.info("Message received! {}", user.toString());

    }
}

