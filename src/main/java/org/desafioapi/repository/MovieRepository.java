package org.desafioapi.repository;

import org.desafioapi.dto.YearReleaseWinnerMovieDTO;
import org.desafioapi.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	List<Movie> findByYearRelease(Integer yearRelease);
	
}
