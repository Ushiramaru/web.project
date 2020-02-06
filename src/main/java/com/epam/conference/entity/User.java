package com.epam.conference.entity;

import com.epam.conference.entity.enums.UserRole;

public class User implements Identifiable {

    public static final String TABLE = "user";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String ROLE = "role";
    public static final String IS_ACTIVE = "is_active";

    private final Long id;
    private final String name;
    private final UserRole role;
    private final boolean isActive;

    public User(Long id, String name, UserRole role, boolean isActive) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.isActive = isActive;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UserRole getRole() {
        return role;
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", isActive=" + isActive +
                '}';
    }

}