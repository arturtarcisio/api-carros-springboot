package com.arturtarcisio.carros.repository;

import com.arturtarcisio.carros.domain.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long >{
}
