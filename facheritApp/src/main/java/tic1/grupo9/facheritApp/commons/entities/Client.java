package tic1.grupo9.facheritApp.commons.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Client extends User {

    @Column
    protected String name;

    @Column
    protected String surname;

    @Column
    protected long phone;

    @Column
    protected String direccion;

    public Client(String email, String password, String name, String surname, long phone, String direccion)
    {
        super(email, password);
        this.name=name;
        this.surname=surname;
        this.phone=phone;
        this.direccion=direccion;
    }

}
