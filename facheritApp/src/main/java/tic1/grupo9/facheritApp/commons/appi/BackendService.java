package tic1.grupo9.facheritApp.commons.appi;

import java.rmi.Remote;

public interface BackendService extends Remote {

    void saveNewBrand();
    void saveAdmin();
    void saveNewLocal(); //se manda un array de marcas.
    void saveStock();







}
