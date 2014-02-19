/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.chimera.gui.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import ph.edu.dlsu.chimera.components.Component;
import ph.edu.dlsu.chimera.components.ComponentActiveProcessor;
import ph.edu.dlsu.chimera.core.Statistics;
import ph.edu.dlsu.chimera.monitors.PhaseMonitor;

/**
 *
 * @author Administrator
 */
public abstract class Task<TMonitor extends PhaseMonitor> extends Thread {

    private static Task task;
    public final ArrayList<Exception> exceptions;
    public final TMonitor monitor;
    private final HashMap<String, Statistics[]> graphingStatistics;

    public Task(TMonitor monitor) {
        this.exceptions = new ArrayList<Exception>();
        this.monitor = monitor;
        this.graphingStatistics = new HashMap<String, Statistics[]>();
    }

    @Override
    public void run() {
        try {
            for (String _c : this.monitor.getComponents().keySet()) {
                Component c = this.monitor.getComponents().get(_c);
                if (c instanceof ComponentActiveProcessor) {
                    ComponentActiveProcessor cap = (ComponentActiveProcessor) c;
                    this.graphingStatistics.put(_c, new Statistics[20]);
                }
            }
            this.doTask();
        } catch (Exception ex) {
            ex.printStackTrace();
            this.exceptions.add(ex);
        }
    }

    public HashMap<String, Statistics[]> getStatistics() {
        for (String _c : this.graphingStatistics.keySet()) {
            Statistics[] cq = this.graphingStatistics.get(_c);
            //shift right
            for (int i = 0; i < cq.length && cq.length - i - 2 >= 0; i++) {
                cq[cq.length - i - 1] = cq[cq.length - i - 2];
            }
            //commit current stats
            Component c = this.monitor.getComponents().get(_c);
            if (c instanceof ComponentActiveProcessor) {
                ComponentActiveProcessor cap = (ComponentActiveProcessor) c;
                cq[0] = cap.stats.copy();
            } else {
                cq[0] = null;
            }
        }
        return this.graphingStatistics;
    }

    protected abstract void doTask() throws Exception;

    public static void setTask(Task task) {
        Task.task = task;
    }

    public static Task getTask() {
        return Task.task;
    }

    public static void terminateTask() {
        if (Task.task != null && Task.task.monitor != null) {
            Task.task.monitor.terminate();
            Task.task = null;
        }
    }
}
