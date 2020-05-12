package com.arturtarcisio.carros.service;

import com.arturtarcisio.carros.domain.Carro;
import com.arturtarcisio.carros.domain.dto.CarroDTO;
import com.arturtarcisio.carros.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepository repositorio;

    public List<CarroDTO> getCarros(){
        //Percorre minha lista de Carro e inserindo no CarroDTO
        return repositorio.findAll().stream().map(CarroDTO::new).collect(Collectors.toList());

//        List<Carro> carro = repositorio.findAll();
//        List<CarroDTO> list = new ArrayList<>();
//
//        for (Carro c : carro){
//            list.add(new CarroDTO(c));
//        }
//        return list;

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

    public List<CarroDTO> getCarroByTipo(String tipo) {
        return repositorio.findCarroByTipo(tipo).stream().map(CarroDTO::new).collect(Collectors.toList());
    }

    public Carro save(Carro carro){
        return repositorio.save(carro);
    }

    public void update(Carro carro, Long id) {
        getCarroById(id).map(carroDb -> {
            carroDb.setNome(carro.getNome());
            carroDb.setTipo(carro.getTipo());
            System.out.println("Carro id: " + carroDb.getId());
            repositorio.save(carroDb);
            return carroDb;
        }).orElseThrow(() -> new RuntimeException("Não foi possível atualizar o registro"));
    }

    public void delete(Long id){
        Optional<Carro> carro = getCarroById(id);
        if(carro.isPresent()){
            repositorio.deleteById(id);
        }
    }

}
