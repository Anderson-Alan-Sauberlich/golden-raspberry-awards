package org.desafioapi.dto;

import java.util.ArrayList;
import java.util.List;

public class YearWinnerDTO {
	
	private List<YearReleaseWinnerMovieDTO> years;
	
	public YearWinnerDTO(List<YearReleaseWinnerMovieDTO> years) {
		this.years = new ArrayList<>();
		this.years.addAll(years);
	}
	
	public YearWinnerDTO() {
		this.years = new ArrayList<>();
	}

	public List<YearReleaseWinnerMovieDTO> getYears() {
		return years;
	}

	public void setYears(List<YearReleaseWinnerMovieDTO> years) {
		this.years = years;
	}

}
