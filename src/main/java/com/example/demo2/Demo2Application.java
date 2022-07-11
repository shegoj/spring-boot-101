package com.example.demo2;


import com.example.demo2.model.NodeItem;
import com.example.demo2.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import de.flapdoodle.embed.mongo.*;
import de.flapdoodle.embed.mongo.config.*;
import org.springframework.data.mongodb.core.*;
import com.mongodb.client.MongoClients;
import de.flapdoodle.embed.mongo.distribution.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;


@SpringBootApplication
@EnableMongoRepositories
public class Demo2Application implements CommandLineRunner {

    @Autowired
    NodeRepository nodeRepo;

    @Value("${csvfile}")
    private String csvfile;


    private static final String CONNECTION_STRING = "mongodb://%s:%d";

    private MongodExecutable mongodExecutable;
    private MongoTemplate mongoTemplate;

    private static final String COMMA_DELIMITER =",";

    public static void main(String[] args) {
        SpringApplication.run(Demo2Application.class, args);
    }

    public void run(String... args)  throws Exception{

        String ip = "localhost";
        int port = 27017;

        ImmutableMongodConfig mongodConfig = MongodConfig
                .builder()
                .version(Version.Main.PRODUCTION)
                .build();

        MongodStarter starter = MongodStarter.getDefaultInstance();
        mongodExecutable = starter.prepare(mongodConfig);
        mongodExecutable.start();
        mongoTemplate = new MongoTemplate(MongoClients.create(String.format(CONNECTION_STRING, ip, port)), "test");

        nodeRepo.deleteAll();

        System.out.println("-------------CREATE Node ITEMS-------------------------------\n");

        creatNodeItems();

        loadCSVToMongo ();

    }

    // CRUD operations

    //CREATE
    void creatNodeItems() {
        System.out.println("Data creation started...");

        nodeRepo.save( new NodeItem("gbld10033031", "GB"));
        nodeRepo.save( new NodeItem("chld10033071", "CH"));
        nodeRepo.save( new NodeItem("gbld1003303", "US"));
        nodeRepo.save( new NodeItem("chld1003307", "SG"));

        System.out.println("Data creation complete...");
    }

    void loadCSVToMongo () {
        List<List<String>> records ;
        try (BufferedReader br = new BufferedReader(new FileReader(csvfile))) {
            String record ;
            while ((record =  br.readLine()) != null ) {
                String [] mongoRecords =  record.split(COMMA_DELIMITER);
                System.out.println ("yes !" + mongoRecords [0] + ", " + mongoRecords [1]);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }


}
