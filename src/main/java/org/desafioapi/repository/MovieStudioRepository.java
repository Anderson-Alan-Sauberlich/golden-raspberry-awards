package org.desafioapi.repository;

import org.desafioapi.entity.MovieStudio;
import org.desafioapi.entity.MovieStudioId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieStudioRepository extends JpaRepository<MovieStudio, MovieStudioId> {

}
