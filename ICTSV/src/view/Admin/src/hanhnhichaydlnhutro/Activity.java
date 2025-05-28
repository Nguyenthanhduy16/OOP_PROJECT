package hanhnhichaydlnhutro;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class representing an Activity with name, category, duration and a selected flag.
 */
public class Activity {
    private final StringProperty name = new SimpleStringProperty(this, "name");
    private final StringProperty category = new SimpleStringProperty(this, "category");
    private final IntegerProperty duration = new SimpleIntegerProperty(this, "duration");
    private final BooleanProperty selected = new SimpleBooleanProperty(this, "selected", false);

    public Activity() { }

    public Activity(String name, String category, int duration) {
        this.name.set(name);
        this.category.set(category);
        this.duration.set(duration);
    }

    public StringProperty nameProperty()    { return name; }
    public String getName()                 { return name.get(); }
    public void setName(String name)        { this.name.set(name); }

    public StringProperty categoryProperty(){ return category; }
    public String getCategory()             { return category.get(); }
    public void setCategory(String category){ this.category.set(category); }

    public IntegerProperty durationProperty(){ return duration; }
    public int getDuration()                { return duration.get(); }
    public void setDuration(int duration)   { this.duration.set(duration); }

    public BooleanProperty selectedProperty(){ return selected; }
    public boolean isSelected()              { return selected.get(); }
    public void setSelected(boolean selected){ this.selected.set(selected); }
}
