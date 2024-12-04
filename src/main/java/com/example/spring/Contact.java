package com.example.spring;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;

@Setter
@Getter
@AllArgsConstructor
public class Contact {
    private String fullName;
    private String phoneNumber;
    private String email;

    @Override
    public String toString() {
        return MessageFormat.format("{0}|{1}|{2}",fullName,phoneNumber,email);
    }
}
