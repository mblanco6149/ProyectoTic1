package tic1.grupo9.facheritApp.commons.entities;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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


   @ManyToMany (mappedBy = "locales")
   private Set<Brand> brands;

    @Column
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "locals")
    List<Stock> stock;


    public Local (String name, String password, Set<Brand> brandList){
        this.name = name;this.password = password;this.brands = brandList;
    }

    public void addBrand (Brand brand){
        brands.add(brand);
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Brand> getBrands() {
        return brands;
    }

    public String getName() {
        return name;
    }
}
