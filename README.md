#  calls-manager

Ejercicio de manejo de llamadas entre operadores, supervisores y directores para Al Mundo

-El Dispatcher es la clase encargada de disparar las llamadas
-QueueManager se encarga del manejo de la disponibilidad y orden de asignación de los operadores
-Director, Supervisor y Operator dependen de la clase abstracta Worker y cada uno tiene un comportamiento
propio para distinguirlos. Se utiliza el objeto instanciado para definir la prioridad de asignación
-OnHoldCall es un wrapper para las llamadas en espera
-OnHoldQueue es la clase encargada del manejo de las llamadas en espera y observadora de
Dispatcher para detectar cuando hay un operador libre
-Se crearon 4 test unitarios, en su mayoría para probar varias concurrencias

Para los ejercicios adicionales:
-Se crea una cola de espera para cuando se superan las 10 llamadas. Las mismas se iran liberando
en orden a medida que se liberan operadores