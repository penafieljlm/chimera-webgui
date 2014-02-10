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
public class TaskProduction extends Task {

    public final String input;
    public final String syslog;
    public final int syslogport;
    public final boolean active;
    public final PhaseMonitorProduction monitor;

    public TaskProduction(String input, String syslog, int syslogport, boolean active, PhaseMonitorProduction monitor) {
        this.input = input;
        this.syslog = syslog;
        this.syslogport = syslogport;
        this.active = active;
        this.monitor = monitor;
    }

    @Override
    protected void doTask() throws Exception {
        Chimera.cproduce(this.monitor, this.input, this.syslog, this.syslogport, this.active);
    }

}
