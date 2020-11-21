package tic1.grupo9.facheritApp.commons.entities;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Proxy(lazy = false)
public class Brand  {

    @Id
    @Column
    protected String name;

    @Column
    protected String password;

   /* @ManyToMany (mappedBy = "brands")
    private List<Local> locals;*/
    @ManyToMany (cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinTable(
            name = "local_brand",
            joinColumns = {@JoinColumn(name = "brand_id")},
            inverseJoinColumns = {@JoinColumn(name = "local_id")}
    )
   Set<Local> locales ;

    @OneToMany(mappedBy = "brand",cascade = CascadeType.ALL)
    private Set<Clothes> clothes;

    public Brand(String name, String password){
        this.name=name;
        this.password = password;
    }

    public void setLocals(Set<Local> locals) {
        this.locales = locals;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if(o==this){
            return true;
        }
        if(!(o instanceof Brand)){
            return false;
        }
        Brand b = (Brand) o;
        return this.name.equals(b.getName());
    }

    public Set<Local> getLocales() {
        return locales;
    }

    public  void addClothes(Clothes c){
        this.clothes.add(c);
    }

    public  void addLocal(Local l){
        this.locales.add(l);
    }
}
