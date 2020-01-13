import React from "react"


    const StarWarsPlan = ({starwarsplan}) => {
        return (
            <div>
            <ul>
                <li>
                     <pre>{JSON.stringify(starwarsplan, null, 2)}</pre>
                </li>
                <li>
                        Name:<pre>{JSON.stringify(starwarsplan.name)}</pre>
                </li>
             </ul>
        </div>
        );
    };
    
    export default StarWarsPlan;