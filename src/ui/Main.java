package ui;

import model.ActivitieType;
import model.Activities;
import model.HashTable;

import java.util.Calendar;

public class Main {
    public static void main(String[] args) {

        HashTable<Integer, Activities> taskTable = new HashTable<>();


        Calendar deadline1 = Calendar.getInstance();
        deadline1.set(2023, Calendar.DECEMBER, 31);
        Activities task1 = new Activities("Tarea 1", "Descripción 1", deadline1, true, ActivitieType.PRIORITY);

        Calendar deadline2 = Calendar.getInstance();
        deadline2.set(2023, Calendar.NOVEMBER, 15);
        Activities task2 = new Activities("Tarea 2", "Descripción 2", deadline2, false, ActivitieType.NON_PRIORITY);

        taskTable.put(1, task1);
        taskTable.put(2, task2);


        Activities retrievedTask = taskTable.get(1);
        if (retrievedTask != null) {
            System.out.println("Título de la tarea 1: " + retrievedTask.getTitle());
            System.out.println("Descripción de la tarea 1: " + retrievedTask.getDescription());
            System.out.println("Fecha límite de la tarea 1: " + retrievedTask.getDeadLine().getTime());
        } else {
            System.out.println("Tarea no encontrada.");
        }
    }
}
