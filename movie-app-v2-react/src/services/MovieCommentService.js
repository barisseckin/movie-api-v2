import axios from "axios";

export default class MovieCommentService {

    save(movieName, userMail, body) {
        
        axios.post("http://localhost:8080/v2/movie-comments", {
            movieName: movieName,
            userMail: userMail,
            body: body
        })
    }
}