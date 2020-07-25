# Android Jetpack


Aplicación movil para mostrar películas, consume una API externa de peliculas desde https://www.themoviedb.org/

### Tech Stack

* Kotlin 5
* Retrofit
* Koin
* Room
* Anko
* Glide

### Pasos de configuracion para levantar la aplicacion


1. Se requiere una cuenta de usuario en https://www.themoviedb.org/ para obtener una clave (APIKEY) y poder consumir informacion de las películas.


2. Una vez obtenida la clave (APIKEY), Setear el valor de la clave en la clase `Constants.kt`

```
   const val KEY_API_MOVIE = <TU_API_KEY>
```
 
3. Ejecutar la aplicación



