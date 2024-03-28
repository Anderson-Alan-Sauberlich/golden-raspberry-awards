package org.desafioapi.repository;

import org.desafioapi.dto.YearReleaseWinnerMovieDTO;
import org.desafioapi.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	List<Movie> findByYearRelease(Integer yearRelease);
	
	@Query(value="select new org.desafioapi.dto.YearReleaseWinnerMovieDTO(movie.yearRelease, count(movie.winner)) "
			+ "from Movie as movie where movie.winner=true and count(movie.winner) > 1 group by movie.yearRelease")
	List<YearReleaseWinnerMovieDTO> findYearReleasesWithMoreThanOneWinner();
	
}
