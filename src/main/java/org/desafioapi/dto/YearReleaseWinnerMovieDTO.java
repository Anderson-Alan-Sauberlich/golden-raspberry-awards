package org.desafioapi.dto;

public class YearReleaseWinnerMovieDTO {
	
	private Integer yearRelease;
	
	private Long winnerCount;
	
	public YearReleaseWinnerMovieDTO(Integer yearRelease, Long winnerCount) {
		this.yearRelease = yearRelease;
		this.winnerCount = winnerCount;
	}

	public Integer getYearRelease() {
		return yearRelease;
	}

	public void setYearRelease(Integer yearRelease) {
		this.yearRelease = yearRelease;
	}

	public Long getWinnerCount() {
		return winnerCount;
	}

	public void setWinnerCount(Long winnerCount) {
		this.winnerCount = winnerCount;
	}
	
}
