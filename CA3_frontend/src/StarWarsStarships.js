import React from "react"


    const StarWarsStarships = ({starwarsstarships}) => {
        return (
            <div>
            <ul>
                <li>
                     <pre>{JSON.stringify(starwarsstarships, null, 2)}</pre>
                </li>
                {/* <li>
                        Name:<pre>{JSON.stringify(starwarsfilm.name)}</pre>
                </li> */}
             </ul>
        </div>
        );
    };
    
    export default StarWarsStarships;