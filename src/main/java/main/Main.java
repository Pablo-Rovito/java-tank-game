package main;

/*
Lista de jugadores:
**Reutilizar la lista de jugadores para guardar scores históricos
***Eso implica sumar un campo nombre para mostrarlo
***También implica guardar la lista en un archivito cada tanto para poder levantarlo

Crear pantallas:
**Bienvenida
**Fin de juego

Crear mapa sencillo

Dibujar sprites:
**Tanque, torreta y upgrades
**Proyectiles
**Torretas y upgrades
**Fondo para la pantalla
**Entidades del mapa

Cambiar atan x atan2 en player.checkFire()

Torretas:
**Por cada torreta destruída generar 2 nuevas.
**Limitar el número máximo de torretas
**Si MAX_TORRETA -> mejorar nivel de torreta
**Hacer un boss final y darle fin al juego

Mejorar posicionamiento:
**Tener en cuenta discrete collision y hacerlo aplicable a cualquier entidad
**Modificar projectile.remove una vez que esté bien el posicionamiento
*/

public class Main {
    public static void main(String[] args) {
        new LandingWindow();
    }
}