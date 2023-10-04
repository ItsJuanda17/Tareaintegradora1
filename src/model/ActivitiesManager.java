package model;

import collections.dataStructures.HashTable;
import collections.dataStructures.PriorityQueue;
import collections.dataStructures.Queue;
import exception.QueueException;

public class ActivitiesManager {

    private HashTable<String, Activity> activities;
    private PriorityQueue<Activity> PriorActivities;
    private Queue<Activity> NonPriorActivities;

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

}
