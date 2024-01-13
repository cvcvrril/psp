package jakarta.excepciones;

import jakarta.ConstantsJakarta;
import jakarta.json.bind.annotation.JsonbPropertyOrder;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonbPropertyOrder({ConstantsJakarta.MESSAGE, ConstantsJakarta.FECHA})
public class ApiError {

    private String message;
    private LocalDateTime fecha;

}
