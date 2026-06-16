# Entrega Parcial 2

## Contexto y Relación con Entregas

Esta es la segunda entrega del proyecto Malatro. En EP1 modelaste las entidades básicas (Carta, Rango, Pinta, etc.). Ahora, tu objetivo es implementar la lógica para validar y representar las **combinaciones de póker** definidas en el enunciado principal.

**Recuerda:** Debes testear todas las funcionalidades que implementes (ver sección de Testing).

## Objetivo

Implementar la estructura y lógica para validar y representar las 6 combinaciones de póker del proyecto. Cada combinación debe poder verificarse sobre un listado de cartas y exponer su puntaje base.

## Combinaciones de póker (Sección 2.5)

Existen 6 combinaciones de póker:

- **Escalera de color (Straight Flush):** Una escalera de cinco cartas consecutivas de la misma pinta. Puntaje: 100 chips, multiplicador x8.
- **Color (Flush):** Cinco cartas de la misma pinta, sin importar el orden. Puntaje: 35 chips, multiplicador x4.
- **Escalera (Straight):** Cinco cartas en orden consecutivo, sin importar la pinta. Puntaje: 30 chips, multiplicador x4.
- **Trío (Three of a Kind):** Tres cartas del mismo rango. Puntaje: 30 chips, multiplicador x3.
- **Par (Pair):** Dos cartas del mismo rango. Puntaje: 10 chips, multiplicador x2.
- **Carta Alta (High Card):** Cualquier mano que no cumpla con las combinaciones anteriores. Solo cuenta la carta de mayor valor. Puntaje: 5 chips, multiplicador x1.

Cada combinación debe:
1. Exponer su puntaje base (chips y multiplicador).
2. Validar un listado de cartas mediante un método que retorne `Boolean` (true si cumple, false si no).
	- Entrada: lista de 1 a 5 cartas.

Si una jugada cumple más de una combinación, **debe considerarse únicamente la de mayor prioridad** según el orden listado arriba.

### Consideraciones técnicas
- El As tiene valor 11, pero para efectos de escalera puede actuar como 1 o como 14.
- La validación de combinaciones debe ser extensible y clara, pero no es necesario usar patrones de diseño avanzados (solo OOP básico).

## Testing

Se espera que incluyas una estructura básica de testing para las funcionalidades implementadas. Puedes refinar y ampliar los tests en futuras entregas.

Incluye pruebas para:
1. Validación básica de cada combinación.
2. Casos simples de conflicto (una mano que cumple más de una combinación, debe elegirse la de mayor prioridad).
3. Casos con As en escalera (A-2-3-4-5 y 10-J-Q-K-A).

No se exige cobertura exhaustiva ni casos de borde avanzados en esta etapa.

## Git

El trabajo realizado para esta entrega se debe realizar en una nueva rama llamada `entrega-parcial-2`:

1. Asegúrate de estar en la rama más reciente de tu trabajo. Puedes usar:
	```
	git checkout <last_branch>
	```
	donde `<last_branch>` es la rama de tu última entrega.
2. Crea la nueva rama:
	```
	git checkout -b entrega-parcial-2
	```
3. Trabaja y haz commits en esa rama.

*Ten en cuenta que el cuerpo docente tiene acceso total a tu repositorio.*

## Entrega

Para subir tu Entrega Parcial, debes crear un *pull request* desde la rama `entrega-parcial-2` a la rama `main` llamado `Tarea 1 - Entrega Parcial 2`.

Es importante que **no hagas merge** de la rama `entrega-parcial-2` a la rama `main` para que el cuerpo docente pueda revisar tu *pull request*.

Por *U-Cursos* debes entregar un único archivo llamado `entrega-parcial-2.txt` con el siguiente contenido:

```
Nombre: <Nombre completo>
Pull Request: <Link del pull request>
```

No cumplir con el formato pedido de una Entrega Parcial podría llevar a no ser considerada, y para una Entrega Final, tiene descuentos en la nota final.
