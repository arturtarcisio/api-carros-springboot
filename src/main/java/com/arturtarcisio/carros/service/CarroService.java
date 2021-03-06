package com.arturtarcisio.carros.service;

import com.arturtarcisio.carros.domain.Carro;
import com.arturtarcisio.carros.domain.dto.CarroDTO;
import com.arturtarcisio.carros.exception.ObjectNotFoundException;
import com.arturtarcisio.carros.repository.CarroRepository;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepository repositorio;

    public List<CarroDTO> getCarros(){
        return repositorio.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public CarroDTO getCarroById(Long id){
        return repositorio.findById(id).map(CarroDTO::create).orElseThrow(
                () -> new ObjectNotFoundException("Carro não encontrado!"));
    }

    public List<CarroDTO> getCarroByTipo(String tipo) {
        return repositorio.findCarroByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public CarroDTO insert(Carro carro){
        Assert.isNull(carro.getId(), "Não foi possível inserir o registro");
        return CarroDTO.create(repositorio.save(carro));
    }

    public CarroDTO update(Carro carro, Long id) {
        Assert.notNull(id, "Não foi possível atualizar o registro");

        Optional<Carro> optional = repositorio.findById(id);
        if(optional.isPresent()){
            Carro db = optional.get();
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            System.out.println("Carro id " + db.getId());

            repositorio.save(db);

            return CarroDTO.create(db);
        } else {
            return null;
        }

    }

    public void delete(Long id){
        repositorio.deleteById(id);
    }

}
