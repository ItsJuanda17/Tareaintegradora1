package model;


import java.util.Calendar;

import java.text.SimpleDateFormat;

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
    public String toString() {
        String priorityStr = priority ? "Priority" : "Non-priority";
        String typeStr = type == ActivityType.TASK ? "Task" : "Reminder";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        return "Title: " + title + "\n" +
                "Description: " + description + "\n" +
                "Deadline: " + dateFormat.format(deadLine.getTime()) + "\n" +
                "Priority: " + priorityStr + "\n" +
                "Type: " + typeStr;
    }


}