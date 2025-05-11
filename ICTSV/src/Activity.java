public class Activity {
    private String title;
    private String name;
    private boolean status;
    private int score;
    
    public Activity(String title, String name, boolean status, int score) {
        this.title = title;
        this.name = name;
        this.status = status;
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public boolean isStatus() {
        return status;
    }

    public int getScore() {
        return score;
    }

    public void display() {
        System.out.println(this.getTitle() + "\n"
                + this.getName() + "\n"
                + this.isStatus() + "\n"
                + "Điểm: " + this.getScore());
    }

}