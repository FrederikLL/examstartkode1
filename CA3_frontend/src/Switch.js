import React, { useState, useEffect } from 'react';
import {
BrowserRouter as Router, 
Route, 
Switch,
} from "react-router-dom";
import StarWars from "./StarWars";
import Users from "./Users";
import Admins from "./Admin";
import Login from "./Login";
import Home from "./Home";

import MovieSimple from "./MovieSimple";
import MovieAll from "./MovieAll";

const Switches = () => {
  const URL = "http://localhost:8080/oneexam/api/info";
  //const URL = "https://fredll.dk/CA3back/api/info";
    //fetch her måske siden det er parent component til vores 3 fetch sider.
     const [starwars,setStarwars] = useState();
     const [moviesimple,setMoviesimple] = useState();
     const [movieall,setMovieAll] = useState();
    async function fetchDataPers() {
    fetch(URL+'/person/1')
    .then(response=>response.json())
    .then(data=> setStarwars( data )); 
    }

            async function fetchDatamoviesimple() {
              fetch("http://localhost:8080/oneexam/api/movie/movie-info-simple/Hair")
              .then(response=>response.json())
              .then(data=> setMoviesimple( data )); 
              }
              async function fetchDatamovieall() {
                fetch("http://localhost:8080/oneexam/api/movie/movie-info-all/Hair")
                .then(response=>response.json())
                .then(data=> setMovieAll( data )); 
                }

    useEffect(() => {

        fetchDataPers();
        fetchDatamoviesimple();

        fetchDatamovieall();
        

      
      }, []); 

    return (
    <Switch>
      <Route path="/starwars">
        <StarWars starwars={starwars}/>
      </Route>
      <Route path="/users">
        <Users />
      </Route>
      <Route path="/admins">
        <Admins />
      </Route>
      <Route path="/moviesimple">
        <MovieSimple moviesimple={moviesimple}/>
      </Route>
      <Route path="/movieall">
        <MovieAll movieall={movieall}/>
      </Route>
      <Route path="/">
        <Home />
      </Route>
    </Switch>
    );
  }

  export default Switches;