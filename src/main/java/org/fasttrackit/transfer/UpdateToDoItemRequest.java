package org.fasttrackit.transfer;

import java.sql.SQLException;

public class UpdateToDoItemRequest {
    private boolean done;


    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }



}