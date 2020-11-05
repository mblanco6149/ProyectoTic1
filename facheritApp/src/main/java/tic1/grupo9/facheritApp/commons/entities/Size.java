package tic1.grupo9.facheritApp.commons.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    protected Integer id;
    @Column
    private String sizes;

    @ManyToOne
    @JoinColumn(name = "clothes_id")
    protected Clothes clothes_size;

    public Size(String sizes){
        this.sizes = sizes;
    }

    @Override
    public String toString() {
        return  sizes;
    }

    public void setClothes_size(Clothes clothes_size) {
        this.clothes_size = clothes_size;
    }
}
