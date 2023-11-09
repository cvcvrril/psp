package dao.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Orden {

    private Integer idOr;
    private LocalDateTime orDate;
    private int idCu;
    private int idTable;
    private List<OrdenItem> ordenItems;

}
