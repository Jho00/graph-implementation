package com.company.Graph.Model.Entity.Command.Base;

import com.company.Graph.Presenter.GraphPresenter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

public class AbstractWriteCommand extends AbstractCommand {
    protected FileWriter writer;

    public AbstractWriteCommand(GraphPresenter presenter) {
        super(presenter);
    }

    @Override
    public boolean execute() {
        return false;
    }

    protected void prepareWriter(String fileName) throws IOException {
        writer = new FileWriter(fileName);
    }
}
