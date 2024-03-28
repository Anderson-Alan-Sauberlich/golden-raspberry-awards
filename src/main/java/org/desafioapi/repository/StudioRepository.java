package org.desafioapi.repository;

import org.desafioapi.dto.StudioWinDTO;
import org.desafioapi.entity.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface StudioRepository extends JpaRepository<Studio, Long> {
	
	Studio findByName(String name);
	
	@Query(value="select new org.desafioapi.dto.StudioWinDTO(studio.name, count(movie.winner)) "
			+ "from MovieStudio as ms join ms.movie as movie join ms.studio as studio "
			+ "where movie.winner=true group by studio.name order by 2 desc")
	List<StudioWinDTO> findByWinners();

}
