package org.desafioapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.desafioapi.entity.Movie;
import org.desafioapi.repository.MovieRepository;
import org.desafioapi.service.ProducerService;
import org.desafioapi.service.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

@SpringBootApplication(scanBasePackages = { "org.desafioapi" })
@OpenAPIDefinition(info = @Info(title = "Golden Raspberry Awards API", version = "1.0", description = "Golden Raspberry Awards API"))
public class Application {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private StudioService studioService;
	
	@Autowired
	private ProducerService producerService;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext appContext) {
		return args -> {
			InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("movielist.csv");
			Reader reader = new InputStreamReader(in);
			Iterable<CSVRecord> records = CSVFormat.RFC4180
					.withDelimiter(';')
					.withHeader("year","title","studios","producers","winner")
					.parse(reader);

			for (CSVRecord record : records) {
				if (record.getRecordNumber() == 1) {
					continue;
				}

				String winner = record.get("winner");
				Movie movie = movieRepository.save(new Movie(Integer.valueOf(record.get("year")), record.get("title"), winner));

				String studios = record.get("studios");
				studioService.saveStudios(movie, studios);

				String producers = record.get("producers");
				producerService.saveProducers(movie, producers);
			}
        };
	}
	
}
