package com.arturtarcisio.carros.service;

import com.arturtarcisio.carros.domain.Carro;
import com.arturtarcisio.carros.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepository repositorio;

    public List<Carro> getCarros(){
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

    public List<Carro> getCarroByTipo(String tipo){
        return repositorio.findCarroByTipo(tipo);
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
