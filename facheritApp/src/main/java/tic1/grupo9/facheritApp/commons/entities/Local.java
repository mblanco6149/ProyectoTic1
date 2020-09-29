package tic1.grupo9.facheritApp.commons.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Local {

    @Id
    protected int id;

    @Column
    protected String name;

    @Column
    @ManyToMany (cascade = CascadeType.ALL)
    List<Brand> brands;


}
