/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;


import facades.MovieFacade;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import utils.EMF_Creator;

/**
 *
 * @author Bruger
 */
@Path("/movie")
public class MovieResource {

    private static EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello anonymouss\"}";
    }
    
        @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("movie-info-simple/{title}")  
    public String movieInfo(@PathParam("title") String title) throws MalformedURLException, IOException{
    URL url = new URL("http://exam-1187.mydemos.dk/movieInfo/"+title);
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");
    con.setRequestProperty("Accept", "application/json;charset=UTF-8");
    con.setRequestProperty("User-Agent", "server"); //remember if you are using SWAPI
    Scanner scan = new Scanner(con.getInputStream());
    String jsonStr = null;
    if (scan.hasNext()) {
      jsonStr = scan.nextLine();
      //jsonStr += "\n";
    }
    scan.close();
    return jsonStr;
  }
    
//        @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("movie-info-simple/{title}")  
//    public String movieInfo(@PathParam("title") String title) throws MalformedURLException, IOException{
//    MovieFacade recipeFac = new MovieFacade();
//        String all = recipeFac.allFetch();
//        return all;
//  }
    
    //This endpoint must require a client to be authenticated with a sufficient role.
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("movie-info-all/{title}")
    public String getMovieInfoAll(@PathParam("title") String title) throws InterruptedException, ExecutionException {
        MovieFacade recipeFac = new MovieFacade();
        String all = recipeFac.criticFetch();
        return all;
    }
    


}


