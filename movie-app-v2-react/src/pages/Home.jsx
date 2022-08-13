import React from "react";
import Navi from "../layouts/Navi";
import MovieList from "./MovieList";


export default function Home() {

    return(
        <div>
            <Navi />
            <MovieList />     
        </div>
    )
}