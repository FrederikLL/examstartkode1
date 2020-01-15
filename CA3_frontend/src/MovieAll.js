import React, { useState, useEffect} from "react"
import {
    BrowserRouter as Router, 
    Route, 
    Link, 
    Switch,
    useRouteMatch,
    useParams} from "react-router-dom";

    const MovieAll = () => {
        
        const URL = "https://fredll.dk/oneexam-1/api/movie/movie-info-all/"; //?§Queryparams
  //const URL = "https://fredll.dk/CA3back/api/info";
    
    
    let match = useRouteMatch();
     const [movieall,setMovieAll] = useState();
     const inival = "Grease";
     const [val, setVal] = useState("Hair")
     const [isStarted, setIsStarted] = useState(false);
     console.log(val);
     console.log(movieall);
     



        function handleSubmit(event){
           if(event.target.id === "val"){
               setVal(event.target.value);
                Router.push('/Grease?' + val)
                
               console.log(val);
       }
        
      }

     function handleChange(event){
         const target = event.target;
       const id = target.id;
       const value = target.value;
       
       if(id === "val"){
        
        }
        setIsStarted(true);
        setVal(value);
       
       
     }
    
            function fetchDatamovieAll() {
              fetch(URL + val, {
                headers : { 
                  'Content-Type': 'application/json',
                  'Accept': 'application/json',
                  'x-access-token': 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOiJ1c2VyIiwiZXhwIjox' +
                  'NTc5MDUxNzAzLCJpYXQiOjE1NzkwNDk5MDMsImlzc3VlciI6InNlbWVzdGVyc3RhcnRjb2RlLWRhdDMiLCJ1c2VybmF' +
                  'tZSI6InVzZXIifQ.agPpYLPiF7Y4gDeUpJVy3Yzvhgsc1redVCnqoZEPYLo'
                 }
          
              })
              .then(response=>response.json())
              .then(data => {
                  setMovieAll( data)});
              
              }

              useEffect(() => {

                fetchDatamovieAll();
        
              }, [isStarted]);
              if(isStarted===true){
                  setIsStarted(false);
              }



        return (
            <div>
            <ul>
                <li>
                    {movieall}
                </li>
                <li>
                       <form onSubmit={handleSubmit}>   
                           <label>
                           <li><Link to={`${match.url}/`}><h2>Copy your movie name in and click here!</h2></Link></li>
                               
                            <input id ="val" type="text"  value={val} onChange={handleChange}/>
                           </label>
                       {/* <input type="submit" value="Submit" /> */}
                       
                       </form>
                       Output: {val} 
                       <h2>Here is some examples!</h2>

                        <ul>
                            <li><Link to={`${match.url}/:Grease`}>Grease</Link></li>
                            <li><Link to={`${match.url}/:Hair`}>Hair</Link></li>
                            <li><Link to={`${match.url}/:Die Hard`}>Die Hard</Link></li>
                            <li><Link to={`${match.url}/:Animal House`}>Animal House</Link></li>
                            <li><Link to={`${match.url}/Taxi Driver`}>Taxi Driver</Link></li>
                            <li><Link to={`${match.url}/Close Encounters of the Third Kind`}>Close Encounters of the Third Kind</Link></li>
                            <li><Link to={`${match.url}/The Last Waltz`}>The Last Waltz</Link></li>
                            <li><Link to={`${match.url}/Empire of the Sun`}>Empire of the Sun</Link></li>
                            <li><Link to={`${match.url}/Alien Nation`}>Alien Nation</Link></li>
                            {/* <li><Link to="/Moviename">Grease</Link></li> */}
                       </ul>
                       <Switch>
                        <Route path={`${match.path}/:title`} ></Route>
                        
                        
                        </Switch>
                     
                   </li>
             </ul>
        </div>
        );
    };
    
    export default MovieAll;