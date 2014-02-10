/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.chimera.gui.tasks;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public abstract class Task extends Thread {

    private static Task task;

    public final ArrayList<Exception> exceptions;

    public Task() {
        this.exceptions = new ArrayList<Exception>();
    }

    @Override
    public void run() {
        try {
            this.doTask();
        } catch (Exception ex) {
            this.exceptions.add(ex);
        }
    }

    protected abstract void doTask() throws Exception;

    public static void setTask(Task task) {
        Task.task = task;
    }

    public static Task getTask() {
        return Task.task;
    }

}
