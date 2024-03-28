package org.desafioapi.service;

import org.desafioapi.dto.MovieDTO;
import org.desafioapi.dto.YearReleaseWinnerMovieDTO;
import org.desafioapi.dto.YearWinnerDTO;
import org.desafioapi.entity.Movie;
import org.desafioapi.exceptions.BadRequestException;
import org.desafioapi.exceptions.ResourceNotFoundException;
import org.desafioapi.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
	
	Logger logger = LoggerFactory.getLogger(MovieService.class);
	
	@Autowired
	private MovieRepository movieRepository;
	
	public List<Movie> getMoviesFromAYear(Integer yearRelease) {
		return movieRepository.findByYearRelease(yearRelease);
	}
	
	public List<MovieDTO> getMoviesByYearRelease(Integer yearRelease) {
		List<Movie> movies = movieRepository.findByYearRelease(yearRelease);
		
		if (movies == null || movies.isEmpty()) {
			return new ArrayList<>();
		} 
		
		List<MovieDTO> moviesDto = new ArrayList<>();
		for (Movie m : movies) {
			moviesDto.add(new MovieDTO(m));
		}
		
		return moviesDto;		
	}
	
	public YearWinnerDTO getYearReleasesWithMoreThanOneWinners() {
		List<YearReleaseWinnerMovieDTO> years = movieRepository.findYearReleasesWithMoreThanOneWinner();
		if (years == null || years.isEmpty()) {
			return new YearWinnerDTO();
		}
		return new YearWinnerDTO(years);
	}

	public void remove(Long id) {
		Optional<Movie> optional = movieRepository.findById(id);
		
		if (optional.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		
		Movie movie = optional.get();
		if ( movie.getWinner() ) {
			throw new BadRequestException();
		}
		
		movieRepository.delete(movie);
	}
	
}
