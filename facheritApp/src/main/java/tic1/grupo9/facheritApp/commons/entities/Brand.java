package tic1.grupo9.facheritApp.commons.entities;

import javax.persistence.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Brand  {

    @Id
    protected int id;

    @Column
    protected String name;

    @ManyToMany (mappedBy = "brands")
    List<Local> locals;
}
