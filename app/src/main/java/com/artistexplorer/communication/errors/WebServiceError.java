package com.artistexplorer.communication.errors;

/**
 * Created by Marian Lungu on 12/07/15.
 */
public class WebServiceError {

    private String message;
    private String type;

    public WebServiceError(String message){
        this(message, null);
    }

    public WebServiceError(String message, String type){
        this.message = message;
        this.type = type;
    }

}
