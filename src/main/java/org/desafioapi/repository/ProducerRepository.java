package org.desafioapi.repository;

import org.desafioapi.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
	
	Producer findByName(String name);
	
}
