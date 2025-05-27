package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Activity {
    @JsonProperty("title")
    private String title;

    @JsonProperty("name")
    private String name;

    @JsonProperty("status")
    private boolean status;

    @JsonProperty("score")
    private int score;
 
    public Activity() {}

    public Activity(String title, String name, boolean status, int score) {
        this.title = title;
        this.name = name;
        this.status = status;
        this.score = score;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    
    @Override 
    public String toString() {
        return (this.getTitle() + "\n"
                + this.getName() + "\n"
                + this.isStatus() + "\n"
                + "Điểm: " + this.getScore());
    }

    public void toPrint() {
        System.out.println(this.getName() + " " + this.getTitle() + " " + this.getScore());
    }
}