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

    @Column
    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable(
            name = "Local_Brand",
            joinColumns = { @JoinColumn(name = "id_local") },
            inverseJoinColumns = { @JoinColumn(name = "brand_name") }
    )
    List<Brand> brands;


}
