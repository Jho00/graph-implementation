package com.company.Graph.Model.Entity.Command;

import jdk.jshell.spi.ExecutionControl;

import java.io.File;
import java.io.IOException;

public abstract class AbstractCommand  {
    public abstract boolean execute();

    protected boolean isValidPath(File file) {
        return file.exists();
    }
}
