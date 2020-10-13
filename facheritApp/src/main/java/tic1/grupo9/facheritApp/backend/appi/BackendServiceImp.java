package tic1.grupo9.facheritApp.backend.appi;

import tic1.grupo9.facheritApp.commons.appi.BackendService;
import tic1.grupo9.facheritApp.commons.entities.Clothes;

import java.util.ArrayList;
import java.util.List;

public class BackendServiceImp implements BackendService {

    List<Clothes> carrito = new ArrayList<>(10);

    public List<Clothes> getCarrito() {
        return carrito;
    }

    public boolean carritoIsEmpty(){
        return carrito.isEmpty();
    }

    @Override
    public void saveNewBrand() {

    }

    @Override
    public void saveAdmin() {

    }

    @Override
    public void saveNewLocal() {

    }

    @Override
    public void saveStock() {

    }
}
