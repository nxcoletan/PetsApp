package sg.edu.rp.c346.id20025835.petsapp;

import java.io.Serializable;

import androidx.annotation.NonNull;

public class Pet implements Serializable {

	private int id;
	private String name;
    private String type;
	private String description;
	private int yearReleased;

    public Pet(int id, String name, String type, String description, int yearReleased) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.yearReleased = yearReleased;
    }

    public int getId() {
        return id;
    }

    public Pet setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Pet setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public Pet setType(String type) {
        this.type = type;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Pet setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getYearReleased() {
        return yearReleased;
    }

    public Pet setYearReleased(int yearReleased) {
        this.yearReleased = yearReleased;
        return this;
    }

    @NonNull
    @Override
    public String toString() {
        return name + "\n" + type + "\n" + description + " - " + yearReleased;
    }
}
