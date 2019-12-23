package model;

import java.util.Date;

public class TopicThread {
    private long id;

    private String title;
    private String message;
    private String username;
    private Date date;
    private FeedbackStatus status;
    private UserRole userRole;

    public TopicThread(String title, String message, String username, Date date, FeedbackStatus status, UserRole userRole) {
        this.title = title;
        this.message = message;
        this.username = username;
        this.date = date;
        this.status = status;
        this.userRole = userRole;
    }

    public String getTitle() {
        return title;
    }

    public String getUsername() {
        return username;
    }
}
