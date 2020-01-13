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
import StarWarsPlan from "./StarWarsPlan";
import StarWarsFilm from "./StarWarsFilm";
import StarWarsSpecies from "./StarWarsSpecies";
import StarWarsStarships from "./StarWarsStarships";

const Switches = () => {
  const URL = "http://localhost:8080/securitystarter/api/info";
  //const URL = "https://fredll.dk/CA3back/api/info";
    //fetch her måske siden det er parent component til vores 3 fetch sider.
     const [starwars,setStarwars] = useState();
     const [starwarsplan,setStarwarsplan] = useState();
     const [starwarsfilm,setStarwarsfilm] = useState();
     const [starwarsspecies,setStarwarsspecies] = useState();
     const [starwarsstarships,setStarwarsstarships] = useState();
    async function fetchDataPers() {
    fetch(URL+'/person/1')
    .then(response=>response.json())
    .then(data=> setStarwars( data )); 
    }
     async function fetchDataPlan() {
      fetch(URL+'/planet/1')
      .then(response=>response.json())
      .then(data=> setStarwarsplan( data )); 
      }
      async function fetchDatafilm() {
        fetch(URL+'/film/1')
        .then(response=>response.json())
        .then(data=> setStarwarsfilm( data )); 
        }
        async function fetchDataspecies() {
          fetch(URL+'/species/1')
          .then(response=>response.json())
          .then(data=> setStarwarsspecies( data )); 
          }
          async function fetchDatastarships() {
            fetch(URL+'/starship/1')
            .then(response=>response.json())
            .then(data=> setStarwarsstarships( data )); 
            }

    useEffect(() => {

        fetchDataPers();
        fetchDataPlan();
        fetchDatafilm();
        fetchDataspecies();
        fetchDatastarships();

      
      }, []); 

    return (
    <Switch>
      <Route path="/starwars">
        <StarWars starwars={starwars}/>
      </Route>
      <Route path="/starwarsplan">
        <StarWarsPlan starwarsplan={starwarsplan}/>
      </Route>
      <Route path="/starwarsfilm">
        <StarWarsFilm starwarsfilm={starwarsfilm}/>
      </Route>
      <Route path="/starwarsspecies">
        <StarWarsSpecies starwarsspecies={starwarsspecies}/>
      </Route>
      <Route path="/starwarsstarships">
        <StarWarsStarships starwarsstarships={starwarsstarships}/>
      </Route>
      <Route path="/users">
        <Users />
      </Route>
      <Route path="/admins">
        <Admins />
      </Route>
      <Route path="/">
        <Home />
      </Route>
    </Switch>
    );
  }

  export default Switches;