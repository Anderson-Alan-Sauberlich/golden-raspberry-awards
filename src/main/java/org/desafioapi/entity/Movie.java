package org.desafioapi.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="MOVIE")
public class Movie {
	
	@Id
	@Column(name="ID_MOVIE")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="YEARRELEASE", nullable=false)
	private Integer yearRelease;
	
	@Column(name="TITLE", nullable=false)
	private String title;
	
	@Column(name="IS_WINNER", nullable=false)
	private Boolean winner;

	@OneToMany(mappedBy="movie", cascade=CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
	private Set<MovieStudio> studios = new HashSet<>();
	
	@OneToMany(mappedBy="movie", cascade=CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
	private Set<MovieProducer> producers = new HashSet<>();
	
	public Movie() {}
	
	public Movie(Integer yearRelease, String title, String winner) {
		this.yearRelease = yearRelease;
		this.title = title;
		this.winner = ("yes".equalsIgnoreCase(winner)) ;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getYearRelease() {
		return yearRelease;
	}

	public void setYearRelease(Integer yearRelease) {
		this.yearRelease = yearRelease;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getWinner() {
		return winner;
	}

	public void setWinner(Boolean winner) {
		this.winner = winner;
	}

	public Set<MovieStudio> getStudios() {
		return studios;
	}

	public void setStudios(Set<MovieStudio> studios) {
		this.studios = studios;
	}

	public Set<MovieProducer> getProducers() {
		return producers;
	}

	public void setProducers(Set<MovieProducer> producers) {
		this.producers = producers;
	}
	
	@Override
	public String toString() {
		return "Year: "+ getYearRelease() + " - Title: "+ getTitle() + " - Winner: "+ getWinner();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		
		Movie other = (Movie) obj;
		return Objects.equals(id, other.getId());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, yearRelease);
	}
}
