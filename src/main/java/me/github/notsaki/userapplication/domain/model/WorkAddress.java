package me.github.notsaki.userapplication.domain.model;

public interface WorkAddress {
    int getId();

    void setId(int id);

    String getAddress();

    void setAddress(String address);

    boolean equals(Object o);

    int hashCode();
}
