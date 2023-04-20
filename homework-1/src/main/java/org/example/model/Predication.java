package org.example.model;

public enum Predication {
    LUCK("У вас сегодня будет удача в делах!"),
    SELF_DEVELOPMENT("Сегодня хороший день для саморазвития!");

    public final String message;

    Predication(String message) {
        this.message = message;
    }
}
