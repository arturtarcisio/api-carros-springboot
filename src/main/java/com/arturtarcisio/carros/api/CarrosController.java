package com.arturtarcisio.carros.api;

import com.arturtarcisio.carros.domain.Carro;
import com.arturtarcisio.carros.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {

    @Autowired
    private CarroService service;

    @GetMapping()
    public Iterable<Carro> get(){
        return service.getCarros();
    }

    @GetMapping("/{id}")
    public Optional<Carro> getById(@PathVariable("id") Long id){
        return service.getCarroById(id);
    }

    @GetMapping("/tipo/{tipo}")
    public Iterable<Carro> getByTipo(@PathVariable("tipo") String tipo){
        return service.getCarroByTipo(tipo);
    }

    @PostMapping
    public String salvarCarro(@RequestBody Carro carro){
        service.save(carro);

        return "Carro salvo com sucesso: " + carro.getId();
    }
}
