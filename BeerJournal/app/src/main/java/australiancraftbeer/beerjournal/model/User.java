package australiancraftbeer.beerjournal.model;

import java.util.Map;

/**
 * Created by andbreen on 29/05/2016.
 */

public class User {

    static User singleton;

    public static void initialise(User user){
        singleton = user;
    }

    public static User currentUser(){
        return singleton;
    }

    String id, name, email;
    Map<String, String> journals;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public Map<String, String> getJournals() {
        return journals;
    }

    public void setJournals(Map<String, String> journals) {
        this.journals = journals;
    }
}
