package tic1.grupo9.facheritApp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tic1.grupo9.facheritApp.commons.entities.Brand;

@Repository
public interface BrandRepo extends JpaRepository<Brand, Integer> {
}