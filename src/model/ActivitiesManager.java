package model;

import java.util.Calendar;
import java.util.Comparator;

import collections.dataStructures.HashTable;
import collections.dataStructures.PriorityQueue;
import collections.dataStructures.Queue;
import exception.QueueException;

public class ActivitiesManager {

    private HashTable<String, Activity> activities;
    private PriorityQueue<Activity> PriorActivities;
    private Queue<Activity> NonPriorActivities;

    public HashTable<String, Activity> getActivities() {
        return activities;
    }

    public PriorityQueue<Activity> getPriorActivities() {
        return PriorActivities;
    }

    public Queue<Activity> getNonPriorActivities() {
        return NonPriorActivities;
    }

    public ActivitiesManager() {
        this.activities = new HashTable<>();
        this.PriorActivities = new PriorityQueue<>();
        this.NonPriorActivities = new Queue<>();
    }

    public void addActivity(Activity activity) throws QueueException {
        activities.put(activity.getTitle(), activity);
        if (activity.getPriority()) {
            PriorActivities.insert(activity);
        } else {
            NonPriorActivities.enqueue(activity);
        }
    }

    public void removeActivity(Activity activity) throws QueueException {

        if (activity == null) {
            return;
        }
        String title = activity.getTitle();

        if (!activities.contains(title)) {
            return;
        }
        activities.remove(title);
        if (activity.getPriority()) {
            PriorActivities.extractRoot();
        } else {

            NonPriorActivities.dequeue();
        }
    }

    public Activity getActivity(String title) {
        return activities.get(title);
    }

    public boolean containsActivity(String title) {
        return activities.contains(title);
    }

    public String modifyActivity(Activity updatedActivity) throws QueueException {
        String title = updatedActivity.getTitle();
        if (activities.contains(title)) {
            Activity existingActivity = activities.get(title);
            existingActivity.setDescription(updatedActivity.getDescription());
            existingActivity.setDeadLine(updatedActivity.getDeadLine());
            existingActivity.setPriority(updatedActivity.getPriority());
            existingActivity.setType(updatedActivity.getType());

            if (existingActivity.getPriority()) {
                PriorActivities.insert(existingActivity);
            } else {
                NonPriorActivities.enqueue(existingActivity);
            }

            return "The activity was modified successfully";
        } else {
            return "Activity not found";
        }
    }




    public String viewActivitiesByDeadline() throws QueueException {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Activity> sortedPriorityQueue = new PriorityQueue<>();


        while (!PriorActivities.isEmpty()) {
            sortedPriorityQueue.insert(PriorActivities.extractRoot());
        }

        while (!NonPriorActivities.isEmpty()) {
            sortedPriorityQueue.insert(NonPriorActivities.dequeue());
        }
        sb.append("Activities by Deadline:\n");

        while (!sortedPriorityQueue.isEmpty()) {
            Activity activity = sortedPriorityQueue.extractRoot();
            sb.append(activity.toString()).append("\n");
        }
        return sb.toString();
    }




}
