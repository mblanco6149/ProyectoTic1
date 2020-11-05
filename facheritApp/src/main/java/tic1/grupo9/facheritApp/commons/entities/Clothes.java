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


    @NonNull
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "clothes")
    protected List<Colour> color;


    @NonNull
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "clothes_size")
    protected List<Size> size;

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

    public Clothes(String name, String type, double price, List<Colour> color, List<Size> size, String gender, byte[] picture, Brand brand){
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

    public  String toStringGetSizes(){
        String sizes = null;
        for(int i = 0; i< size.size(); i++){
            sizes += size.get(i).toString() + ", ";
        }
        return sizes;
    }

    @Override
    public String toString() {
        return
                name + '\n' +
                "price= $" + price +
                "   colours= " + toStringGetColor() +
                "   size= " + toStringGetSizes()  ;
    }

    public ImageView getPicture(){
        return new ImageView(new Image(new ByteArrayInputStream(clothPicture), 140, 140, true, true));
    }


}
