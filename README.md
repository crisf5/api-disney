# API Disney World!

API con manejo de Personajes y Peliculas de Disney

**NOTAS**
- Para crear una Pelicula tiene que estar creado previamente el genero a usar. Se pueden crear desde la Base de Datos en la tabla **genres** o desde **/genres**. 
En caso de que el genero no esté creado previamente, el programa lanzará un error: 
"Error, Genre is not found".

- Se pueden hacer relaciones entre personajes y peliculas desde la Base de Datos en la tabla **character_movie** o tambien creando los Personajes al crearse las Peliculas.

**ADICIONALES**
- Se agregó a las listas basicas la posibilidad de Filtrar por atributos. En caso de que se quiera traer la lista completa, no se tiene que seleccionar ningun filtro.

## Documentacion

[Documentacion realizada en Postman](https://documenter.getpostman.com/view/19756170/UVkqqZpo)

El Postman para probar se encuentra en el paquete **docs**