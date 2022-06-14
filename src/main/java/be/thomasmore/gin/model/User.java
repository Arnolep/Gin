package be.thomasmore.gin.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
    public class User {
        @Id
        @SequenceGenerator(name = "user_seq", sequenceName = "USER_SEQ", initialValue = 0, allocationSize = 1)
        @GeneratedValue(generator = "user_seq")
        private Integer id;
        private String username;
        private String email;
        private String password;
        private String role;

        @ManyToMany
        Collection<Recept> favorites;


        public User() {
        }

    public User(Integer id, String username, String email, String password, String role, Collection<Recept> favorites) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.favorites = favorites;
    }

    public Collection<Recept> getFavorites() {
        return favorites;
    }

    public void setFavorites(Collection<Recept> favorites) {
        this.favorites = favorites;
    }

    public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
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

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

