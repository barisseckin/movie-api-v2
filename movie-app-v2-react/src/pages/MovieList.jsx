import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { Button, Card, Grid } from 'semantic-ui-react'
import MovieService from "../services/MovieService";

export default function MovieList() {

  const [movies, setMovies] = useState([]);

  let movieService = new MovieService();

  useEffect(() => {
    movieService.getMovies().then((result) => setMovies(result.data));
  }, []);

  const increaseMovieRating = (movieName) => {
    movieService.increaseMovieRating(movieName);
  }

    return (
      <div>
      
        <Grid>
          <Grid.Row columns={2}>
              <Grid.Column width={13}>

                <Card.Group itemsPerRow={6}>

                {movies.map((movie) => (

                  <Card
                    image={movie.imageUrl}
                    header={<a href={movie.link}>{movie.name}</a>}
                    meta={movie.rating}
                    description={movie.description}
                    extra={
                      <Button 
                      basic 
                      color="blue"
                      onClick={() => increaseMovieRating(movie.name)}>Like</Button>}
                  />

                ))}
                    
              </Card.Group>
            </Grid.Column>
          </Grid.Row>
        </Grid>

      </div>
    );
}
