package com.example.spring.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
@Profile("init")
public class ConsoleServiceInitImpl extends AbstractConsoleService  {

    public ConsoleServiceInitImpl(ContactServiceImpl contactServiceImpl) {
        super(contactServiceImpl);
    }

    @PostConstruct
    public void init() throws IOException {
        contactServiceImpl.parsingFile();
        consoleCommand();
    }
}
