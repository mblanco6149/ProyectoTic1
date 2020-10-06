package tic1.grupo9.facheritApp.commons.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Clothes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    protected int id;

    @Column
    @NonNull
    protected String name;

    @Column
    @NonNull
    protected String type;

    @Column
    @NonNull
    protected double price;

    @Column
    @NonNull
    protected String color;

    @Column
    @NonNull
    protected String size;

}
