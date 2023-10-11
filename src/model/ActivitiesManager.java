package model;

import collections.dataStructures.HashTable;
import collections.dataStructures.PriorityQueue;
import collections.dataStructures.Queue;
import exception.QueueException;

public class ActivitiesManager {

    private HashTable<String, Activity> activities;
    private PriorityQueue<Activity> priorActivities;
    private Queue<Activity> nonPriorActivities;

    public HashTable<String, Activity> getActivities() {
        return activities;
    }

    public PriorityQueue<Activity> getPriorActivities() {
        return priorActivities;
    }

    public Queue<Activity> getNonPriorActivities() {
        return nonPriorActivities;
    }

    public ActivitiesManager() {
        this.activities = new HashTable<>();
        this.priorActivities = new PriorityQueue<>();
        this.nonPriorActivities = new Queue<>();
    }

    public void addActivity(Activity activity) throws QueueException {
        activities.put(activity.getTitle(), activity);
        if (activity.getPriority()) {
            priorActivities.insert(activity);
        } else {
            nonPriorActivities.enqueue(activity);
        }
    }

    public void removeActivity(Activity activity) throws QueueException {
        if (activity == null) {
            throw new QueueException("The activity does not exist");
        }
        String title = activity.getTitle();
        activities.remove(title);

        PriorityQueue<Activity> tempPriorActivities = new PriorityQueue<>();
        while (!priorActivities.isEmpty()) {
            Activity current = priorActivities.extractRoot();
            if (!current.getTitle().equals(title)) {
                tempPriorActivities.insert(current);
            }
        }
        priorActivities = tempPriorActivities;

        Queue<Activity> tempNonPriorActivities = new Queue<>();
        while (!nonPriorActivities.isEmpty()) {
            Activity current = nonPriorActivities.dequeue();
            if (!current.getTitle().equals(title)) {
                tempNonPriorActivities.enqueue(current);
            }
        }
        nonPriorActivities = tempNonPriorActivities;
    }


    public Activity getActivity(String title) {
        return activities.get(title);
    }

    public boolean containsActivity(String title) {
        return activities.contains(title);
    }

    public void modifyActivity(Activity updatedActivity) throws QueueException {
        String title = updatedActivity.getTitle();
        Activity existingActivity = activities.get(title);
        removeActivity(existingActivity);
        addActivity(updatedActivity);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Prior Activities:\n");
        sb.append(priorActivities).append("\n");
        sb.append("Non Prior Activities:\n");
        sb.append(nonPriorActivities).append("\n");
        return sb.toString();
    }



}
