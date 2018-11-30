package com.company.Graph.Presenter;

import com.company.Graph.Model.Entity.Command.AbstractCommand;
import com.company.Graph.Model.Entity.Command.LoadGraphCommand;
import com.company.Graph.Model.Entity.Exceptions.IllegalCommandException;
import com.company.Graph.Model.Entity.Exceptions.IllegalPathToGraph;
import com.company.Graph.View.ConsoleViewWorker;

import java.io.File;

public class GraphPresenter {
    public void runMenu() {
        while (true) {
            ConsoleViewWorker.printFirstMenu();
            try {
                AbstractCommand command = this.getCommand(ConsoleViewWorker.getGeneralAction());
                if(!command.execute()) {
                    ConsoleViewWorker.printErrorMessage("Произошла незивестная ошибка, повторите дейтсвие");
                }
            } catch (IllegalCommandException | IllegalPathToGraph e) {
               ConsoleViewWorker.printErrorMessage(e.getMessage());
            }
        }
    }

    private AbstractCommand getCommand(int action) throws IllegalCommandException, IllegalPathToGraph {
        switch (action) {
            case 1 : return this.getLoadGraphCommand();
            default: throw new IllegalCommandException("Неверная команда");
        }
    }

    private LoadGraphCommand getLoadGraphCommand() throws IllegalPathToGraph {
        String path = ConsoleViewWorker.getPathToFile();
        if(this.validatePath(path)) {
            return new LoadGraphCommand(path);
        }

        throw new IllegalPathToGraph("Такого файла не существует");
    }

    private boolean validatePath(String path) {
        File file = new File(path);
        return file.exists();
    }
}
