/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.chimera.gui.tasks;

/**
 *
 * @author Administrator
 */
public abstract class Task extends Thread {

    private static Task task;

    public static void setTask(Task task) {
        Task.task = task;
    }

    public static Task getTask() {
        return Task.task;
    }

}
