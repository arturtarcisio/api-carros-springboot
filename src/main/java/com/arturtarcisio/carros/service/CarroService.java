package com.arturtarcisio.carros.service;

import com.arturtarcisio.carros.domain.Carro;
import com.arturtarcisio.carros.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepository repositorio;

    public Iterable getCarros(){
        return repositorio.findAll();
    }

//    public List<Carro> getCarrosFake(){
//        List<Carro> carros = new ArrayList<>();
//        carros.add(new Carro(1L, "Fusca"));
//        carros.add(new Carro(2L, "Brasilia"));
//        carros.add(new Carro(3L, "Chevette"));
//        return carros;
//    }

    public Optional<Carro> getCarroById(Long id){
        return repositorio.findById(id);
    }

    public Iterable<Carro> getCarroByTipo(String tipo){
        return repositorio.findCarroByTipo(tipo);
    }

    public Carro save(Carro carro){
        return repositorio.save(carro);
    }
}
