# Tarea 0 | Entrega Final

La idea de esta primera entrega es que se familiaricen con el lenguaje Scala, así como el manejo de Git, resolviendo un problema de programación sencillo.

## Problema: Encontrar el Máximo Común Divisor (mcd) entre dos números dados.

El **Máximo Común Divisor (MCD)** de dos números enteros es el mayor número entero positivo que divide a ambos sin dejar residuo. Por ejemplo, el MCD de 12 y 8 es 4, ya que 4 es el mayor número que divide tanto a 12 como a 8.

Para calcularlo, utilizaremos el **algoritmo de Euclides**, que se basa en la siguiente observación:

> mcd(a, b) = mcd(b, a mod b)

Es decir, el MCD de dos números es igual al MCD del segundo número y el resto de dividir el primero por el segundo. Cuando el resto es 0, el MCD es el último divisor no nulo.

**Ejemplo paso a paso:** mcd(12, 8)
- mcd(12, 8) → mcd(8, 12 mod 8) = mcd(8, 4)
- mcd(8, 4) → mcd(4, 8 mod 4) = mcd(4, 0)
- Como el segundo valor es 0, el MCD es **4**.

### Comportamiento esperado

El programa debe ser **interactivo**: pedirle al usuario dos números, calcular el MCD entre ellos utilizando el algoritmo de Euclides, mostrar el resultado, y repetir el proceso. El programa termina cuando el usuario ingresa **0** como primer número.

### Ejemplo de ejecución

```
Ingrese el primer número (0 para salir): 12
Ingrese el segundo número: 8
El MCD de 12 y 8 es 4

Ingrese el primer número (0 para salir): 15
Ingrese el segundo número: 5
El MCD de 15 y 5 es 5

Ingrese el primer número (0 para salir): 0
Adiós!
```

## Git

Para trabajar en esta tarea, deben crear una rama llamada `entrega-final-0` a partir de `main`

Una vez que hayan terminado de implementar su solución, hagan commit y push de sus cambios en esa rama

## Instrucciones de implementación

El archivo que deben editar es:

```
src/main/scala/mcd/euclides.scala
```

Deben escribir su código **entre los comentarios de inicio y fin** que encontrarán en el archivo:

```scala
// Inicio de la zona donde deben editar el código

// (su código va aquí)

// Fin de la zona donde deben editar el código
```

Para ejecutar el programa y probar su solución, utilicen:

```bash
sbt run
```

## Entrega

