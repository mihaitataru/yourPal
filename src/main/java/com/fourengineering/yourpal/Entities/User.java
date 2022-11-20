package com.fourengineering.yourpal.Entities;

import com.fourengineering.yourpal.Misc.BadHabits;
import com.fourengineering.yourpal.Misc.Hobbies;
import com.fourengineering.yourpal.Repositories.UserRepository;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Component
public class User {

    @Id
    @GeneratedValue
    private long id;
    @Column(name = "Username")
    private String username;

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "surname")
    private String surname;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name = "Password")
    private byte[] password;

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    @Column( unique = true, name = "Email")
    private String email;


    @Column(name = "Bio")
    private String Bio;

    @Column(name = "Mentor")
    private boolean mentor;

    @Column(name = "Hobbies")
    private Hobbies hobbies;

    @Column(name = "BadHabits")
    private BadHabits badHabits;

    @Column(name = "Rating")
   private double rating;

    @Column(name = "Connections")
    private String connections;

    @Column(nullable = true, length = 64)
    private String photos;

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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


    public String getBio() {
        return Bio;
    }

    public void setBio(String Bio) {
        this.Bio = Bio;
    }

    public boolean isMentor() {
        return mentor;
    }

    public void setMentor(boolean mentor) {
        this.mentor = mentor;
    }

    public Hobbies getHobbies() {
        return hobbies;
    }

    public void setHobbies(Hobbies hobbies) {
        this.hobbies = hobbies;
    }

    public BadHabits getBadHabits() {
        return badHabits;
    }

    public void setBadHabits(BadHabits badHabits) {
        this.badHabits = badHabits;
    }

    public String getConnections() {
        return connections;
    }

    public void setConnections(String connections) {
        this.connections = connections;
    }
        public double getRating() {
      return rating;
    }

   public void setRating(double rating) {
       this.rating = rating;
   }

    public List<User> getPossibleConnections(UserRepository repo){
        List<User> users1 = new ArrayList<User>();
        List<User> users2 = new ArrayList<User>();
        users1 = repo.findByHobbies(this.hobbies);
        users2 = repo.findByBadHabits(this.badHabits);
        users1.addAll(users2);
        Set<User> set = new HashSet<>(users1);
        users1.clear();
        users1.addAll(set);
        for(int i=0; i< users1.size(); i++){
            if(users1.get(i).getUsername().equals(this.username))
                users1.remove(i);
        }
        return users1;
    }
    public static boolean validateRating(double rating){
        if(rating>10 || rating<0)
            return false;
        return true;
    }
}
