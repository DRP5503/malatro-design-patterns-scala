# Entrega Parcial 4

## Proyecto

En esta entrega parcial se espera que usted implemente excepciones en su proyecto y comenzar la implementación del cálculo de puntaje, para lo que se le solicitan los siguientes puntos:

### Excepciones
- Una mano no puede tener más de 8 cartas.
- Una mano no puede tener más de 2 jokers.
- No se puede quitar una carta que no exista en la mano. (Índice inválido)
- No se puede quitar un joker que no exista en la mano. (Índice inválido)
- No se puede jugar más de 3 veces un conjunto de cartas.
- No se puede descartar más de 3 veces. (Implementar método asociado)
- No se puede jugar más de 5 cartas.
- No se puede jugar menos de 1 carta.
- No se puede jugar una lista de índices inválidos (fuera de rango), no puede asumir que los índices están ordenados.
- No se puede descartar más de 5 cartas.
- No se puede descartar menos de 1 carta.
- No se puede descartar una lista de índices inválidos (fuera de rango), no puede asumir que los índices están ordenados.

### Cálculo de Puntaje (Parte 1)
Dado un método general para calcular puntaje ``applyScore(score: Score, j: Joker): Score``, implemente las interacciones necesarias para calcular el puntaje cuando:
- Se calcula el puntaje de una combinación de poker, dado un puntaje y un joker.
- Se calcula el puntaje de una pinta, dado un puntaje y un joker.
- Se calcula el puntaje de un rango, tomando su tipo de rango en consideración, dado un puntaje y un joker.

Note que a pesar de que las instrucciones son breves, el código a implementar es largo, no se sienta extrañado. **IMPORTANTE**: NO UTILIZAR _PATTERN MATCHING_ (match-case) NI PROGRAMACIÓN FUNCIONAL.

## Git

El trabajo realizado para esta entrega se debe realizar en una nueva rama que deberá
llamar ``entrega-parcial-4``:

1. Asegúrese de estar en la rama más reciente de su trabajo. Puede usar el comando
``git checkout <last_branch>`` para cambiarse de rama, donde `<last_branch>` es la
rama de su última entrega.

2. Utilice el comando ``git branch <branch_name>`` para crear la rama. En este caso, sería
``git branch entrega-parcial-4``.

3. Recuerde que para que su progreso pueda ser almacenado en dicha rama, debe cambiarse de
rama utilizando el comando ``git checkout <branch_name>``. En este caso, sería
``git checkout entrega-parcial-4``.

*Tenga en cuenta que el cuerpo docente tiene acceso total a su repositorio.*

## Entrega

Para subir su Entrega Parcial, deberá crear un *pull request* desde la rama
``entrega-parcial-4`` a la rama ``master`` llamado ``Tarea 2 - Entrega Parcial 4``.

Es importante que **no hagan merge** de la rama ``entrega-parcial-4`` a la rama ``master`` 
para que el cuerpo docente pueda revisar su *pull request*.

Por *U-Cursos* debe entregar un único archivo llamado ``entrega-parcial-4.txt`` con el
siguiente contenido:

```
Nombre: <Nombre completo>
Pull Request: <Link del pull request>
```

No cumplir con el formato pedido de una Entrega Parcial podría llevar a no ser
considerada, y para una Entrega Final, tiene descuentos en su nota final.
