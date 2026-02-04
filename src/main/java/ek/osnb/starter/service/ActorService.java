package ek.osnb.starter.service;

import ek.osnb.starter.exceptions.NotFoundException;
import ek.osnb.starter.model.Actor;
import ek.osnb.starter.model.Movie;
import ek.osnb.starter.repository.ActorRepository;
import ek.osnb.starter.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorService {
    private final ActorRepository actorRepository;
    private final MovieRepository movieRepository;

    public ActorService(ActorRepository actorRepository, MovieRepository movieRepository) {
        this.actorRepository = actorRepository;
        this.movieRepository = movieRepository;
    }

    public Actor createActor(Actor actor) {
        return actorRepository.save(actor);
    }

    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    public Actor getActorById(Long id) {
        Optional<Actor> actorOptional = actorRepository.findById(id);
        if (actorOptional.isEmpty()) {
            throw new NotFoundException("Actor not found with id: " + id);
        }
        // Or use shortcut:
        // return actorRepository.findById(id).orElseThrow(() -> new NotFoundException("Actor not found with id: " + id));

        return actorOptional.get();
    }

    public Movie addActorToMovie(Long movieId, Long actorId) {
        // TODO: Find the movie by ID (throw exception if not found)
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        // TODO: Find the actor by ID (throw exception if not found)
        Actor actor = actorRepository.findById(actorId)
                .orElseThrow(() -> new RuntimeException("Actor not found"));

        // TODO: Add actor to movie's actors list
        movie.getActors().add(actor);

        // TODO: Save and return the updated movie
        return movieRepository.save(movie);
    }
}
