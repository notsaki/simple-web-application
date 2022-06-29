package me.github.notsaki.userapplication.domain.model;

public interface Admin {
    int getId();

    void setId(int id);

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    boolean equals(Object o);

    int hashCode();
}