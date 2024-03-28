package org.desafioapi.dto;

public class YearReleaseWinnerMovieDTO {
	
	private Integer yearRelease;
	
	private Integer winnerCount;
	
	public YearReleaseWinnerMovieDTO(Integer yearRelease, Integer winnerCount) {
		this.yearRelease = yearRelease;
		this.winnerCount = winnerCount;
	}

	public Integer getYearRelease() {
		return yearRelease;
	}

	public void setYearRelease(Integer yearRelease) {
		this.yearRelease = yearRelease;
	}

	public Integer getWinnerCount() {
		return winnerCount;
	}

	public void setWinnerCount(Integer winnerCount) {
		this.winnerCount = winnerCount;
	}
	
}
