package tic1.grupo9.facheritApp.commons.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    @Id
    @Column(name = "email", unique = true, nullable = false)
    protected String email;

    @NonNull
    protected String password;

    public User(String email, @NonNull String password) {
        this.email = email;
        this.password = password;
    }
}
