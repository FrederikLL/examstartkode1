/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;


import com.google.gson.Gson;
import entities.Movie;
import facades.MovieFacade;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.annotation.security.RolesAllowed;
import javax.json.Json;
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
    int threads = 8;
    ExecutorService executorservice = Executors.newFixedThreadPool(threads);
//    String title = "";
//    String replaceAll;
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
    public String movieInfo(@PathParam("title") final String title) throws MalformedURLException, IOException, InterruptedException, ExecutionException{
    //title = title.replaceAll(" ", "%20");
    final String final1 = replaceAll(title);
    
    Callable<String> fetch1 = new Callable<String>() {
        @Override
        public String call() throws Exception {
            URL url = new URL("http://exam-1187.mydemos.dk/movieInfo/" + final1);
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
    };

    Callable<String> fetch2 = new Callable<String>() {
        @Override
        public String call() throws Exception {
            URL url = new URL("http://exam-1187.mydemos.dk/moviePoster/" + final1);
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
    };
    
    
        StringBuilder sb = new StringBuilder();
        Future<String> future1 = executorservice.submit(fetch1);
        Future<String> future2 = executorservice.submit(fetch2);
        //executorservice.
        String result1 = future1.get();
        String result2 = future2.get();
        sb = sb.append(result1);
        sb = sb.append(result2);
        String all = sb.toString();
        executorservice.shutdown();
        
       Gson gson = new Gson();
       String test = gson.toJson(all);
       
       
        
        return test;
    
    
//    URL url = new URL("http://exam-1187.mydemos.dk/movieInfo/" + title.replaceAll(" ", "%20"));
//    HttpURLConnection con = (HttpURLConnection) url.openConnection();
//    con.setRequestMethod("GET");
//    con.setRequestProperty("Accept", "application/json;charset=UTF-8");
//    con.setRequestProperty("User-Agent", "server"); //remember if you are using SWAPI
//    Scanner scan = new Scanner(con.getInputStream());
//    String jsonStr = null;
//    if (scan.hasNext()) {
//      jsonStr = scan.nextLine();
//      //jsonStr += "\n";
//    }
//    scan.close();
//    return jsonStr;
  }
    
//        @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("movie-info-simple/{title}")  
//    public String movieInfo(@PathParam("title") String title) throws MalformedURLException, IOException{
//    MovieFacade recipeFac = new MovieFacade();
//        String all = recipeFac.allFetch();
//        return all;
//  }
    
    //OBS!!!This endpoint must require a client 
    //to be authenticated with a sufficient role.
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("movie-info-all/{title}")
    @RolesAllowed("user")
    public String getMovieInfoAll(@PathParam("title") final String title) throws InterruptedException, ExecutionException {
//        MovieFacade recipeFac = new MovieFacade();
//        String all = recipeFac.criticFetch();
        EntityManager em = EMF.createEntityManager();
        final String final1 = replaceAll(title);
        
        
        Callable<String> fetch1 = new Callable<String>() {
        @Override
        public String call() throws Exception {
            URL url = new URL("http://exam-1187.mydemos.dk/movieInfo/" + final1);
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
    };

    Callable<String> fetch2 = new Callable<String>() {
        @Override
        public String call() throws Exception {
            URL url = new URL("http://exam-1187.mydemos.dk/moviePoster/" + final1);
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
    };

    Callable<String> fetch3 = new Callable<String>() {
        @Override
        public String call() throws Exception {
            URL url = new URL("http://exam-1187.mydemos.dk/imdbScore/" + final1);
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
    };
    
        Callable<String> fetch4 = new Callable<String>() {
        @Override
        public String call() throws Exception {
            URL url = new URL("http://exam-1187.mydemos.dk/tomatoesScore/" + final1);
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
    };
        
            Callable<String> fetch5 = new Callable<String>() {
        @Override
        public String call() throws Exception {
            URL url = new URL("http://exam-1187.mydemos.dk/metacriticScore/" + final1);
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
    };
        
        StringBuilder sb = new StringBuilder();
        Future<String> future1 = executorservice.submit(fetch1);
        Future<String> future2 = executorservice.submit(fetch2);
        Future<String> future3 = executorservice.submit(fetch3);
        Future<String> future4 = executorservice.submit(fetch4);
        Future<String> future5 = executorservice.submit(fetch5);
        //executorservice.
        String result1 = future1.get();
        String result2 = future2.get();
        String result3 = future3.get();
        String result4 = future4.get();
        String result5 = future5.get();

        sb = sb.append(result1);
        sb = sb.append(result2);
        sb = sb.append(result3);
        sb = sb.append(result4);
        sb = sb.append(result5);

        String all = sb.toString();
        executorservice.shutdown();
        Movie mov = new Movie(all);
        try {
            em.getTransaction().begin();
            em.persist(mov);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        Gson gson = new Gson();
       String test = gson.toJson(all);
       
       
        
        return test;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("movie-count/{title}")
    public String getMovieCount(@PathParam("title") String title){
        return "hi";
    }

    public static String replaceAll(String str) {
        String[] words = str.split(" ");
        StringBuilder sentence = new StringBuilder(words[0]);
 
        for (int i = 1; i < words.length; ++i) {
            sentence.append("%20");
            sentence.append(words[i]);
        }
 
        return sentence.toString();
    }
    
}


