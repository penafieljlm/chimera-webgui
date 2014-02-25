/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.chimera.gui.tasks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import ph.edu.dlsu.chimera.Chimera;
import ph.edu.dlsu.chimera.monitors.PhaseMonitorProduction;

/**
 *
 * @author root
 */
public class TaskProduction extends Task<PhaseMonitorProduction> {

    public final InputStream input;
    public final String syslog;
    public final Integer syslogport;
    public final boolean active;

    public TaskProduction(PhaseMonitorProduction _monitor, InputStream _input, String _syslog, Integer _syslogport, boolean _active) {
        super(_monitor);
        this.input = _input;
        this.syslog = _syslog;
        this.syslogport = _syslogport;
        this.active = _active;
    }

    @Override
    protected void doTask() throws Exception {
        String suffix = ".cmodel";
        File tFile = File.createTempFile("model_", suffix);
        FileOutputStream fs = new FileOutputStream(tFile);
        byte[] buffer = new byte[1024];
        int len;
        while ((len = this.input.read(buffer)) != -1) {
            fs.write(buffer, 0, len);
        }
        StringBuilder sb = new StringBuilder(tFile.getAbsolutePath());
        int i = sb.lastIndexOf(suffix);
        String inputfilename = sb.substring(0, i);
        Chimera.cproduce(this.monitor, inputfilename, this.syslog, this.syslogport, this.active);
        tFile.delete();
    }
}
