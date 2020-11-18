package tic1.grupo9.facheritApp.commons.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    protected Integer id;

    @ManyToOne
    @JoinColumn(name = "clothes_id")
    protected Clothes clothes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "locals_id")
    protected Local locals;
    @Column
    protected Integer quantity;
    @Column
    protected String size;
    @Column
    protected String color;

    public Stock(Clothes clothes, Local local, Integer quantity , String size, String color){
        this.color=color;this.clothes=clothes;this.locals=local;this.quantity=quantity;this.size=size;
    }

    public Clothes getClothes() {
        return clothes;
    }

    public void setClothes(Clothes clothes) {
        this.clothes = clothes;
    }

    public Local getLocals() {
        return locals;
    }

    public void setLocals(Local locals) {
        this.locals = locals;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
