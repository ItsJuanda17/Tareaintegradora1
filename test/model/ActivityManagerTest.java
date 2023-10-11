package model;

import exception.QueueException;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class ActivityManagerTest {

    private ActivitiesManager activitiesManager;

    public void setUp1(){
        activitiesManager = new ActivitiesManager();
    }

    public void setUp2(){
        activitiesManager = new ActivitiesManager();
        try {
            Calendar taskDeadLine = Calendar.getInstance();
            taskDeadLine.set(Calendar.YEAR, 2023);
            taskDeadLine.set(Calendar.MONTH, Calendar.OCTOBER);
            taskDeadLine.set(Calendar.DAY_OF_MONTH, 8);

            Calendar reminderDeadLine = Calendar.getInstance();
            taskDeadLine.set(Calendar.YEAR, 2023);
            taskDeadLine.set(Calendar.MONTH, Calendar.DECEMBER);
            taskDeadLine.set(Calendar.DAY_OF_MONTH, 24);

            activitiesManager.addActivity(new Activity("prior", "description", taskDeadLine, true, ActivityType.TASK));
            activitiesManager.addActivity(new Activity("NonPrior", "description", reminderDeadLine, false, ActivityType.REMINDER));
        } catch (QueueException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }

    @Test
    public void testAddPriorActivityEmpty() throws QueueException {
        // setup
        setUp1();
        boolean exceptionThrown = false;
        Activity activity = null;

        // act
        try {
            Calendar taskDeadLine = Calendar.getInstance();
            taskDeadLine.set(Calendar.YEAR, 2023);
            taskDeadLine.set(Calendar.MONTH, Calendar.SEPTEMBER);
            taskDeadLine.set(Calendar.DAY_OF_MONTH, 1);
            activity = new Activity("task", "description", taskDeadLine, true, ActivityType.TASK);
            activitiesManager.addActivity(activity);

        } catch (QueueException e) {
            exceptionThrown = true;
        }

        // assert
        assertFalse(exceptionThrown);
        assertTrue(activitiesManager.getActivities().contains("task"));
        assertEquals(activity, activitiesManager.getPriorActivities().extractRoot());
    }

    @Test
    public void testAddPriorActivityNotEmpty() throws QueueException {
        // setup
        setUp2();
        boolean exceptionThrown = false;
        Activity activity = null;

        // act
        try {
            Calendar taskDeadLine = Calendar.getInstance();
            taskDeadLine.set(Calendar.YEAR, 2023);
            taskDeadLine.set(Calendar.MONTH, Calendar.SEPTEMBER);
            taskDeadLine.set(Calendar.DAY_OF_MONTH, 1);
            activity = new Activity("task", "description", taskDeadLine, true, ActivityType.TASK);
            activitiesManager.addActivity(activity);

        } catch (QueueException e) {
            exceptionThrown = true;
        }

        // assert
        assertFalse(exceptionThrown);
        assertTrue(activitiesManager.getActivities().contains("task"));
        assertEquals(activity, activitiesManager.getPriorActivities().extractRoot());
    }

    @Test
    public void testAddNonPriorActivityEmpty(){
        // setup
        setUp1();
        boolean exceptionThrown = false;
        Activity activity = null;
        Activity front = null;

        // act
        try {
            Calendar reminderDeadLine = Calendar.getInstance();
            reminderDeadLine.set(Calendar.YEAR, 2023);
            reminderDeadLine.set(Calendar.MONTH, Calendar.DECEMBER);
            reminderDeadLine.set(Calendar.DAY_OF_MONTH, 24);
            activity = new Activity("reminder", "description", reminderDeadLine, false, ActivityType.REMINDER);
            activitiesManager.addActivity(activity);
            front = activitiesManager.getNonPriorActivities().front();
        } catch (QueueException e) {
            exceptionThrown = true;
        }

        // assert
        assertFalse(exceptionThrown);
        assertTrue(activitiesManager.getActivities().contains("reminder"));
        assertEquals(activity, front);
    }

    @Test
    public void testAddNonPriorActivityNotEmpty(){
        // setup
        setUp2();
        boolean exceptionThrown = false;
        Activity activity = null;
        Activity back = null;

        // act
        try {
            Calendar reminderDeadLine = Calendar.getInstance();
            reminderDeadLine.set(Calendar.YEAR, 2023);
            reminderDeadLine.set(Calendar.MONTH, Calendar.DECEMBER);
            reminderDeadLine.set(Calendar.DAY_OF_MONTH, 24);
            activity = new Activity("reminder", "description", reminderDeadLine, false, ActivityType.REMINDER);
            activitiesManager.addActivity(activity);
            activitiesManager.getNonPriorActivities().dequeue();
            back = activitiesManager.getNonPriorActivities().dequeue();
        } catch (QueueException e) {
            exceptionThrown = true;
        }

        // assert
        assertFalse(exceptionThrown);
        assertTrue(activitiesManager.getActivities().contains("reminder"));
        assertEquals(activity, back);
    }

    @Test
    public void testGetActivityNotExist(){
        // setup
        setUp1();

        // act
        Activity activity = activitiesManager.getActivity("task");

        // assert
        assertNull(activity);
    }

    @Test
    public void testGetActivityExist(){
        // setup
        setUp2();

        // act
        Activity activity = activitiesManager.getActivity("prior");

        // assert
        assertNotNull(activity);
        assertEquals("prior", activity.getTitle());
    }

    @Test
    public void testContainsActivityNotExist(){
        // setup
        setUp1();

        // act
        boolean contains = activitiesManager.containsActivity("task");

        // assert
        assertFalse(contains);
    }

    @Test
    public void testContainsActivityExist(){
        // setup
        setUp2();

        // act
        boolean contains = activitiesManager.containsActivity("prior");

        // assert
        assertTrue(contains);
    }


    @Test
    public void testViewActivitiesByDeadlineNonEmpty() throws QueueException {
        // setup
        setUp2();

        ActivitiesManager activitiesManager = new ActivitiesManager();
        Calendar taskDeadLine = Calendar.getInstance();
        taskDeadLine.set(Calendar.YEAR, 2023);
        taskDeadLine.set(Calendar.MONTH, Calendar.SEPTEMBER);
        taskDeadLine.set(Calendar.DAY_OF_MONTH, 1);
        Activity task = new Activity("task", "description", taskDeadLine, true, ActivityType.TASK);
        activitiesManager.addActivity(task);


        String result = activitiesManager.toString();


        assertTrue(result.contains("Title: task"));
    }

    @Test
    public void testModifyActivityFound() throws QueueException {
        setUp2();

        Calendar originalDeadline = Calendar.getInstance();
        originalDeadline.set(Calendar.YEAR, 2023);
        originalDeadline.set(Calendar.MONTH, Calendar.SEPTEMBER);
        originalDeadline.set(Calendar.DAY_OF_MONTH, 1);

        Activity originalActivity = new Activity("task", "description", originalDeadline, true, ActivityType.TASK);
        activitiesManager.addActivity(originalActivity);

        Calendar newDeadline = Calendar.getInstance();
        newDeadline.set(Calendar.YEAR, 2023);
        newDeadline.set(Calendar.MONTH, Calendar.OCTOBER);
        newDeadline.set(Calendar.DAY_OF_MONTH, 15);

        Activity updatedActivity = new Activity("task", "new description", newDeadline, false, ActivityType.REMINDER);
        boolean exceptionThrown = false;

        try{
            activitiesManager.modifyActivity(updatedActivity);
        } catch (QueueException e){
            exceptionThrown = true;
        }

        Activity modifiedActivity = activitiesManager.getActivity("task");
        assertNotNull(modifiedActivity);
        assertFalse(exceptionThrown);
        assertEquals("new description", modifiedActivity.getDescription());
        assertEquals(newDeadline, modifiedActivity.getDeadLine());
        assertFalse(modifiedActivity.getPriority());
        assertEquals(ActivityType.REMINDER, modifiedActivity.getType());
    }

    @Test
    public void testModifyActivityNotFound(){
        setUp2();

        Calendar newDeadline = Calendar.getInstance();
        newDeadline.set(Calendar.YEAR, 2023);
        newDeadline.set(Calendar.MONTH, Calendar.OCTOBER);
        newDeadline.set(Calendar.DAY_OF_MONTH, 15);

        Activity updatedActivity = new Activity("task", "new description", newDeadline, false, ActivityType.REMINDER);
        boolean exceptionThrown = false;
        try {
            activitiesManager.modifyActivity(updatedActivity);
        } catch (QueueException e) {
            exceptionThrown = true;
        }

        assertTrue(exceptionThrown);
    }


}
