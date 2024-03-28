package org.desafioapi.dto;

import org.desafioapi.entity.Movie;
import org.desafioapi.entity.MovieProducer;
import org.desafioapi.entity.MovieStudio;
import java.util.ArrayList;
import java.util.List;

public class MovieDTO {
	
	private Long id;
	
	private Integer yearRelease;
	
	private String title;
	
	private List<String> studios = new ArrayList<>();
	
	private List<String> producers = new ArrayList<>();
	
	private Boolean winner;
	
	public MovieDTO(Movie movie) {
		this.id = movie.getId();
		this.yearRelease = movie.getYearRelease();
		this.title = movie.getTitle();
		this.winner = movie.getWinner();
		
		for (MovieStudio ms : movie.getStudios()) {
			this.studios.add(ms.getStudio().getName());
		}
		
		for (MovieProducer mp : movie.getProducers()) {
			this.producers.add(mp.getProducer().getName());
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getYear() {
		return yearRelease;
	}

	public void setYear(Integer yearRelease) {
		this.yearRelease = yearRelease;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getStudios() {
		return studios;
	}

	public void setStudios(List<String> studios) {
		this.studios = studios;
	}

	public List<String> getProducers() {
		return producers;
	}

	public void setProducers(List<String> producers) {
		this.producers = producers;
	}

	public Boolean getWinner() {
		return winner;
	}

	public void setWinner(Boolean winner) {
		this.winner = winner;
	}
	
}
