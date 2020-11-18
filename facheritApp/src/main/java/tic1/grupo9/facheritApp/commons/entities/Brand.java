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

   /* @ManyToMany (mappedBy = "brands")
    private List<Local> locals;*/
    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable(
            name = "local_brand",
            joinColumns = {@JoinColumn(name = "brand_id")},
            inverseJoinColumns = {@JoinColumn(name = "local_id")}
    )
    List<Local> locales ;

    @OneToMany(mappedBy = "brand")
    private List<Clothes> clothes;

    public Brand(String name, String password){
        this.name=name;
        this.password = password;
    }

    public void setLocals(List<Local> locals) {
        this.locales = locals;
    }

    public String getName() {
        return name;
    }
}
