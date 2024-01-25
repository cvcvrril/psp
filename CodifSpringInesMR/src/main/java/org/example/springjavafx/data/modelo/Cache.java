package org.example.springjavafx.data.modelo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.context.annotation.ApplicationScope;

@ApplicationScope
@Getter
@Setter
public class Cache {

    private String userPassword;

}
