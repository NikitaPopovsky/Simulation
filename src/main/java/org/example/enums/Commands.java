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

    public static Commands getByValue (String commandString) {
        for (Commands com: Commands.values()){
            if (com.getValue().equals(commandString)) {
                return com;
            }
        }
        return Commands.EMPTY;
    }



}
