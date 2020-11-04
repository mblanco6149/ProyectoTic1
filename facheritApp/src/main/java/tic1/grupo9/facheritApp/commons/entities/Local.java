package tic1.grupo9.facheritApp.commons.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column
    protected String name;

    @Column
    protected String password;


    @ManyToMany (cascade = CascadeType.ALL)
            @JoinTable(
                    name = "local_brand",
                    joinColumns = @JoinColumn(name = "local_id"),
                    inverseJoinColumns = @JoinColumn(name = "brand_id")
    )
    List<Brand> brands;

    @Column
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "locals")
    List<Stock> stock;




}
