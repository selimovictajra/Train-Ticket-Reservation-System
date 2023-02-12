package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Class that contains information about users made for train tickets.
 * @author Tajra Selimovic
 */
public class User implements Idable {
    private int id;
    private String name;
    private boolean role;
    private String username;
    private String password;

    /**
     * Default constructor
     */
    public User() {}

    /**
     * Getter for id
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for id
     * @param user_id int
     */
    public void setId(int user_id) {
        this.id = user_id;
    }

    /**
     * Getter for name
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for role
     * @return boolean
     */
    public boolean isRole() {
        return role;
    }

    /**
     * Setter for role
     * @param role boolean
     */
    public void setRole(boolean role) {
        this.role = role;
    }

    /**
     * Getter for username
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for username
     * @param username boolean
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for password
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password
     * @param password String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Users{" +
                "user_id=" + id +
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
        User u = (User) o;
        return id == u.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, role, username, password);
    }
}
