/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.chimera.gui.tasks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import ph.edu.dlsu.chimera.Chimera;
import ph.edu.dlsu.chimera.monitors.PhaseMonitorTraining;

/**
 *
 * @author root
 */
public class TaskTraining extends Task<PhaseMonitorTraining> {

    public final InputStream input;
    public final String output;
    public final String filter;
    public final boolean exclude;
    private double uploadProgress;

    public TaskTraining(PhaseMonitorTraining _monitor, InputStream _input, String _output, String _filter, boolean _exclude) {
        super(_monitor);
        this.input = _input;
        this.output = _output;
        this.filter = _filter;
        this.exclude = _exclude;
        this.uploadProgress = 0.0;
    }

    @Override
    protected void doTask() throws Exception {
        this.uploadProgress = 0.0;
        String suffix = ".ctset";
        File tFile = File.createTempFile(new Date().toLocaleString(), suffix);
        FileOutputStream fs = new FileOutputStream(tFile);
        byte[] buffer = new byte[1024];
        int len = 0;
        long written = 0;
        while ((len = this.input.read(buffer)) != -1) {
            fs.write(buffer, 0, len);
            written += len;
            this.uploadProgress = (double) (written / (tFile.length() * 1.0));
        }
        StringBuilder sb = new StringBuilder(tFile.getAbsolutePath());
        int i = sb.lastIndexOf(suffix);
        sb = sb.delete(i, suffix.length());
        this.uploadProgress = 1.0;
        Chimera.ctrain(this.monitor, sb.toString(), this.output, this.filter, this.exclude);
    }

    public double getUploadProgress() {
        return this.uploadProgress;
    }

}
