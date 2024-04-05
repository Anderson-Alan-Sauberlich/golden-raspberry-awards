package org.desafioapi.service;

import org.desafioapi.dto.ProducerMinMaxPrizesDTO;
import org.desafioapi.dto.ProducerPrizesDTO;
import org.desafioapi.entity.Movie;
import org.desafioapi.entity.MovieProducer;
import org.desafioapi.entity.Producer;
import org.desafioapi.enumerator.Classification;
import org.desafioapi.enumerator.Score;
import org.desafioapi.repository.MovieProducerRepository;
import org.desafioapi.repository.ProducerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProducerService {
	
	private Logger logger = LoggerFactory.getLogger(ProducerService.class);
	
	@Autowired
	private ProducerRepository producerRepository;
	
	@Autowired
	private MovieProducerRepository movieProducerRepository;
	
	public void saveProducers(Movie movie, String producers) {
		for (String strProducer : producers.split(",|\\ and ")) {
			Producer producer = new Producer(strProducer.trim());
			
			Example<Producer> example = Example.of(producer); 
			
			if (producerRepository.exists(example)) {
				producer = producerRepository.findByName(strProducer.trim());
			} else {
				producer = producerRepository.save(producer);
			}
			
			movieProducerRepository.save(new MovieProducer(movie, producer));
		}
	}
	
	public ProducerMinMaxPrizesDTO getMaxAndMinPrizes() {
		List<MovieProducer> mpList = movieProducerRepository.findByMovieWinnerOrderByProducerId(true);
		
		ProducerMinMaxPrizesDTO dto = new ProducerMinMaxPrizesDTO();
		dto.addMin(findInterval(mpList, Score.MIN, Classification.FIRST));
		dto.addMin(findInterval(mpList, Score.MIN, Classification.SECOND));
		dto.addMax(findInterval(mpList, Score.MAX, Classification.FIRST));
		dto.addMax(findInterval(mpList, Score.MAX, Classification.SECOND));
		
		return dto;
	}

	private ProducerPrizesDTO findInterval(List<MovieProducer> mpList, Score score, Classification classification) {
		int interV = score.equals(Score.MIN) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
		ProducerPrizesDTO prize1 = new ProducerPrizesDTO(null, interV, null, null);
		ProducerPrizesDTO prize2 = new ProducerPrizesDTO(null, interV, null, null);
		
		for ( int i = 0; i < mpList.size() - 1; i++ ) {
			
			for (int j = i + 1; j < mpList.size(); j++) {
				
				MovieProducer mpi = mpList.get(i);
				MovieProducer mpj = mpList.get(j);
				
				if (mpi.getProducer().equals(mpj.getProducer())) {
					Integer interval = Math.abs(mpi.getMovie().getYearRelease() - mpj.getMovie().getYearRelease());
					
					if (switch (score) {
						case MIN -> interval <= prize1.getInterval();
						case MAX -> interval >= prize1.getInterval();
					}) {

						prize2 = new ProducerPrizesDTO(prize1.getProducer(), prize1.getInterval(), prize1.getPreviousWin(), prize1.getFollowingWin());

						prize1.setInterval(interval);
						prize1.setProducer(mpi.getProducer().getName());
						prize1.setPreviousWin(mpi.getMovie().getYearRelease());
						prize1.setFollowingWin(mpj.getMovie().getYearRelease());
						
						break;
					}
				}
			}
		}

        return switch (classification) {
            case FIRST -> prize1;
            case SECOND -> prize2;
        };

	}

}
