package org.desafioapi.repository;

import org.desafioapi.entity.MovieProducer;
import org.desafioapi.entity.MovieProducerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface MovieProducerRepository extends JpaRepository<MovieProducer, MovieProducerId> {
	
	@Query(value="select mp from MovieProducer as mp join mp.movie as movie join mp.producer as producer "
			+ "where movie.winner = true order by producer.id, movie.yearRelease")
	List<MovieProducer> findByMovieWinnerOrderByProducerId(Boolean isWinner);
	
}
