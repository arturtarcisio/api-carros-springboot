package com.arturtarcisio.carros.api;

import com.arturtarcisio.carros.domain.Carro;
import com.arturtarcisio.carros.domain.dto.CarroDTO;
import com.arturtarcisio.carros.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {

    @Autowired
    private CarroService service;

    @GetMapping()
    public ResponseEntity<List<CarroDTO>> get(){
        return ResponseEntity.ok(service.getCarros());
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id){
        Optional<CarroDTO> carro = service.getCarroById(id);
        return carro.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

//        return carro.isPresent() ?
//                ResponseEntity.ok(carro.get()) :
//                ResponseEntity.notFound().build();
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity getByTipo(@PathVariable("tipo") String tipo){
        List<CarroDTO> carro = service.getCarroByTipo(tipo);

        return carro.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carro);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody Carro carro){
        try{
            CarroDTO carroDTO = service.insert(carro);
            URI location = getUri(carroDTO.getId());
            return ResponseEntity.created(location).build();
        } catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }

    private URI getUri(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

//    @PutMapping("/{id}")
//    public String atualizarCarro(@PathVariable("id") Long id, @RequestBody Carro carro){
//        service.update(carro, id);
//
//        return "Carro atualizado com sucesso: " + carro.getId();
//    }

    @DeleteMapping("/{id}")
    public String deletarCarro(@PathVariable("id") Long id){
        service.delete(id);
        return "Carro deletado com sucesso";
    }


}
