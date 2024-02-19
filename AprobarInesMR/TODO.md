# TODOs:

### General

* [HECHO] Mover la contraseña de sitio (del config.properties al application.properties).
* [HECHO] Quitar el Configuration. Te lo hace solo Spring si metes el @Value. 
* Crear objetos DTO para devovler info (SIN TENER QUE METER LA CONTRASEÑA NI INFO SENSIBLE) al hacer login o el registro
* Para generar los tokens, hacerlo en un servicio y llamar ese servicio desde el rest -> para firmar se firma con la privada en vez de con la key 

### GraphQL

* Meter CRUD (add, delete, update) -> Ejemplo GraphQL. 
* Meter más objetos.
* Meter login sencillo.
* [HECHO] Meter registro sencillo.
* Mover de aquí el login y el registro al servidor de Seguridad.

### Seguridad

* Montar el filtro. Preguntar cómo coño hacerlo.
* Probar generación de Tokens (Spring).
* Probar conexiones con módulo/servidor GraphQL.

### Swagger

* Empezar a probarlo. Si hay problemas acudir a Luis.

---------------------------------------------------------------------

### Dudas

* No sé cómo meter el security ni donde (es necesario para el hasheo de las contraseñas)