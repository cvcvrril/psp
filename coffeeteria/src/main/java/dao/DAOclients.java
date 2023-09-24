package dao;

import model.Client;
import model.Order;

public interface DAOclients {

    int save(Client t);

    int uptdate(Client t);

    int delete(Client t);


}
