package com.example.demo2.model;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("NodeItem")
public class NodeItem {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getDataCentre() {
        return dataCentre;
    }

    public void setDataCentre(String dataCentre) {
        this.dataCentre = dataCentre;
    }

    @Id
    private String id;

    private String hostname;

    private String dataCentre;


    public NodeItem(String hostname) {
        super();
        this.setHostname(hostname);
    }

    public NodeItem() {
        // do nothing
    }

    public NodeItem (String hostname, String dataCentre ) {
        super();
        this.setHostname(hostname);
        this.setDataCentre(dataCentre);
    }

    @Override
    public String toString () {
        //Creating the ObjectMapper object
        ObjectMapper mapper = new ObjectMapper();
        //Converting the Object to JSONString
        try {
            String jsonString = mapper.writeValueAsString(this);
            return jsonString;
        }
        catch (Exception ex) {
            // do nothing
            ex.printStackTrace();
            return "";
        }
    }

}
