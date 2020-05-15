package com.arturtarcisio.carros;

import com.arturtarcisio.carros.domain.Carro;
import com.arturtarcisio.carros.domain.dto.CarroDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CarrosApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarrosAPITeste {
    @Autowired
    protected TestRestTemplate rest;

    private ResponseEntity<CarroDTO> getCarro(String url){
        return rest.getForEntity(url, CarroDTO.class);
    }

    private ResponseEntity<List<CarroDTO>> getCarros(String url){
        return rest.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CarroDTO>>() {
                }
        );
    }

    @Test
    public void testSave(){
        Carro carro = new Carro();
        carro.setNome("Porshe");
        carro.setTipo("Esportivo");

        //Insert
        ResponseEntity response = rest.postForEntity("/api/v1/carros", carro, null);
        System.out.println(response);

        //Verifica se criou
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        //Busca o objeto
        String location =   response.getHeaders().get("location").get(0);
        CarroDTO c = getCarro(location).getBody();

        //Verifica se o objeto é igual ao que foi inserido
        assertNotNull(c);
        assertEquals("Porshe", c.getNome());
        assertEquals("Esportivo", c.getTipo());

        //Deletar o objeto
        rest.delete(location);

        //Verificar se deletou
        assertEquals(HttpStatus.NOT_FOUND, getCarro(location).getStatusCode());
    }

    @Test
    public void testGetNotFound(){
            ResponseEntity response = getCarro("/api/v1/carros/98343");
            assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testGetOk(){
        ResponseEntity<CarroDTO> response = getCarro("/api/v1/carros/11");
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        CarroDTO c = response.getBody();
        assertEquals("Ferrari FF", c.getNome());
    }

//    @Test
//    public void testListaPorTipo(){
//        assertEquals(10, getCarros("/api/v1/carros/classicos").getBody().size());
//        assertEquals(10, getCarros("/api/v1/carros/esportivos").getBody().size());
//        assertEquals(10, getCarros("/api/v1/carros/luxo").getBody().size());
//
//        assertEquals(HttpStatus.NO_CONTENT, getCarros("/api/v1/carros/popular").getStatusCode());
//    }
}
