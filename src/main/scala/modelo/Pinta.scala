package cl.uchile.dcc
package modelo

abstract class Pinta(val tipo: String)

class Trebol extends Pinta("Trebol") 
class Pica extends Pinta("Pica")
class Corazon extends Pinta("Corazon")
class Diamante extends Pinta("Diamante")