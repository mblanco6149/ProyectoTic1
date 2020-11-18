package tic1.grupo9.facheritApp.commons.entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "clothes")
public class Clothes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    protected Integer id;

    @Column
    @NonNull
    protected String name;

    @Column
    @NonNull
    protected String type;

    @Column
    @NonNull
    protected double price;


    @NonNull
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "clothes",fetch = FetchType.EAGER)
    protected List<Colour> color;


    @NonNull
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "clothes_size",fetch = FetchType.EAGER)
    protected Set<Size> size;

    @Column
    @NonNull
    protected String gender;

    @Lob
    @Column
    private byte[] clothPicture;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    protected Brand brand;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "clothes")
    protected List<Stock> stocks;

    public Clothes(String name, String type, double price, List<Colour> color, Set<Size> size, String gender, byte[] picture, Brand brand){
        this.name = name;
        this.type = type;
        this.price = price;
        this.color = color;
        this.size = size;
        this.gender = gender;
        this.clothPicture = picture;
        this.brand = brand;
    }

    public String toStringGetColor(){
        String colours = null;
        System.out.println(color);
        for(int i = 0; i< color.size(); i++){
            colours += color.get(i).toString() + ", ";
        }
        return colours;
    }



    @Override
    public String toString() {
        return
                name + ", price= $" + price  ;
                //"price= $" + price ;
               // "   colours= " + toStringGetColor() +
                //"   size= " + toStringGetSizes()  ;
    }

    public ImageView getPicture(){
        return new ImageView(new Image(new ByteArrayInputStream(clothPicture), 140, 140, true, true));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Colour> getColor() {
        return color;
    }

    public void setColor(List<Colour> color) {
        this.color = color;
    }

    public Set<Size> getSize() {
        return size;
    }

    public void setSize(Set<Size> size) {
        this.size = size;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public byte[] getClothPicture() {
        return clothPicture;
    }

    public void setClothPicture(byte[] clothPicture) {
        this.clothPicture = clothPicture;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }
}
