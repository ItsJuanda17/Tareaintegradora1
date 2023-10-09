package model;

import collections.dataStructures.Stack;
import exception.QueueException;
import exception.StackException;

import java.util.Calendar;

public class Controller {

    private Stack<Actions> actions;
    private ActivitiesManager activitiesManager;
    private Stack<Activity> lastActions;

    public Controller() {
        this.actions = new Stack<>();
        this.activitiesManager = new ActivitiesManager();
        this.lastActions = new Stack<>();
    }

    public ActivitiesManager getActivitiesManager() {
        return activitiesManager;
    }

    public String registerActivity(String title, String description, Calendar deadLine, int priorityOption, int typeOption) {
        String msg;
        try {
            if (activitiesManager.containsActivity(title)) {
                msg = "The activity already exists";
            } else {
                Activity newActivity = new Activity(title, description, deadLine, (priorityOption == 1),
                        (typeOption == 1) ? ActivityType.TASK : ActivityType.REMINDER);
                activitiesManager.addActivity(newActivity);
                saveAction(Actions.ADD, newActivity);
                msg = "The activity was registered successfully";
            }
        } catch (QueueException | StackException e) {
            msg = e.getMessage();
        }

        return msg;
    }

    public String removeActivity(String title) throws StackException, QueueException {
        String msg;
        Activity removedActivity = activitiesManager.getActivity(title);

        if (removedActivity != null) {
            activitiesManager.removeActivity(removedActivity);
            saveAction(Actions.REMOVE, removedActivity);

            msg = "The activity was removed successfully";
        } else {
            msg = "Activity not found";
        }

        return msg;
    }

    public String modifyActivity(String title, String description, Calendar deadLine, int priorityOption, int typeOption) throws QueueException, StackException {
        String msg;
        Activity updateActivity = activitiesManager.getActivity(title);

        if (updateActivity != null) {
            saveAction(Actions.MODIFY, updateActivity);
            updateActivity.setDescription(description);
            updateActivity.setDeadLine(deadLine);
            updateActivity.setPriority(priorityOption == 1);
            updateActivity.setType(typeOption == 1 ? ActivityType.TASK : ActivityType.REMINDER);

            msg = activitiesManager.modifyActivity(updateActivity);
        } else {
            msg = "Activity not found";
        }

        return msg;
    }


    public String viewActivitiesByDeadline() throws QueueException {
        return activitiesManager.toString();
    }

    public String undoLastAction(){
        String msg = "";
        try {
            Actions lastAction = actions.pop();
            Activity lastActivity = lastActions.pop();
            switch (lastAction) {
                case ADD:
                    activitiesManager.removeActivity(lastActivity);
                    msg = "The add action was undone successfully";
                    break;
                case REMOVE:
                    activitiesManager.addActivity(lastActivity);
                    msg = "The remove action was undone successfully";
                    break;
                case MODIFY:
                    activitiesManager.modifyActivity(lastActivity);
                    msg = "The modify action was undone successfully";
                    break;
            }
        } catch (QueueException | StackException e) {
            msg = "There is no action to undo";
        }
        return msg;
    }

    public void saveAction(Actions action, Activity activity) throws StackException {
        actions.push(action);
        Activity copy = new Activity();
        activity.copy(copy);
        lastActions.push(copy);
    }

}