package ui.pantallas.common;


import ui.pantallas.customerlist.ListController;

public class BasePantallaController {

    private ListController listController;

    public ListController getPrincipalController() {
        return listController;
    }

    public void setPrincipalController(ListController listController) {
        this.listController = listController;
    }

    public void principalCargado()
    {

    }
}
