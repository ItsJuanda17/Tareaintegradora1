package model;

import collections.dataStructures.Stack;
import exception.QueueException;
import exception.StackException;

import java.util.Calendar;

public class Controller {

    private Stack<Actions> stack;
    private ActivitiesManager activitiesManager;
    private Activity lastActivity;
    private Activity originalActivity;

    public Controller() {
        this.stack = new Stack<>();
        this.activitiesManager = new ActivitiesManager();
    }

    public String registerActivity(String title, String description, Calendar deadLine, int priorityOption, int typeOption) {
        String msg = "";
        Activity newActivity = new Activity(title, description, deadLine, (priorityOption == 1), (typeOption == 1) ? ActivityType.TASK : ActivityType.REMINDER);
        try {
            if(activitiesManager.containsActivity(title)){
                msg = "The activity already exists";
            }else {
                activitiesManager.addActivity(newActivity);
                stack.push(Actions.ADD);
                lastActivity = newActivity;
                msg = "The activity was registered successfully";
            }
        } catch (QueueException | StackException e) {
            msg = e.getMessage();
        }

        return msg;
    }

    public String undoLasAction(){
        String msg = "";
        try {
            Actions lastAction = stack.pop();
            switch (lastAction){
                case ADD:
                    activitiesManager.removeActivity(lastActivity);
                    msg = "The last action was undone successfully";
                    break;
                case REMOVE:
                    activitiesManager.addActivity(lastActivity);
                    msg = "The last action was undone successfully";
                    break;
                case MODIFY:
                    lastActivity = originalActivity;
                    msg = "The last action was undone successfully";
                    break;
            }
        } catch (QueueException | StackException e) {
            msg = "There is no action to undo";
        }
        return msg;
    }



}