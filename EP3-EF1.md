# Entrega Parcial 3 - Entrega Final Tarea 1

## Proyecto

El objetivo de la entrega final es completar la estructura base del proyecto, para esto debe terminar su implementación de las tareas parciales previas y añadir las funcionalidades mencionadas en este documento.

### Mano (Sección 2.4)

Usted debe poder realizar las siguientes acciones:

- Añadir cartas a la mano
- Quitar cartas a la mano, según indices
- Añadir jokers a la mano
- Quitar jokers a la mano, según indices
- "Jugar" una mano, para esto:
    - Se le entrega una lista de indices, correspondientes a las cartas que desean ser jugadas.
    - Usted debe devolver la lista de cartas correspondientes a los índices y eliminar las mismas de la mano.

Para estas implementaciones no debe preocuparse de los casos bordes, en particular puede asumir:

- Que hay espacio en la mano para añadir las cartas y jokers.
- Que al quitar cartas y jokers, se entregarán indices válidos.
- Que al jugar una mano le entregaran una lista con al menos 1 índice y a lo más 5, y que estos índices existen.

## Git

El trabajo realizado para esta entrega se debe realizar en una nueva rama que deberá
llamar ``entrega-final-1``:

1. Asegúrese de estar en la rama más reciente de su trabajo. Puede usar el comando
``git checkout <last_branch>`` para cambiarse de rama, donde `<last_branch>` es la
rama de su última entrega.

2. Utilice el comando ``git branch <branch_name>`` para crear la rama. En este caso, sería
``git branch entrega-final-1``.

3. Recuerde que para que su progreso pueda ser almacenado en dicha rama, debe cambiarse de
rama utilizando el comando ``git checkout <branch_name>``. En este caso, sería
``git checkout entrega-final-1``.

*Tenga en cuenta que el cuerpo docente tiene acceso total a su repositorio.*

## Entrega

Para subir su Entrega Final, deberá crear un *pull request* desde la rama
``entrega-final-1`` a la rama ``master`` llamado ``Tarea 1 - Entrega Final``.

Es importante que **no hagan merge** de la rama ``entrega-final-1`` a la rama ``master`` 
para que el cuerpo docente pueda revisar su *pull request*.

Por *U-Cursos* debe entregar un único archivo llamado ``entrega-final-1.txt`` con el
siguiente contenido:

```
Nombre: <Nombre completo>
Pull Request: <Link del pull request>
```

No cumplir con el formato pedido de una Entrega Final, tiene descuentos en su nota.
