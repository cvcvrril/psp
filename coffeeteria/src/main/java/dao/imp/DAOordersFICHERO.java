package dao.imp;

import common.Configuration;
import dao.DAOorder;
import io.vavr.control.Either;
import lombok.extern.log4j.Log4j2;
import model.Order;
import model.errors.ErrorCOrder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DAOordersFICHERO implements DAOorder {

    @Override
    public Either<ErrorCOrder, List<Order>> getAll() {
        Path path = Paths.get(Configuration.getInstance().getProperty("pathDataOrders"));
        List<Order> orders = new ArrayList<>();
        BufferedReader reader = null;
        try{
            reader = Files.newBufferedReader(path);
            DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String line;
            while ((line = reader.readLine()) != null){
                String[] trozo = line.split(";");
                //Order = int id_co, int id_ord, int id_table, LocalDate or_date
                orders.add(new Order(Integer.parseInt(trozo[0]), Integer.parseInt(trozo[1]), Integer.parseInt(trozo[2]), LocalDate.parse(trozo[3], form)));

            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return Either.right(orders);
    }

    @Override
    public Either<ErrorCOrder, Order> getOrder(int id) {
        return null;
    }

    @Override
    public Either<ErrorCOrder, Integer> save(Order t) {
        Path path = Paths.get(Configuration.getInstance().getProperty("pathDataOrders"));
        int error = 0;
        try{
            BufferedWriter writer = Files.newBufferedWriter(path, java.nio.file.StandardOpenOption.APPEND);
            DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String orderString = String.format("%d;%d;%d;%s%n", t.getId_co(), t.getId_ord(), t.getId_table(), t.getOr_date().format(form));
            writer.write(orderString);
            writer.close();
            error = 1;
        } catch (IOException e) {
            log.error("Error writing the file", e);
            return Either.left(new ErrorCOrder("Error al guardar la orden", 1));
        }
        return Either.right(error);
    }

    @Override
    public Either<ErrorCOrder, Integer> update(Order t) {
        return null;
    }

    @Override
    public Either<ErrorCOrder, Integer> delete(Order t) {
        return null;
    }



}
