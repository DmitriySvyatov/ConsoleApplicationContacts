package com.example.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
@Profile("default")
public class ConsoleServiceDefaultImpl extends AbstractConsoleService {

    public ConsoleServiceDefaultImpl(ContactServiceImpl contactServiceImpl) {
        super(contactServiceImpl);
    }

    @PostConstruct
    public void init() throws IOException {
        consoleCommand();
    }

}
