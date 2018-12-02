package com.company.Graph.Model.Entity.Command.Base;

import com.company.Graph.Model.Entity.Exceptions.IllegalPathToGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public abstract class AbstractLoadCommand extends AbstractCommand {
    protected File file;
    protected FileReader reader;

    private boolean isValidPath(File file) {
        return file.exists();
    }

    protected void prepareReader() throws IllegalPathToGraph {
        if(!this.isValidPath(this.file)) {
            throw new IllegalPathToGraph("Путь до файла неверный");
        }

        try {
            this.reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace(); // Всегда существует
        }
    }
}
