package tic1.grupo9.facheritApp.frontend.controllers;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tic1.grupo9.facheritApp.backend.services.AdminService;
import tic1.grupo9.facheritApp.backend.services.ClientService;


@Controller
public class StartAppiController {


    @Autowired
    AdminService as;

    @Autowired
    ClientService cs;


}
