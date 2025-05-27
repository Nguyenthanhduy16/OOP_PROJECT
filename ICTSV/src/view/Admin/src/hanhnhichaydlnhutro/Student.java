package hanhnhichaydlnhutro;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class representing a Student with an ID, name and a selected flag.
 */
public class Student {
    private final StringProperty id = new SimpleStringProperty(this, "id");
    private final StringProperty name = new SimpleStringProperty(this, "name");
    private final BooleanProperty selected = new SimpleBooleanProperty(this, "selected", false);

    public Student() {
    }

    public Student(String id, String name) {
        this.id.set(id);
        this.name.set(name);
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public BooleanProperty selectedProperty() {
        return selected;
    }

    public boolean isSelected() {
        return selected.get();
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }
}
