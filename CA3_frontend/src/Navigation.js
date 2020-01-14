import React from "react"
import {
    BrowserRouter as Router, 
   
    Link, 
    } from "react-router-dom";
import "./App.css";
import "./index.css";

    const Navigation = () => {

        return (
            <nav>
        <ul className="header">
          <li>
            <Link to="/">Home</Link>
          </li>
          <li>
            <Link to="/users">See all users</Link>
          </li>
          <li>
            <Link to="/admins">See all admins</Link>
          </li>
          <li>
            <Link to="/starwars">Starwars Person</Link>
          </li>
          <li>
            <Link to="/starwarsplan">Starwars Planet</Link>
          </li>
          <li>
            <Link to="/starwarsfilm">Starwars Film</Link>
          </li>
          <li>
            <Link to="/starwarsspecies">Starwars Species</Link>
          </li>
          <li>
            <Link to="/starwarsstarships">Starwars Starships</Link>
          </li>
        </ul>
     </nav>
        );
    };
    
    export default Navigation;