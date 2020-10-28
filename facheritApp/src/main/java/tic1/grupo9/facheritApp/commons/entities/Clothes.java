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

    @Column
    @NonNull
    protected String color;

    @Column
    @NonNull
    protected String size;

    @Column
    @NonNull
    protected String gender;

    @Lob
    @Column
    private byte[] clothPicture;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    protected Brand brand;


    public Clothes(String name, String type, double price, String color, String size, String gender, byte[] picture, Brand brand){
        this.name = name;
        this.type = type;
        this.price = price;
        this.color = color;
        this.size = size;
        this.gender = gender;
        this.clothPicture = picture;
        this.brand = brand;
    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                '}';
    }

    public ImageView getPicture(){
        return new ImageView(new Image(new ByteArrayInputStream(clothPicture), 65, 60, true, true));
    }


}
