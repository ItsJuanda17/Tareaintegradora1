package model;


import java.util.Calendar;

public class Activities{

    private String title;
    private String description;
    private Calendar deadLine;

    private Boolean priority;

    private ActivitieType type;



    public Activities(String title, String description, Calendar deadLine, Boolean priority , ActivitieType type) {
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

    public ActivitieType getType() {
        return type;
    }

    public void setType(ActivitieType type) {
        this.type = type;
    }
}