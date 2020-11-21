package tic1.grupo9.facheritApp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tic1.grupo9.facheritApp.commons.entities.Brand;

import java.util.List;

@Repository
public interface BrandRepo extends JpaRepository<Brand, Integer> {

    public List<Brand> findByName(String name);

   /* @Transactional
    @Modifying
    @Query("insert into )
    void updateQuantity(@Param(value = "id") Integer id, @Param(value = "quantity") Integer quantity);*/
}
