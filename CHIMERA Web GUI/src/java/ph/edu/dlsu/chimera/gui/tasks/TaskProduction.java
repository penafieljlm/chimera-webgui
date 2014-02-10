/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.chimera.gui.tasks;

import ph.edu.dlsu.chimera.Chimera;
import ph.edu.dlsu.chimera.monitors.PhaseMonitorProduction;

/**
 *
 * @author root
 */
public class TaskProduction extends Task<PhaseMonitorProduction> {

    public final String input;
    public final String syslog;
    public final int syslogport;
    public final boolean active;

    public TaskProduction(PhaseMonitorProduction _monitor, String _input, String _syslog, int _syslogport, boolean _active) {
        super(_monitor);
        this.input = _input;
        this.syslog = _syslog;
        this.syslogport = _syslogport;
        this.active = _active;
    }

    @Override
    protected void doTask() throws Exception {
        Chimera.cproduce(this.monitor, this.input, this.syslog, this.syslogport, this.active);
    }
}
