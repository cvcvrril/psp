package dao.imp;

import dao.DAOclients;
import io.vavr.control.Either;
import model.Client;
import model.errors.ErrorCCustomer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DAOclientsIMP implements DAOclients {

    public List<Client>clientsList(){
        List<Client> resClientsList = new ArrayList<>();
        resClientsList.add(new Client(1, "Pepe", "Pepito", "pepepepito@gmail.com", 123456789, LocalDate.now()));
        resClientsList.add(new Client(2, "Juan", "Juanito", "juanjuanito@gmail.com", 987654321, LocalDate.now()));
        resClientsList.add(new Client(3, "Lola", "Lolita", "lolalolita@gmail.com", 111111111, LocalDate.now()));

        return  resClientsList;
    }

    public List<Client> getClients() {
        return clientsList();
    }


    @Override
    public Either<ErrorCCustomer, List<Client>> getAll() {
        return null;
    }

    @Override
    public Either<ErrorCCustomer, Client> get(int i) {
        return null;
    }

    @Override
    public Either<ErrorCCustomer, Integer> save(Client t) {
        return null;
    }

    @Override
    public Either<ErrorCCustomer, Integer> update(Client t) {
        return null;
    }

    @Override
    public Either<ErrorCCustomer, Integer> delete(Client t) {
        return null;
    }
}

