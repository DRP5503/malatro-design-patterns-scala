# Entrega Parcial 5

## Proyecto

Para esta entrega, se les pide implementar el cálculo de puntaje completo y empezar a plantar su controlador del videojuego, en particular, sus respectivas fases.

Para guiarles en este proceso, contará con una lista de requisitos a cumplir. Cada uno de estos requisitos deberá estar reflejado en su proyecto al momento de realizar la entrega final, tanto de manera funcional como testeada. El requisito en cuestión solo indica *qué* es lo que debe hacer, sin embargo, el *cómo* hacerlo de la mejor manera recaerá en usted.

### Cálculo de Puntaje (Sección 2.4 del enunciado de Proyecto)
Debe crear una nueva estructura en su proyecto que le permita realizar la siguiente acción:

- Calcular el puntaje final dado un conjunto de cartas (1 a 5) y un conjunto de jokers (1 a 2). Para esto tiene que tener en consideración:
    - La combinación que forman las cartas.
    - El puntaje asociado a la combinación.
    - Los chips asociados a las cartas jugadas.
    - Los efectos activos de los Jokers.

Para un mayor detalle, siga las instrucciones dadas en la Sección 2.4 del enunciado, también puede ver la Sección 6, con un ejemplo de cálculo anexado.

### Diagrama de estados

Debe diseñar un diagrama de estados que modele el flujo de la partida. Para esto, lea el enunciado del proyecto e identifique los siguientes aspectos:

+ Las distintas etapas de la partida
+ Las transiciones entre las fases
+ Los eventos que disparan las transiciones

Para hacer este diagrama puede usar la herramienta que usted estime, por ejemplo,
le puede resulta útil la página [draw.io](https://app.diagrams.net).

El diagrama debe incluirse en el archivo ``README.md`` de su proyecto.
Para hacer esto, debe subir el archivo a su repositorio, por ejemplo en la carpeta ``docs``, con el nombre ``diagrama-estados.png``. Luego, debe incluir la imagen en el archivo ``README.md``:

```markdown
# Malatro
...

## Diagrama de estados

![Diagrama de estados](docs/diagrama-estados.png)
```

## Git

El trabajo realizado para esta entrega se debe realizar en una nueva rama que deberá
llamar ``entrega-parcial-5``:

1. Asegúrese de estar en la rama más reciente de su trabajo. Puede usar el comando
``git checkout <last_branch>`` para cambiarse de rama, donde `<last_branch>` es la
rama de su última entrega.

2. Utilice el comando ``git branch <branch_name>`` para crear la rama. En este caso, sería
``git branch entrega-parcial-5``.

3. Recuerde que para que su progreso pueda ser almacenado en dicha rama, debe cambiarse de
rama utilizando el comando ``git checkout <branch_name>``. En este caso, sería
``git checkout entrega-parcial-5``.

*Tenga en cuenta que el cuerpo docente tiene acceso total a su repositorio.*

## Entrega

Para subir su Entrega Parcial, deberá crear un *pull request* desde la rama
``entrega-parcial-5`` a la rama ``master`` llamado ``Tarea 3 - Entrega Parcial 5``.

Es importante que **no hagan merge** de la rama ``entrega-parcial-5`` a la rama ``master`` 
para que el cuerpo docente pueda revisar su *pull request*.

Por *U-Cursos* debe entregar un único archivo llamado ``entrega-parcial-5.txt`` con el
siguiente contenido:

```
Nombre: <Nombre completo>
Pull Request: <Link del pull request>
```

No cumplir con el formato pedido de una Entrega Parcial podría llevar a no ser
considerada, y para una Entrega Final, tiene descuentos en su nota final.
