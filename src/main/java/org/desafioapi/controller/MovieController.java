package org.desafioapi.controller;

import org.desafioapi.dto.MovieDTO;
import org.desafioapi.dto.YearWinnerDTO;
import org.desafioapi.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
	
	Logger logger = LoggerFactory.getLogger(MovieController.class);
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping("/{yearRelease}")
    public ResponseEntity<List<MovieDTO>> getMovies(@PathVariable(name="yearRelease") Integer yearRelease) {
		List<MovieDTO> movies = movieService.getMoviesByYearRelease(yearRelease);
		
		HttpStatus status = HttpStatus.OK;
		if ( movies.isEmpty() ) {
			status = HttpStatus.NO_CONTENT;
		}
		
        return new ResponseEntity<List<MovieDTO>>( movies, status ) ;
    }
	
	/**
	 * @return {@link YearWinnerDTO}
	 */
	@GetMapping("/years")
	public ResponseEntity<YearWinnerDTO> getYearsWithMoreThanOneWinners() {
		YearWinnerDTO dto = movieService.getYearReleasesWithMoreThanOneWinners();
		
		HttpStatus status = HttpStatus.OK;
		if ( dto.getYears().isEmpty() ) {
			status = HttpStatus.NO_CONTENT;
		}
		
		return new ResponseEntity<YearWinnerDTO>( dto, status ) ;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removeMovie(@PathVariable(name="id") Long id) {
		movieService.remove(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
