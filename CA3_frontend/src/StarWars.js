import React, { useState, useEffect} from "react"

    //nedenunder får vi bare hele endpointet ud som json. Vi vil også gerne have hvert 
    //element ud hver for sig
    //koden virker ikke hvis man logger ind på http://localhost:3000/starwars, 
    //det skal være uden /starwars , måske skal man også lige vente 5 sek(?)
    const StarWars = ({starwars},props) => {
        //let match = useRouteMatch();
        
        const inival = "";
        const [val, setVal] = useState(inival);  // ins "starwars." + i usestate

    function handleSubmit(event){
        function choose(){
            if(val === "name"){
                return starwars.name;
            }
            if(val === "height"){
                return starwars.height;
            }
            if(val==="mass"){
                return starwars.mass;
            }
            if(val==="hair_color"){
                return starwars.hair_color;
            }
            if(val==="skin_color"){
                return starwars.skin_color;
            }
            else{
                return starwars.gender;
            }
        }
        //event.preventdefault();
        if(event.target.id === "val"){
            setVal("starwars" + event.target.value);
        }
        const test1= JSON.stringify("starwars.val");
        const test = JSON.stringify(val);
        const test2 = test1.replace("val", JSON.parse(test));
        window.alert(choose());
        //JSON.parse("starwars.name") should work but doesnt
        //JSON.parse(test2)
        //JSON.stringify(starwars.val)
        console.log(val);
        console.log(starwars);
        console.log(starwars.val);
    }
    function handleChange(event){
        const target = event.target;
      const id = target.id;
      const value = target.value;
      if(id === "val"){
          setVal(value);
      }
    }
        return (
           <div>
               <ul>
                   <li>
                        <pre>{JSON.stringify(starwars, null, 2)}</pre>
                   </li>
                   <li>
                        {/* Name:<pre>{JSON.stringify(starwars.name)}</pre> */}
                   </li>
                   <li>
                       <form onSubmit={handleSubmit} >
                           <label>
                            <input id ="val" type="text"  value={val} onChange={handleChange}/>
                           </label>
                       <input type="submit" value="Submit" />
                       </form>
                        Output: {val}
                       
                       
                       {}
                   </li>
                </ul>
           </div>
        );
    };
    
    export default StarWars;