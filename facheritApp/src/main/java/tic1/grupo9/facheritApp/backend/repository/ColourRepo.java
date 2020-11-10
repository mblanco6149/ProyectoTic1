package tic1.grupo9.facheritApp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tic1.grupo9.facheritApp.commons.entities.Colour;

import java.util.List;

@Repository
public interface ColourRepo extends JpaRepository<Colour,Integer> {
    public List<Colour> findByColours(String colours);
}
