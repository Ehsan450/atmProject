package com.example.demo.service;

public class AtmService {


    private static String generate(int length) {
        String generated = "";

        for (int i = 1; i <= length; i += 1) {
            int digit = (int) (Math.random() * 10);
            generated = generated.concat(String.valueOf(digit));
        }

        return generated;
    }
}
