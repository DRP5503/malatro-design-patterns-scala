# Entrega Parcial 6

## Proyecto

El objetivo de esta entrega será empezar a implementar el controlador del videojuego, en particular, sus respectivas fases.

Para guiarles en este proceso, contará con una lista de requisitos a cumplir. Cada uno de estos requisitos deberá estar reflejado en su proyecto al momento de realizar la entrega final, tanto de manera funcional como testeada. El requisito en cuestión solo indica *qué* es lo que debe hacer, sin embargo, el *cómo* hacerlo de la mejor manera recaerá en usted.

### Controlador y flujo del juego

Debe comenzar a hacer el controlador del juego. Para eso, debe:

+ Implementar los patrones de diseño necesarios para modelar los estados de la partida.
+ Implementar los métodos necesarios en el controlador que gatillen los eventos del diagrama de estados.

Con esto, el controlador debiese ser capaz de hacer las siguientes acciones en las fases que correspondan:

+ Inicializar una partida.
+ Hacer que se pueda jugar un conjunto de cartas.
+ Hacer que se pueda descartar un conjunto de cartas.
+ Finalizar una partida. En el instante en que la mano se quede sin jugadas, el controlador deberá ser notificado, y entregar un mensaje distinto al ejecutado, indicando la finalización del juego. Para ello, deberá utilizar **patrones de diseño**, usando el adecuado para este caso.

**MUY IMPORTANTE**: A continuación viene un snippet con una **IDEA** de lo que se tiene que hacer. ¿Se tiene que hacer tal cual? **NO**, tienen toda libertad de decidir como hacerlo, y de ajustar el código como más les acomode. Es probable que su controlador requiera más métodos para funcionar.

```scala
class GameController {
    // Estado actual del juego
    var state: GameState = new SomeState(this)

    def eventA(): Unit = { 
        state.eventA()
        /* ... */
    }

    def eventB(): Unit = {
        /* ... */
    }
}

trait GameState {
    def eventA(): Unit
    def eventB(): Unit
}

class SomeState(val controller: GameController) extends GameState {
    override def eventA(): Unit = {
        /* ... */
        controller.state = new /* ... */
    }

    override def eventB(): Unit = {
        /* ... */
        controller.state = new /* ... */
    }
}
```

### 

## Git

El trabajo realizado para esta entrega se debe realizar en una nueva rama que deberá
llamar ``entrega-parcial-6``:

1. Asegúrese de estar en la rama más reciente de su trabajo. Puede usar el comando
``git checkout <last_branch>`` para cambiarse de rama, donde `<last_branch>` es la
rama de su última entrega.

2. Utilice el comando ``git branch <branch_name>`` para crear la rama. En este caso, sería
``git branch entrega-parcial-6``.

3. Recuerde que para que su progreso pueda ser almacenado en dicha rama, debe cambiarse de
rama utilizando el comando ``git checkout <branch_name>``. En este caso, sería
``git checkout entrega-parcial-6``.

*Tenga en cuenta que el cuerpo docente tiene acceso total a su repositorio.*

## Entrega

Para subir su Entrega Parcial, deberá crear un *pull request* desde la rama
``entrega-parcial-6`` a la rama ``master`` llamado ``Tarea 3 - Entrega Parcial 6``.

Es importante que **no hagan merge** de la rama ``entrega-parcial-6`` a la rama ``master`` 
para que el cuerpo docente pueda revisar su *pull request*.

Por *U-Cursos* debe entregar un único archivo llamado ``entrega-parcial-6.txt`` con el
siguiente contenido:

```
Nombre: <Nombre completo>
Pull Request: <Link del pull request>
```

No cumplir con el formato pedido de una Entrega Parcial podría llevar a no ser
considerada, y para una Entrega Final, tiene descuentos en su nota final.
