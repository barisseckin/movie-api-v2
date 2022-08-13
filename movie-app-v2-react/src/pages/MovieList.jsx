import React, { useEffect, useState } from "react";
import { Button, Card } from 'semantic-ui-react'
import MovieService from "../services/MovieService";

export default function MovieList() {

  const [movies, setMovies] = useState([]);

  let movieService = new MovieService();

  useEffect(() => {
    
    movieService.getMovies().then((result) => setMovies(result.data));
  }, [])

  const increaseMovieRating = (movieName) => {
    movieService.increaseMovieRating(movieName);
  }

  return (
    <div>

      {movies.map((movie) => (
        <Card
          image={movie.imageUrl}
          header={movie.name}
          meta={movie.rating}
          description={movie.description}
          extra={<Button 
            basic 
            color="blue"
            onClick={() => increaseMovieRating(movie.name)}>Like</Button>}
        />
      ))}

    </div>
  );
}
