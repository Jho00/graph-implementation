package com.company.Graph.Model.Entity.Command;


import com.company.Graph.Model.AdjMatrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadGraphCommand extends AbstractCommand {
    private File file;
    private FileReader reader;
    private List<Integer> lineOfMatrix;
    private int[][] gettedMatrix;
    private boolean isFirstLine;
    private int lastIndex;


    public LoadGraphCommand(String pathToFile) {
        this.file = new File(pathToFile);
        try {
            this.reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace(); // Всегда существует
        }
        this.lineOfMatrix = new ArrayList<>();
        this.isFirstLine = true;
        this.lastIndex = 0;
    }

    @Override
    public boolean execute() {
        int c;
        while(true){
            try {
                c = this.reader.read();
                if(c != ' ' &&  c!= '\n') {
                    this.lineOfMatrix.add(Character.getNumericValue(c));
                }
                if (c == '\n') {
                    if(isFirstLine) {
                        this.createMatrixArray(this.lineOfMatrix.size());
                        this.isFirstLine = false;
                    }
                    this.createNextLine();
                    this.lineOfMatrix = new ArrayList<>();
                }
                if (c == -1) {
                    AdjMatrix.load(this.gettedMatrix);
                    break;
                }
            } catch (IOException e) {
                return false;
            }

        }

        return true;
    }

    private void createMatrixArray(int len) {
        this.gettedMatrix = new int[len][len];
    }

    private void createNextLine() {
        final int[] i = {0};
        this.lineOfMatrix.forEach(item -> {
            this.gettedMatrix[this.lastIndex][i[0]] = item;
            i[0]++;
        });
        this.lastIndex++;
    }
}
