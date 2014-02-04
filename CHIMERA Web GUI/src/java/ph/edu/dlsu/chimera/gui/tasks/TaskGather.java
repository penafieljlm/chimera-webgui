/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.chimera.gui.tasks;

import ph.edu.dlsu.chimera.Chimera;
import ph.edu.dlsu.chimera.monitors.PhaseMonitorGathering;

/**
 *
 * @author Administrator
 */
public class TaskGather extends Task {

    public final String output;
    public final String protectedIface;
    public final String access;
    public final boolean allow;
    public final String training;
    public final boolean attack;
    public final PhaseMonitorGathering monitor;

    public TaskGather(String _output, String _protected, String _access, boolean _allow, String _training, boolean _attack, PhaseMonitorGathering _monitor) {
        this.output = _output;
        this.protectedIface = _protected;
        this.access = _access;
        this.allow = _allow;
        this.training = _training;
        this.attack = _attack;
        this.monitor = _monitor;
    }

    @Override
    public void run() {
        try {
            Chimera.cgather(this.monitor, this.output, this.protectedIface, this.access, this.allow, this.training, this.attack);
        } catch (Exception ex) {
        }
    }
}
