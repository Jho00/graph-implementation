package com.company.Graph.Presenter;

import com.company.Graph.Model.Entity.Storage.AdjLists;
import com.company.Graph.Model.Entity.Storage.AdjMatrix;
import com.company.Graph.Model.Entity.Command.Base.AbstractCommand;
import com.company.Graph.Model.Entity.Command.LoadGraphListsCommand;
import com.company.Graph.Model.Entity.Command.LoadGraphMatrixCommand;

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
        this.commands.put(2, new LoadGraphListsCommand(this));
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

    public void printCurrentMatrix() {
        for(int i = 0; i < AdjMatrix.getLength(); i++) {
            for(int j = 0; j < AdjMatrix.getLength(); j++) {
                ConsoleViewWorker.printMatrixChar(AdjMatrix.getMatrix()[i][j]);
            }
            ConsoleViewWorker.printNewLine();
        }
    }

    public void printCurrnetLists() {
        AdjLists.getLists().forEach(item -> {
            ConsoleViewWorker.printLine("Вершина " + item.getId());
            item.getAdjacencyList().forEach(adjItem -> {
                ConsoleViewWorker.printLine(adjItem.getNode() + " " + adjItem.getWeight());
            });

            ConsoleViewWorker.printDelimiter();
        });
    }
}
