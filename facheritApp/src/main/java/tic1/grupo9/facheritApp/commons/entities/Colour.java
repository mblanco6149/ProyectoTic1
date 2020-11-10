package tic1.grupo9.facheritApp.commons.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Colour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    protected Integer id;
    @Column
    private String colours;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clothes_id")
    protected Clothes clothes;


    public Colour(String color){
        colours = color;
    }

    @Override
    public String toString() {
        return colours ;
    }

    public void setClothes(Clothes clothes) {
        this.clothes = clothes;
    }

    public String getColours() {
        return colours;
    }

    public void setColours(String colours) {
        this.colours = colours;
    }
}
