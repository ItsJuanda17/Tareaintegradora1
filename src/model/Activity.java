package model;


import java.util.Calendar;

import java.text.SimpleDateFormat;
import java.util.Objects;

public class Activity implements Comparable<Activity> {

    private String title;
    private String description;
    private Calendar deadLine;
    private Boolean priority;
    private ActivityType type;

    public Activity(String title, String description, Calendar deadLine, Boolean priority , ActivityType type) {
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;
        this.priority = priority;
        this.type = type;
    }

    public Activity(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public Calendar getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Calendar deadLine) {
        this.deadLine = deadLine;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getPriority() {
        return priority;
    }

    public void setPriority(Boolean priority) {
        this.priority = priority;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    @Override
    public int compareTo(Activity other) {
        if(this.deadLine.compareTo(other.deadLine) <= 0){
            return 1;
        }else{
            return -1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return priority == activity.priority &&
                Objects.equals(title, activity.title) &&
                Objects.equals(description, activity.description) &&
                Objects.equals(deadLine, activity.deadLine) &&
                type == activity.type;
    }

    @Override
    public String toString() {
        String typeStr = type == ActivityType.TASK ? "Task" : "Reminder";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        return "Title: " + title + "\n" +
                "Description: " + description + "\n" +
                "Deadline: " + dateFormat.format(deadLine.getTime()) + "\n" +
                "Type: " + typeStr;
    }

    public void copy(Activity other) {
        other.title = this.title;
        other.description = this.description;
        other.deadLine = (Calendar) this.deadLine.clone();
        other.priority = this.priority;
        other.type = this.type;
    }


}