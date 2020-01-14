/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Bruger
 */
public class MovieFacade {
    
     private static EntityManagerFactory emf;
    private static MovieFacade instance;
    int threads = 8;
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    ExecutorService executorservice = Executors.newFixedThreadPool(threads);
    
    public MovieFacade(){}
    
    /**
     * 
     * @param _emf
     * @return the instance of this facade.
     */
    public static MovieFacade getMovieFacade (EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }
    
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
     //methods
    Callable<String> fetch1 = new Callable<String>() {
        @Override
        public String call() throws Exception {
            URL url = new URL("http://exam-1187.mydemos.dk/movieInfo/Hair");
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
            URL url = new URL("http://exam-1187.mydemos.dk/moviePoster/Hair");
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
            URL url = new URL("http://exam-1187.mydemos.dk/imdbScore/Hair");
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
            URL url = new URL("http://exam-1187.mydemos.dk/tomatoesScore/Hair");
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
            URL url = new URL("http://exam-1187.mydemos.dk/metacriticScore/Hair");
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
    public String allFetch() throws InterruptedException, ExecutionException {
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
        return all;
    }

    public String criticFetch() throws InterruptedException, ExecutionException {
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
        return all;
    }
    
}
