package com.company.Graph.Model.Entity.Command.Base;

import com.company.Graph.Presenter.GraphPresenter;

public abstract class AbstractCommand  {
    protected GraphPresenter presenter;

    public AbstractCommand(GraphPresenter presenter) {
        this.presenter = presenter;
    }

    public abstract boolean execute();
}
