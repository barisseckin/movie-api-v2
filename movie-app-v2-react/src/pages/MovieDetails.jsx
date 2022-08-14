import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import MovieService from "../services/MovieService";

export default function MovieDetails() {

    const {name} = useParams();

    const [movies, setMovies] = useState([]);

    let movieService = new MovieService();

   useEffect(() => {
    movieService.getMovieByName(name).then(response => setMovies(response.data));
   }, [])

    return(
        <div>

            {movies.name}


        </div>


    
    )

}