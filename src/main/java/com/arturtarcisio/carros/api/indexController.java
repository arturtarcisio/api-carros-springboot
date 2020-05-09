package com.arturtarcisio.carros.api;

import com.arturtarcisio.carros.domain.Carro;
import com.arturtarcisio.carros.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class indexController {

    @GetMapping()
    public String get(){
        return "API dos carros";
    }
}
