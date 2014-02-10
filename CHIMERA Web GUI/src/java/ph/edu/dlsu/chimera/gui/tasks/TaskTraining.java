/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.chimera.gui.tasks;

import ph.edu.dlsu.chimera.Chimera;
import ph.edu.dlsu.chimera.monitors.PhaseMonitorTraining;

/**
 *
 * @author root
 */
public class TaskTraining extends Task<PhaseMonitorTraining> {

    public final String input;
    public final String output;
    public final String filter;
    public final boolean exclude;

    public TaskTraining(PhaseMonitorTraining _monitor, String _input, String _output, String _filter, boolean _exclude) {
        super(_monitor);
        this.input = _input;
        this.output = _output;
        this.filter = _filter;
        this.exclude = _exclude;
    }

    @Override
    protected void doTask() throws Exception {
        Chimera.ctrain(this.monitor, this.input, this.output, this.filter, this.exclude);
    }
}
