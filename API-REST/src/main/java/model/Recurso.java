package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recurso {

    private Long id;
    private String titulo;
    private String url;
    private String tipo;
    private String categoria;

}
