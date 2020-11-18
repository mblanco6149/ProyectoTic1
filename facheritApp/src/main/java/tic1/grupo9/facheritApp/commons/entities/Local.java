package tic1.grupo9.facheritApp.commons.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column
    protected String name;

    @Column
    protected String password;


   /* @ManyToMany (cascade = CascadeType.ALL)
            @JoinTable(
                    name = "local_brand",
                    joinColumns = {@JoinColumn(name = "local_id")},
                    inverseJoinColumns = {@JoinColumn(name = "brand_id")}
    )
    List<Brand> brands ;*/
   @ManyToMany (mappedBy = "locales")
   private List<Brand> brands;

    @Column
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "locals")
    List<Stock> stock;


    public Local (String name, String password, List<Brand> brandList){
        this.name = name;this.password = password;this.brands = brandList;
    }

    public void addBrand (Brand brand){
        brands.add(brand);
    }

    public void setId(int id) {
        this.id = id;
    }
}
