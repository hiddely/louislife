package com.louislife.model;

/**
 * Created by hidde on 23/01/15.
 */
public class TeamNotFoundException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = -1089850085063859128L;

    public TeamNotFoundException() {
        super();
    }

    public TeamNotFoundException(String message) {
        super(message);
    }
}
