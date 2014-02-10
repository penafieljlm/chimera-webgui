/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.chimera.gui.tasks;

import java.util.ArrayList;
import ph.edu.dlsu.chimera.monitors.PhaseMonitor;

/**
 *
 * @author Administrator
 */
public abstract class Task<TMonitor extends PhaseMonitor> extends Thread {

    private static Task task;
    public final ArrayList<Exception> exceptions;
    public final TMonitor monitor;

    public Task(TMonitor monitor) {
        this.exceptions = new ArrayList<Exception>();
        this.monitor = monitor;
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
