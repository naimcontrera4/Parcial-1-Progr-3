# Anexo Técnico de Rendimiento - ECORIDE PRO

A continuación, se detallan las justificaciones técnicas y algorítmicas aplicadas para resolver los problemas de escalabilidad y rendimiento detectados en la plataforma.

### 1. ¿Por qué la nueva estructura de búsqueda es más rápida que la lineal?
En la versión inicial, los vehículos se almacenaban en una lista tradicional (`ArrayList`). Para encontrar un monopatín por su patente, el procesador debía recorrer la colección elemento por elemento. Esto significaba que, con miles de vehículos en la calle, el algoritmo tenía un rendimiento de **O(n)** (tiempo de búsqueda lineal), lo que generaba cuellos de botella.

Para solucionarlo, migramos la estructura de almacenamiento de la `EstacionAnclaje` a un mapa (`HashMap`). Al utilizar la "patente" como clave única (`Key`), aprovechamos la función de hash interna de Java. Ahora, el sistema calcula la dirección de memoria exacta basándose en la patente y accede al vehículo de manera directa. Esto reduce drásticamente el costo computacional, logrando un tiempo de acceso instantáneo y constante de **O(1)**, sin importar el volumen de la flota.

### 2. Evitando bucles anidados en la deduplicación del GPS
El enfoque ineficiente para eliminar duplicados hubiera requerido comparar cada reporte contra todos los demás usando un `for` dentro de otro `for`. Esto dispara la complejidad temporal a **O(n²)**, saturando por completo la CPU del servidor frente a miles de señales desordenadas.

Para evitar este sobrecosto, diseñamos el algoritmo `GpsDeduplicador` apoyándonos en una estructura de conjunto (`HashSet`). Los conjuntos matemáticos en Java no permiten elementos repetidos y verifican colisiones en tiempo constante O(1). 
Mediante un único bucle secuencial, intentamos insertar cada alerta en el Set; si el método `add()` nos devuelve verdadero (el dato es nuevo), lo guardamos en la lista limpia. Así, con **una sola pasada O(n)**, depuramos toda la colección sin hacer iteraciones internas.

### 3. Convivencia del ordenamiento natural y el ordenamiento por tarifas
Teníamos el desafío de ordenar la misma lista de vehículos bajo dos lógicas totalmente distintas (Carga de Batería vs. Tarifa Base) sin romper el encapsulamiento del modelo ni duplicar datos en memoria.

Lo resolvimos aplicando dos herramientas nativas de Java orientadas a objetos:
* **Ordenamiento Natural (Criterio Intrínseco):** Hicimos que la clase `Vehiculo` implementara la interfaz `Comparable`. Sobrescribimos el método `compareTo` para que el vehículo sepa cómo compararse a sí mismo frente a otro según su porcentaje de batería. Cuando el taller solicita la lista, un simple `Collections.sort()` activa este comportamiento por defecto.
* **Ordenamiento Externo (Criterio Concurrente):** Para no alterar el ADN del vehículo cuando administración pidió ordenar por precios, inyectamos el patrón Strategy a nivel de colección. Creamos la clase `TarifaBaseComparator` que implementa `Comparator`. Al enviarle esta clase por parámetro al ordenamiento (`Collections.sort(lista, comparadorExterno)`), forzamos dinámicamente un cambio de regla en memoria. De esta forma, ambas listas coexisten y se generan al instante sin interferir entre sí.