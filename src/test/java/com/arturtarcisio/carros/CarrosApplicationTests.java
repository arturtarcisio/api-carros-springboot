package com.arturtarcisio.carros;

import com.arturtarcisio.carros.domain.Carro;
import com.arturtarcisio.carros.service.CarroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CarrosApplicationTests {

    @Autowired
    private CarroService service;

    @Test
    public void test1() {
        Carro carro = new Carro();
        carro.setNome("Ferrari");
        carro.setTipo("Luxo");

        service.insert(carro);
     }

     @Test
     public void test2(){

     }

}
