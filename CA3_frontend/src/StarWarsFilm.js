import React from "react"


    const StarWarsFilm = ({starwarsfilm}) => {
        return (
            <div>
            <ul>
                <li>
                     <pre>{JSON.stringify(starwarsfilm, null, 2)}</pre>
                </li>
                {/* <li>
                        Name:<pre>{JSON.stringify(starwarsfilm.name)}</pre>
                </li> */}
             </ul>
        </div>
        );
    };
    
    export default StarWarsFilm;