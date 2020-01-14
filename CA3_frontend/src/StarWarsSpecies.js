import React from "react"


    const StarWarsSpecies = ({starwarsspecies}) => {
        return (
            <div>
            <ul>
                <li>
                     <pre>{JSON.stringify(starwarsspecies, null, 2)}</pre>
                </li>
                {/* <li>
                        Name:<pre>{JSON.stringify(starwarsfilm.name)}</pre>
                </li> */}
             </ul>
        </div>
        );
    };
    
    export default StarWarsSpecies;