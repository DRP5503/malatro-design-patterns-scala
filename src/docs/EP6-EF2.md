# Entrega Final Tarea 2

## Proyecto

En esta entrega final se espera continuar la implementación del cálculo de puntaje y la conclusión de lo solicitado en las entregas parciales, por lo que se le solicita lo siguiente:

### Cálculo de Puntaje (Parte 2)
Dado un método general para calcular puntaje ``applyScore(score: Score, jokers: List[Joker]): Score``, _considere que su lista puede ser un Array, ArrayBuffer, o similares, lo importante es que represente una lista de jokers_.

Implemente las interacciones necesarias para calcular el puntaje cuando:
- Se calcula el puntaje de una carta (la cual tiene rango, tipo de rango y pinta), dado un puntaje y una **lista** de jokers.

**IMPORTANTE**: NO UTILIZAR _PATTERN MATCHING_ (match-case) NI PROGRAMACIÓN FUNCIONAL.

## Git

El trabajo realizado para esta entrega se debe realizar en una nueva rama que deberá
llamar ``entrega-final-2``:

1. Asegúrese de estar en la rama más reciente de su trabajo. Puede usar el comando
``git checkout <last_branch>`` para cambiarse de rama, donde `<last_branch>` es la
rama de su última entrega.

2. Utilice el comando ``git branch <branch_name>`` para crear la rama. En este caso, sería
``git branch entrega-final-2``.

3. Recuerde que para que su progreso pueda ser almacenado en dicha rama, debe cambiarse de
rama utilizando el comando ``git checkout <branch_name>``. En este caso, sería
``git checkout entrega-final-2``.

*Tenga en cuenta que el cuerpo docente tiene acceso total a su repositorio.*

## Entrega

Para subir su Entrega Final, deberá crear un *pull request* desde la rama
``entrega-final-2`` a la rama ``master`` llamado ``Tarea 2 - Entrega Final``.

Es importante que **no hagan merge** de la rama ``entrega-final-2`` a la rama ``master`` 
para que el cuerpo docente pueda revisar su *pull request*.

Por *U-Cursos* debe entregar un único archivo llamado ``entrega-final-2.txt`` con el
siguiente contenido:

```
Nombre: <Nombre completo>
Pull Request: <Link del pull request>
```

No cumplir con el formato pedido de una Entrega Final, tiene descuentos en su nota.
