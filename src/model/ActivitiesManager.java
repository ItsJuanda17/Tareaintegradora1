package model;

import collections.dataStructures.HashTable;
import collections.dataStructures.PriorityQueue;
import collections.dataStructures.Queue;
import exception.QueueException;

public class ActivitiesManager{ // cambiar ActivitieManager

    private HashTable<String, Activity> activities;
    private PriorityQueue<Activity> PriorActivities;
    private Queue<Activity> NonPriorActivities;

    public ActivitiesManager(){
        this.activities = new HashTable<>();
        this.PriorActivities = new PriorityQueue<>();
        this.NonPriorActivities = new Queue<>();
    }

    public void addActivity(Activity activity) throws QueueException {
        activities.put(activity.getTitle(), activity);
        if(activity.getPriority()) {
            PriorActivities.insert(activity);
        }else{
            NonPriorActivities.enqueue(activity);
        }
    }

    public void removeActivity(Activity activity){

    }

    public Activity getActivity(String title){
        return activities.get(title);
    }

    public boolean containsActivity(String title){
        return activities.contains(title);
    }


}
