package jakarta.excepciones;

import jakarta.json.bind.annotation.JsonbPropertyOrder;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonbPropertyOrder({"message", "fecha"})
public class ApiError {

    private String message;
    private LocalDateTime fecha;

}
