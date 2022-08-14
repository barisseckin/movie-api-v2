import axios from "axios";

export default class MovieService {

    getMovies() {
        return axios.get("http://localhost:8080/v2/movies");
    }

    saveMovie(name, description, producer, releaseYear, language, subTitleLanguage, link, imageUrl, categoryName) {

        return axios.post("http://localhost:8080/v2/movies", {
            name: name,
            description: description,
            producer: producer,
            releaseYear: releaseYear,
            language: language,
            subTitleLanguage: subTitleLanguage,
            link: link,
            imageUrl: imageUrl, 
            categoryName: categoryName
        });
    }

    increaseMovieRating(movieName) {

        return axios.put("http://localhost:8080/v2/movies/increase-rating/"+movieName);
    }

    getMovieByName(movieName) {

        return axios.get("http://localhost:8080/v2/movies/get-by-name/"+movieName);
    }

}