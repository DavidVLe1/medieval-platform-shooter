package learn.platformShooter.models;

import java.util.Objects;

public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String favoriteColor;
    private String gender;

    public User() {
    }

    public User( String firstName , String lastName , String username ,
                String email , String password , String favoriteColor , String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.favoriteColor = favoriteColor;
        this.gender = gender;
    }

    public User(int userId , String firstName , String lastName , String username , String email , String password , String favoriteColor , String gender) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.favoriteColor = favoriteColor;
        this.gender = gender;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFavoriteColor() {
        return favoriteColor;
    }

    public void setFavoriteColor(String favoriteColor) {
        this.favoriteColor = favoriteColor;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals (firstName , user.firstName) && Objects.equals (lastName , user.lastName) && Objects.equals (username , user.username) && Objects.equals (email , user.email) && Objects.equals (password , user.password) && Objects.equals (favoriteColor , user.favoriteColor) && Objects.equals (gender , user.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash (userId , firstName , lastName , username , email , password , favoriteColor , gender);
    }
}
