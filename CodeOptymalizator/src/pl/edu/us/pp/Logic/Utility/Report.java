package pl.edu.us.pp.Logic.Utility;

import java.util.List;

/**
 * Created by Adrian on 2016-05-19.
 */
public class Report {
    private boolean status;
    private List<Message> messages;

    public Report(boolean status, List<Message> messages) {
        this.status = status;
        this.messages = messages;
    }

    public boolean isStatus() {
        return status;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
