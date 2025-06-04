package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
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
    @JsonProperty("semester")
    private String semester;
    public Activity() {}

    public Activity(String title, String name, String semester, boolean status, int score, String date, String location) {
        this.title = title;
        this.name = name;
        this.semester = semester;
        this.status = status;
        this.score = score;
        this.date = date;
        this.location = location;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Activity activity = (Activity) o;

        return name != null ? name.equals(activity.name) : activity.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

//	public double getHocTap() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	public double getKyLuat() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	public double getXaHoi() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	public double getYThuc() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
}