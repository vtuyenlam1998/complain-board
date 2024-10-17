//package com.example.complainboard.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//import javax.annotation.PostConstruct;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.Random;
//import java.util.concurrent.TimeUnit;
//
//@RestController
//@RequestMapping("/favourites")
//@Slf4j
//public class ApiServlet {
//
//    @Value("${toggle.service.delay}")
//    private Integer delayTime;
//    @Value("${redis.host}")
//    private String redisHost;
//    @Value("${redis.port}")
//    private Integer redisPort;
//    private JedisPool r;
//
//    @PostConstruct
//    public void init() {
//        JedisPoolConfig poolConfig = new JedisPoolConfig();
//        poolConfig.setMaxWaitMillis(3000);
//        poolConfig.setMaxTotal(100);
//        r = new JedisPool(poolConfig, redisHost, redisPort);
//    }
//
//
//    @GetMapping
//    public String helloWorld(@RequestParam(required = false) String user_id) throws InterruptedException {
//        if (user_id == null) {
//            log.info("Main request successful");
//            return "Hello World!";
//        } else {
//            return getUserFavourites(user_id);
//        }
//    }
//
//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public String handlePost(@RequestParam(required = false) String user_id, @RequestParam(required = false) String requestBody) throws Exception {
//        handleDelay();
//        log.info("Adding or removing favorites");
//        JSONObject json = new JSONObject(requestBody);
//        String movieId = Integer.toString(json.getInt("id"));
//        log.info("Adding or removing favorites for user " + user_id + ", movie " + movieId);
//        Jedis jedis = r.getResource();
//        try {
//            Long redisReponse = jedis.srem(user_id, movieId);
//            if (redisReponse == 0) {
//                jedis.sadd(user_id, movieId);
//            }
//        } catch (Exception e) {
//            log.error("Error adding or removing favorites for user {}, movie {}", user_id, movieId);
//        } finally {
//            jedis.close();
//        }
//        String favorites = getUserFavourites(user_id);
//        handleCanary();
//        return favorites;
//    }
//
//    private String getUserFavourites(String user_id) {
//        Jedis jedis = r.getResource();
//        String returnedFavorites = "";
//        try {
//            handleDelay();
//            log.info("Getting favorites for user " + user_id);
//            List<String> favorites = new ArrayList<>(jedis.smembers(user_id));
//            JSONObject favoritesJson = new JSONObject();
//            favoritesJson.put("favorites", favorites);
//            log.info("User {} favorites {}", user_id, favorites);
//            returnedFavorites = favoritesJson.toString();
//        } catch (Exception e) {
//
//        } finally {
//            jedis.close();
//        }
//        log.info("User {} favorites {}", user_id, returnedFavorites);
//        return returnedFavorites;
//    }
//
//    private void handleDelay() throws InterruptedException {
//        if (delayTime > 0) {
//            Random random = new Random();
//            double randomGaussDelay = Math.max(0, random.nextGaussian() * (delayTime / 1000 / 10) + (delayTime / 1000));
//            TimeUnit.MILLISECONDS.sleep((long) randomGaussDelay);
//        }
//    }
//    private void handleCanary() throws Exception {
//        Integer sleepTime = Integer.parseInt(Objects.requireNonNullElse(System.getenv("TOGGLE_CANARY_DELAY"), "0"));
//        Random random = new Random();
//        if (sleepTime > 0 && random.nextDouble() < 0.5) {
//            double randomGaussDelay = Math.max(0, random.nextGaussian() * (delayTime / 1000 / 10) + (delayTime / 1000));
//            TimeUnit.MILLISECONDS.sleep((long) randomGaussDelay);
//            log.info("Canary enabled");
//
//            Double toggleCanaryFailure = Double.parseDouble(Objects.requireNonNullElse(System.getenv("TOGGLE_CANARY_DELAY"),"0"));
//            if(random.nextDouble() < toggleCanaryFailure) {
//                log.error("Something went wrong");
//                throw new Exception("Something went wrong");
//            }
//        }
//    }
//}
