import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import Home from './pages/Home';
import MovieDetails from './pages/MovieDetails';

function App() {

  return (
    <BrowserRouter>
    <div className="App">

      <Routes>
        <Route exact path='' element={<Home />}></Route>
        <Route exact path='/movie-details/:name' element={<MovieDetails />}></Route>
      </Routes>

    </div>

    </BrowserRouter>
  );
}

export default App;
