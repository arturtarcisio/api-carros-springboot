package com.arturtarcisio.carros;

import com.arturtarcisio.carros.domain.Carro;
import com.arturtarcisio.carros.domain.dto.CarroDTO;
import com.arturtarcisio.carros.exception.ObjectNotFoundException;
import com.arturtarcisio.carros.service.CarroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarrosServiceTests {

    @Autowired
    private CarroService service;

    @Test
    public void testeInsercaoDeCarros() {
        Carro carro = new Carro();
        carro.setNome("Ferrari");
        carro.setTipo("Luxo");

        CarroDTO c = service.insert(carro);

        assertNotNull(c);

        Long id = c.getId();
        assertNotNull(id);

        //Buscar o objeto
        c = service.getCarroById(id);
        assertNotNull(c);

        assertEquals("Ferrari", c.getNome());
        assertEquals("Luxo", c.getTipo());

        //Deletar o objeto
        service.delete(id);

        //Verificar se deletou o objeto
        try{
            assertNull(service.getCarroById(id));
            fail("O carro não foi excluído");
        } catch (ObjectNotFoundException e){
            // OK
        }

     }

     @Test
     public void testeLista(){

        List<CarroDTO> carros = service.getCarros();
        assertEquals(30, carros.size());
     }

    @Test
    public void testeGet(){
        CarroDTO c = service.getCarroById(11L);
        assertNotNull(c);

        assertEquals("Ferrari FF", c.getNome());
    }

    @Test
    public void testeListaPorTipo(){
        assertEquals(10, service.getCarroByTipo("classicos").size());
        assertEquals(10, service.getCarroByTipo("esportivos").size());
        assertEquals(10, service.getCarroByTipo("luxo").size());
        assertEquals(0, service.getCarroByTipo("x").size());

    }

}
