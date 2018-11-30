package com.company.Graph;


import com.company.Graph.Presenter.GraphPresenter;

public class Main {
    private static GraphPresenter presenter;
    public static void main(String[] args) {
        presenter = new GraphPresenter();
        presenter.runMenu();
    }

}
