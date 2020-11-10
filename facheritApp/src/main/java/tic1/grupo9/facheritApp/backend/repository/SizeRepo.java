package tic1.grupo9.facheritApp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tic1.grupo9.facheritApp.commons.entities.Size;

import java.util.List;

@Repository
public interface SizeRepo extends JpaRepository<Size,Integer> {
    public List<Size> findBySizes(String sizes);
}
