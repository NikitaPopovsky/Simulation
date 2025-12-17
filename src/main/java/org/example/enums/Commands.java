package org.example.enums;

public enum Commands {
    START("s"),
    PAUSE("p"),
    EXIT("e"),
    EMPTY("");

    private final String value;

    Commands(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Commands getByValue (String value) {
        for (Commands com: Commands.values()){
            if (com.getValue().equals(value)) {
                return com;
            }
        }
        return Commands.EMPTY;
    }



}
