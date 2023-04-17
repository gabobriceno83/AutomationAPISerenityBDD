package com.pe.screenplaybdd.endpoints;

public enum UserEndpoints {
    Create_User("/user"),
    Obtain_User("/user/{username}"),
    Update_User("/user/{username}"),
    Delete_User("/user/{username}");

    private final String path;


   //Constructor
    UserEndpoints(String path) {
        this.path = path;
    }


    //Getter
    public String getPath() {
        return path;
    }
}
