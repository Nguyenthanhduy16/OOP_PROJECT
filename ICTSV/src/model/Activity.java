package model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class Activity {
    @JsonProperty("title")
    private String title;

    @JsonProperty("name")
    private String name;

    @JsonProperty("status")
    private boolean status;

    @JsonProperty("score")
    private int score;
    
    @JsonProperty("date")
    private String date;
    
    @JsonProperty("location")
    private String location;
 
    public Activity() {} // Contructor cần thiết cho Json đọc dữ liệu không được xóa

    public Activity(String title, String name, boolean status, int score, String date, String location) {
        this.title = title;
        this.name = name;
        this.status = status;
        this.score = score;
        this.date = date;
        this.location = location;
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
    
    public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Activity)) return false;
        Activity activity = (Activity) o;
        return status == activity.status &&
               score == activity.score &&
               Objects.equals(title, activity.title) &&
               Objects.equals(name, activity.name) &&
               Objects.equals(date, activity.date) &&
               Objects.equals(location, activity.location);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(title, name, status, score, date, location);
    }
    
    @Override 
    public String toString() {
        return (this.getTitle() + "\n"
                + this.getName() + "\n"
                + this.isStatus() + "\n"
                + "Điểm: " + this.getScore() + "\n"
                + "Ngày: " + this.getDate() + "\n"
                + "Địa điểm: " + this.getLocation());
    }
}