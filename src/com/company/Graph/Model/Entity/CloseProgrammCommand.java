package com.company.Graph.Model.Entity;

import com.company.Graph.Model.Entity.Command.Base.AbstractCommand;
import com.company.Graph.Presenter.GraphPresenter;

public class CloseProgrammCommand extends AbstractCommand {
    public CloseProgrammCommand(GraphPresenter presenter) {
        super(presenter);
    }

    @Override
    public boolean execute() {
        presenter.printMessage("Ждем вашего возвращения :)");
        System.exit(0);
        return false;
    }
}
