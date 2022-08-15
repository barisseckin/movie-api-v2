import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Button, Form } from "semantic-ui-react";
import Navi from "../layouts/Navi";
import MovieService from "../services/MovieService";
import MovieCommentService from "../services/MovieCommentService";


export default function MovieDetails() {

    let movieCommentService = new MovieCommentService();


    const {name} = useParams();
    const [movies, setMovies] = useState([]);

    let movieService = new MovieService();

   useEffect(() => {
    movieService.getMovieByName(name).then(response => setMovies(response.data));
   }, [])

   const createMovieComment = function(movieName, userMail, body) {
        movieCommentService.save(movieName, userMail, body);
   }

    return(
        <div>
            <Navi />

            <Form>
                <Form.Input
                
                fluid
                label='Comment'
                placeholder='comment...'
                id='body'
                />
                <Form.Checkbox
                label='I agree to the Terms and Conditions'
                />
                <Button 
                onClick={() => createMovieComment(name, "barisseckin0@gmail.com", )}
                basic
                color="green"
                >Send</Button>
            </Form>


        </div>


    
    )

}