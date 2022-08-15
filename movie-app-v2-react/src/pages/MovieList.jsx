import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { Button, Card, Grid, Icon, Label, Menu } from 'semantic-ui-react'
import MovieService from "../services/MovieService";


export default function MovieList() {

  const [movies, setMovies] = useState([]);
  const [liked, setLiked] = useState(false);

  let movieService = new MovieService();

  useEffect(() => {
    movieService.getMovies().then((result) => setMovies(result.data));
  }, []);

  const increaseMovieRating = (movieName) => {

    if(!liked) {
      setLiked(true);
      movieService.increaseMovieRating(movieName);
    }
      
    if(liked) {
      setLiked(false);
      movieService.reduceTheRating(movieName);
    }
    
  }

    return (
      <div>

        <Grid>
          <Grid.Row columns={2}>
              <Grid.Column width={14}>

                <Card.Group itemsPerRow={6}>

                {movies.map((movie) => (

                  <Card
                    image={movie.imageUrl}
                    header={<a href={movie.link}>{movie.name}</a>}
                    meta={movie.language}
                    description={movie.description}
                    extra={
                      <div>
                      <Button as='div' labelPosition='right'>
                      <Button 
                        color='white'
                        onClick={() => {increaseMovieRating(movie.name)}}>
                        <Icon name='heart' style={liked? {color: "red"} : null}/>
                        Like
                      </Button>
                      <Label as='a' basic color='white' pointing='right'>
                        {movie.rating}
                      </Label>
                    </Button>
                      <Link to={{pathname: '/movie-details/' + movie.name}}>
                      <Button
                        basic
                        color="blue"
                      >
                      Details
                      </Button>
                      </Link>

                     </div>}
                  />

                ))}
                    
              </Card.Group>
            </Grid.Column>
          </Grid.Row>
        </Grid>

      </div>
    );
}
