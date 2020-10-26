package tic1.grupo9.facheritApp.commons.entities;

import lombok.NoArgsConstructor;

import javax.persistence.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
@NoArgsConstructor
public class Brand  {

    @Id
    @Column
    protected String name;

    @Column
    protected String password;

    @ManyToMany (mappedBy = "brands")
    List<Local> locals;

    public Brand(String name, String password){
        this.name=name;
        this.password = password;
    }

    public void setLocals(List<Local> locals) {
        this.locals = locals;
    }


}
