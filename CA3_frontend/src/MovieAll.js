import React from "react"


    const MovieAll = ({movieall}) => {
        return (
            <div>
            <ul>
                <li>
                     <pre>{JSON.stringify(movieall, null, 2)}</pre>
                </li>

             </ul>
        </div>
        );
    };
    
    export default MovieAll;