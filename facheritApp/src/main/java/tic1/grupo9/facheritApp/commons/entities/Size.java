package tic1.grupo9.facheritApp.commons.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    protected Integer id;
    @Column
    private String sizes;

    @ManyToOne
    @JoinColumn(name = "clothes_id")
    protected Clothes clothes_size;
}
