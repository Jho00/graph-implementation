package com.company.Graph.Presenter;

import com.company.Graph.Model.Entity.Command.*;
import com.company.Graph.Model.Entity.Storage.AdjLists;
import com.company.Graph.Model.Entity.Storage.AdjMatrix;
import com.company.Graph.Model.Entity.Command.Base.AbstractCommand;

import com.company.Graph.Model.Entity.Exceptions.IllegalCommandException;
import com.company.Graph.View.ConsoleViewWorker;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GraphPresenter {
    private Map<Integer, AbstractCommand> commands;

    public GraphPresenter() {
        this.commands = new HashMap<>();
        this.commands.put(1, new LoadGraphMatrixCommand(this));
        this.commands.put(2, new LoadGraphListsJsonCommand(this));
        this.commands.put(3, new SaveMatrixCommand(this));
        this.commands.put(4, new SaveListsCommand(this));
        this.commands.put(5, new CreateEmptyGraphCommand(this));
    }

    public void runMenu() {
        while (true) {
            ConsoleViewWorker.printFirstMenu();
            AbstractCommand command = null;
            try {
                command = this.getCommand(ConsoleViewWorker.getGeneralAction());
            } catch (IllegalCommandException e) {
                ConsoleViewWorker.printErrorMessage(e.getMessage());
            }

            if(Objects.requireNonNull(command).execute()) {
                this.printSuccessMessage();
                continue;
            }

            ConsoleViewWorker.printErrorMessage("Что-то пошло не так, повторите попытку");
        }
    }

    private AbstractCommand getCommand(int action) throws IllegalCommandException {
        AbstractCommand command = this.commands.get(action);
        if(command == null) {
            throw new IllegalCommandException("Неверная команда");
        }
        return  command;
    }

    public void printErrorMessage(String message) {
        ConsoleViewWorker.printErrorMessage(message);
    }

    public void printSuccessMessage() {
        ConsoleViewWorker.printSuccessMessage();
    }

    public String getPathToMatrixFile() {
        return ConsoleViewWorker.getPathToMatrixFile();
    }
    public String getPathToListsFile() {
        return ConsoleViewWorker.getPathToListsFile();
    }
    public String getFileNameToSave() {
        return ConsoleViewWorker.getFileName();
    }

    public void printCurrentMatrix() {
        int len = AdjMatrix.getLength();
        if(len == 0) {
            printErrorMessage("Граф пуст");
        }
        for(int i = 0; i < len; i++) {
            for(int j = 0; j < len; j++) {
                ConsoleViewWorker.printMatrixChar(AdjMatrix.getMatrix()[i][j]);
            }
            ConsoleViewWorker.printNewLine();
        }
    }

    public void printCurrnetLists() {
        var lists = AdjLists.getLists();
        if(lists.size() == 0) {
            printErrorMessage("Граф пуст");
        }
        lists.forEach(item -> {
            ConsoleViewWorker.printLine("Вершина " + item.getId());
            item.getAdjacencyList().forEach(adjItem -> {
                ConsoleViewWorker.printLine(adjItem.getNode() + " " + adjItem.getWeight());
            });

            ConsoleViewWorker.printDelimiter();
        });
    }
}
