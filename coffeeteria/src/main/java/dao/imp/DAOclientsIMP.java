package dao.imp;

import dao.DAOclients;
import io.vavr.control.Either;
import model.Client;
import model.errors.ErrorC;

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

//    private static final List<Client> clients;
//
//    static {
//        clients = new ArrayList<>();
//        clients.add(new Client(1, "Pepe", "Pepito", "pepepepito@gmail.com", 123456789));
//        clients.add(new Client(2, "Juan", "Juanito", "juanjuanito@gmail.com", 987654321));
//        clients.add(new Client(3, "Lola", "Lolita", "lolalolita@gmail.com", 111111111));
//
//    }

    public List<Client> getClients() {
        return clientsList();
        //return clients;
    }


    @Override
    public Either<ErrorC, List<Client>> getAll() {
        return null;
    }

    @Override
    public Either<ErrorC, Client> get(int i) {
        return null;
    }

    @Override
    public Either<ErrorC, Integer> save(Client t) {
        return null;
    }

    @Override
    public Either<ErrorC, Integer> update(Client t) {
        return null;
    }

    @Override
    public Either<ErrorC, Integer> delete(Client t) {
        return null;
    }
}

