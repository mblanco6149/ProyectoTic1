package tic1.grupo9.facheritApp.commons.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Proxy(lazy = false)
public class Client extends User {

    @Column(name = "name")
    protected String firstName;

    @Column(name = "surname")
    protected String lastName;

    @Column(name = "phone")
    protected long phone;

    @Column(name = "address")
    protected String address;

    public Client(String email, String password, String firstName, String lastName, long phone, String address)
    {
        super(email, password);

        this.firstName=firstName;
        this.lastName=lastName;
        this.phone=phone;
        this.address=address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}
