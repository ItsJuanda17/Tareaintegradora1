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
        Calendar taskDeadLine = Calendar.getInstance();
        taskDeadLine.set(Calendar.YEAR, 2023);
        taskDeadLine.set(Calendar.MONTH, Calendar.OCTOBER);
        taskDeadLine.set(Calendar.DAY_OF_MONTH, 8);

        controller.registerActivity("task", "description", taskDeadLine, 1, 1);
        controller.registerActivity("task2", "description", taskDeadLine, 1, 1);
        controller.modifyActivity("task2", "task3", "description", Calendar.getInstance(), 2, 2);

        try {
            controller.removeActivity("task2");
        } catch (StackException | QueueException e) {
           e.getStackTrace();
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
    public void testUndoLastActionWhenEmpty(){
        // setup
        setUp1();

        // act
        String message = controller.undoLasAction();

        // assert
        assertEquals("There is no action to undo", message);
    }

    @Test
    public void testUndoLastActionAdd(){
        // setup
        setUp1();

        controller.registerActivity("task3", "description", Calendar.getInstance(), 1, 1);
        String message = controller.undoLasAction();

        // assert
        assertEquals("The add activity action was undone successfully", message);
    }

    @Test
    public void testUndoLastActionModify(){
        // setup
        setUp1();
        boolean exceptionThrown = false;

        // act
        controller.registerActivity("task3", "description", Calendar.getInstance(), 1, 1);
        try {
            controller.modifyActivity("task3", "task4", "description2", Calendar.getInstance(), 2, 2);
        } catch (StackException | QueueException e) {
            exceptionThrown = true;
        }
        String message = controller.undoLasAction();

        assertFalse(exceptionThrown);
        assertEquals("The modify activity action action was undone successfully", message);
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
        String message = controller.undoLasAction();

        assertFalse(exceptionThrown);
        assertEquals("The remove activity action was undone successfully", message);
    }

    @Test
    public void testUndoMultipleActions(){
        // setup
        setUp2();

        // act
        String message = controller.undoLasAction();
        String message2 = controller.undoLasAction();
        String message3 = controller.undoLasAction();
        String message4 = controller.undoLasAction();
        String message5 = controller.undoLasAction();

        assertEquals("The remove activity action was undone successfully", message);
        assertEquals("The modify activity action action was undone successfully", message2);
        assertEquals("The add activity action was undone successfully", message3);
        assertEquals("The add activity action was undone successfully", message4);
        assertEquals("There is no action to undo", message5);
    }


}
