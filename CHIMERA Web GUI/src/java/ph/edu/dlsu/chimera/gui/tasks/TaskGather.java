/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.chimera.gui.tasks;

import java.util.logging.Level;
import java.util.logging.Logger;
import ph.edu.dlsu.chimera.Chimera;
import ph.edu.dlsu.chimera.monitors.PhaseMonitorGathering;

/**
 *
 * @author Administrator
 */
public class TaskGather extends Task {

    public final String _output;
    public final String _protected;
    public final String _access;
    public final boolean _allow;
    public final String _training;
    public final boolean _attack;
    public final PhaseMonitorGathering _monitor;

    public TaskGather(String _output, String _protected, String _access, boolean _allow, String _training, boolean _attack, PhaseMonitorGathering _monitor) {
        this._output = _output;
        this._protected = _protected;
        this._access = _access;
        this._allow = _allow;
        this._training = _training;
        this._attack = _attack;
        this._monitor = _monitor;
    }

    @Override
    public void run() {
        try {
            Chimera.cgather(this._monitor, this._output, this._protected, this._access, this._allow, this._training, this._attack);
        } catch (Exception ex) {
        }
    }
}
