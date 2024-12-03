package edu.miu.cs544.moe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "ON_CAMPUS")
public class OnCampus extends Course {
    private String room;
    private int capacity;

    public OnCampus() {
        super();
    }

    public OnCampus(String title, Date startDate, String professorName, String room, int capacity) {
        super(title, startDate, professorName);
        this.room = room;
        this.capacity = capacity;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "OnCampus{" +
                "room='" + room + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
