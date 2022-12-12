package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 *
 * @author Tajra Selimovic
 */
public class Users {
    private int user_id;
    private String name;
    private boolean role;
    private String username;
    private String password;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Users{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users u = (Users) o;
        return user_id == u.user_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, name, role, username, password);
    }
}
