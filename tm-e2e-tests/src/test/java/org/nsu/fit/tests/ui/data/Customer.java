package org.nsu.fit.tests.ui.data;

public class Customer {
    public String login;
    public String firstName;
    public String lastName;

    public Customer() {
    }

    public Customer(String login, String firstName, String lastName) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
