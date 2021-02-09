package com.destrostudios.authtoken;

public class JwtAuthenticationUser {
    public long id;
    public String login;

    @Override
    public int hashCode() {
        return (int) id;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof JwtAuthenticationUser)) {
            return false;
        }
        JwtAuthenticationUser other = (JwtAuthenticationUser) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return "JwtAuthenticationUser{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }
}
