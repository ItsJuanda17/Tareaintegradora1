package model;

import exception.QueueException;
import exception.StackException;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    private Controller controller;

    public void setUp1(){
        controller = new Controller();
    }

    public void setUp2(){
        controller = new Controller();

        Calendar taskDeadLine = Calendar.getInstance();
        taskDeadLine.set(Calendar.YEAR, 2023);
        taskDeadLine.set(Calendar.MONTH, Calendar.OCTOBER);
        taskDeadLine.set(Calendar.DAY_OF_MONTH, 8);

        Calendar newDeadline = Calendar.getInstance();
        newDeadline.set(Calendar.YEAR, 2023);
        newDeadline.set(Calendar.MONTH, Calendar.SEPTEMBER);
        newDeadline.set(Calendar.DAY_OF_MONTH, 9);

        System.out.println("taskDeadLine: " + taskDeadLine.getTime());
        System.out.println("newDeadline: " + newDeadline.getTime());

        try {
            controller.registerActivity("task", "description", taskDeadLine, 1, 1);

            controller.registerActivity("task2", "description", taskDeadLine, 1, 1);
            controller.modifyActivity("task2", "description2", newDeadline, 2, 2);

            controller.removeActivity("task2");
        } catch (StackException | QueueException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testRegisterActivitySuccess(){
        // setup
        setUp1();

        // act
        String message = controller.registerActivity("task3", "description", Calendar.getInstance(), 1, 1);

        //assert
        assertEquals("The activity was registered successfully", message);
    }


    @Test
    public void testRegisterActivityAlreadyExist(){
        //setup
        setUp2();

        // act
        String message = controller.registerActivity("task", "description", Calendar.getInstance(), 1, 1);

        // assert
        assertEquals("The activity already exists", message);
    }


     @Test
    public void testRemoveActivitySuccess() throws StackException, QueueException {
        // Arrange
        controller.registerActivity("task3", "description", Calendar.getInstance(), 1, 1);

        // Act
        String message = controller.removeActivity("task3");

        // Assert
        assertEquals("The activity was removed successfully", message);
    }

    @Test
    public void testRemoveActivityNotFound() throws StackException, QueueException {
        // Act
        String message = controller.removeActivity("non-existing");

        // Assert
        assertEquals("Activity not found", message);
    }


    @Test
    public void testUndoLastActionWhenEmpty(){
        // setup
        setUp1();

        // act
        String message = controller.undoLastAction();

        // assert
        assertEquals("There is no action to undo", message);
    }

    @Test
    public void testUndoLastActionAdd(){
        // setup
        setUp1();

        controller.registerActivity("task3", "description", Calendar.getInstance(), 1, 1);
        String message = controller.undoLastAction();

        // assert
        assertEquals("The add action was undone successfully", message);
        assertFalse(controller.getActivitiesManager().containsActivity("task3"));
    }


    @Test
    public void testUndoLastActionModify(){
        // setup
        setUp1();
        boolean exceptionThrown = false;

        // act
        Calendar taskDeadLine = Calendar.getInstance();
        taskDeadLine.set(Calendar.YEAR, 2023);
        taskDeadLine.set(Calendar.MONTH, Calendar.OCTOBER);
        taskDeadLine.set(Calendar.DAY_OF_MONTH, 8);
        controller.registerActivity("task3", "description", taskDeadLine, 1, 1);
        try {
            controller.modifyActivity("task3", "description2", Calendar.getInstance(), 2, 2);
        } catch (StackException | QueueException e) {
            exceptionThrown = true;
        }
        String message = controller.undoLastAction();

        assertFalse(exceptionThrown);
        assertEquals("The modify action was undone successfully", message);
        assertEquals("description", controller.getActivitiesManager().getActivity("task3").getDescription());
        assertEquals(taskDeadLine, controller.getActivitiesManager().getActivity("task3").getDeadLine());
        assertTrue(controller.getActivitiesManager().getActivity("task3").getPriority());
        assertEquals(ActivityType.TASK, controller.getActivitiesManager().getActivity("task3").getType());

    }

    @Test
    public void testUndoLastActionRemove(){
        // setup
        setUp1();
        boolean exceptionThrown = false;

        // act
        controller.registerActivity("task3", "description", Calendar.getInstance(), 1, 1);
        try {
            controller.removeActivity("task3");
        } catch (StackException | QueueException e) {
            exceptionThrown = true;
        }
        String message = controller.undoLastAction();

        assertFalse(exceptionThrown);
        assertEquals("The remove action was undone successfully", message);
        assertTrue(controller.getActivitiesManager().containsActivity("task3"));
    }


    @Test
    public void testUndoMultipleActions(){
        // setup
        setUp2();

        // act
        String message = controller.undoLastAction();
        String message2 = controller.undoLastAction();
        String message3 = controller.undoLastAction();
        String message4 = controller.undoLastAction();
        String message5 = controller.undoLastAction();

        assertEquals("The remove action was undone successfully", message);
        assertEquals("The modify action was undone successfully", message2);
        assertEquals("The add action was undone successfully", message3);
        assertEquals("The add action was undone successfully", message4);
        assertEquals("There is no action to undo", message5);
    }

    @Test
    public void testModifyActivitySuccess() throws QueueException, StackException {

        setUp1();

        String registerResult = controller.registerActivity("task", "description", Calendar.getInstance(), 1, 1);
        assertEquals("The activity was registered successfully", registerResult);


        String modifyResult = controller.modifyActivity("task", "new description", Calendar.getInstance(), 2, 2);
        assertEquals("The activity was modified successfully", modifyResult);


        Activity modifiedActivity = controller.getActivitiesManager().getActivity("task");
        assertEquals("new description", modifiedActivity.getDescription());
        assertFalse(modifiedActivity.getPriority());
        assertEquals(ActivityType.REMINDER, modifiedActivity.getType());
    }

    @Test
    public void testModifyActivityNotFound() throws QueueException, StackException {
        setUp1();


        String modifyResult = controller.modifyActivity("non_existent_task", "new description", Calendar.getInstance(), 2, 2);
        assertEquals("Activity not found", modifyResult);


        String undoResult = controller.undoLastAction();
        assertEquals("There is no action to undo", undoResult);
    }


}
