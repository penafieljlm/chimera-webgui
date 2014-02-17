/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.chimera.gui.tasks;

import java.io.File;
import ph.edu.dlsu.chimera.Chimera;
import ph.edu.dlsu.chimera.monitors.PhaseMonitorGathering;

/**
 *
 * @author Administrator
 */
public class TaskGathering extends Task<PhaseMonitorGathering> implements TaskFileDownload {

    public final String output;
    public final String protectedIface;
    public final String access;
    public final boolean allow;
    public final String training;
    public final boolean attack;
    private File outputFile;

    public TaskGathering(PhaseMonitorGathering _monitor, String _output, String _protected, String _access, boolean _allow, String _training, boolean _attack) {
        super(_monitor);
        this.output = _output;
        this.protectedIface = _protected;
        this.access = _access;
        this.allow = _allow;
        this.training = _training;
        this.attack = _attack;
        this.outputFile = null;
    }

    @Override
    protected void doTask() throws Exception {
        this.outputFile = Chimera.cgather(this.monitor, this.output, this.protectedIface, this.access, this.allow, this.training, this.attack);
    }

    @Override
    public File getOutputFile() {
        return this.outputFile;
    }
}
