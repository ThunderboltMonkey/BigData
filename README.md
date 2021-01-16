# BigData
INTRODUCCIÓN

Como ya se mencionó en el resumen este proyecto final surge como una especie de refuerzo de los conocimientos obtenidos a lo largo del curso. Se acordó al inicio del mismo, que la entrega de este proyecto final era indispensable para poder acreditar la materia, se establecieron los términos, requisitos y fecha de entrega.

El producto final debe ser el presente documento donde se explique a detalle la realización del proyecto, por otra parte debe generarse un video explicativo de no más de 10 minutos de duración donde se expliquen algunos puntos como la elección de los algoritmos, el proceso de prueba y comparación de los algoritmos, observaciones de los mismos y por último las conclusiones.


MARCO TEÓRICO

Como parte del marco teórico les hablaremos de las herramientas y algoritmos que utilizaremos para la realización del proyecto.

APACHE SPARK

Apache Spark combina un sistema de computación distribuida a través de clusters de ordenadores con una manera sencilla y elegante de escribir programas. Fue creado en la Universidad de Berkeley en California y es considerado el primer software de código abierto que hace la programación distribuida realmente accesible a los científicos de datos.  Es sencillo entender Spark si lo comparamos con su predecesor, MapReduce, el cual revolucionó la manera de trabajar con grandes conjuntos de datos ofreciendo un modelo relativamente simple para escribir programas que se podían ejecutar paralelamente en cientos y miles de máquinas al mismo tiempo. Gracias a su arquitectura, MapReduce logra prácticamente una relación lineal de escalabilidad, ya que si los datos crecen es posible añadir más máquinas y tardar lo mismo.

Apache Spark es una herramienta útil y eficiente para tareas de procesamiento masivo de datos. Está en constante desarrollo y se actualiza frecuentemente. Además, su documentación es muy completa y la comunidad cada vez se hace más grande.

MLLIB

MLlib es la biblioteca de aprendizaje automático (ML) de Spark. Su objetivo es hacer que el aprendizaje automático práctico sea escalable y fácil. A alto nivel, proporciona herramientas como:

Algoritmos ML: algoritmos de aprendizaje comunes como clasificación, regresión, agrupación y filtrado colaborativo
Caracterización: extracción, transformación, reducción de dimensionalidad y selección de características
Pipelines: herramientas para construir, evaluar y ajustar ML Pipelines
Persistencia: guardar y cargar algoritmos, modelos y canalizaciones
Utilidades: álgebra lineal, estadística, manejo de datos, etc.


ÁRBOLES DE DECISIÓN

Los árboles de decisión y sus conjuntos son métodos populares para las tareas de clasificación y regresión de aprendizaje automático. Los árboles de decisión se utilizan ampliamente ya que son fáciles de interpretar, manejan características categóricas, se extienden a la configuración de clasificación multiclase, no requieren escalado de características y pueden capturar no linealidades e interacciones de características. 
Los algoritmos de conjuntos de árboles, como los bosques aleatorios y el impulso, se encuentran entre los mejores para las tareas de clasificación y regresión.

La implementación de spark.ml admite árboles de decisión para la clasificación binaria y multiclase y para la regresión, utilizando características continuas y categóricas. La implementación divide los datos por filas, lo que permite un entrenamiento distribuido con millones o incluso miles de millones de instancias.


K-MEANS

k-means es uno de los algoritmos de agrupación más utilizados que agrupa los puntos de datos en un número predefinido de agrupaciones. La implementación de MLlib incluye una variante paralelizada del método k-means ++ llamada kmeans ||.

KMeans se implementa como un Estimador y genera un KMeansModel como modelo base.

K-means es un algoritmo de clasificación no supervisada (clusterización) que agrupa objetos en k grupos basándose en sus características. El agrupamiento se realiza minimizando la suma de distancias entre cada objeto y el centroide de su grupo o cluster. Se suele usar la distancia cuadrática.

El algoritmo consta de tres pasos:
Inicialización: una vez escogido el número de grupos, k, se establecen k centroides en el espacio de los datos, por ejemplo, aleatoriamente.
Asignación objetos a los centroides: cada objeto de los datos es asignado a su centroide más cercano.
Actualización centroides: se actualiza la posición del centroide de cada grupo tomando como nuevo centroide la posición del promedio de los objetos pertenecientes a dicho grupo.

Se repiten los pasos 2 y 3 hasta que los centroides no se mueven, o se mueven por debajo de una distancia umbral en cada paso.


MÁQUINAS DE VECTORES DE SOPORTE

Las máquinas de vector soporte se fundamentan en el clasificador de margen máximo, que a su vez, se basa en el concepto de hiperplano. Una máquina de vectores de soporte construye un hiperplano o un conjunto de hiperplanos en un espacio de dimensión alta o infinita, que se puede utilizar para clasificación, regresión u otras tareas. Intuitivamente, se logra una buena separación por el hiperplano que tiene la mayor distancia a los puntos de datos de entrenamiento más cercanos de cualquier clase (el llamado margen funcional), ya que en general, cuanto mayor es el margen, menor es el error de generalización del clasificador. La máquina de vectores de soporte lineal en Spark ML admite la clasificación binaria con máquinas de vectores de soportes lineal. Internamente, optimiza la pérdida de bisagra utilizando el optimizador OWLQN (Orthant-Wise Limited-memory Quasi-Newton).
DESARROLLO

Para el desarrollo del proyecto se eligió hacer la comparativa entre los tres siguientes algoritmos, cada uno fue probado 10 veces y se midió el tiempo transcurrido:

ALGORITMO 1: Árbol de decisión

Código:

// Sesión de Spark.
val spark = SparkSession.builder.appName("DecisionTree").getOrCreate()
 
// Leyendo el archivo csv.
val df  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("C:/Users/jonat/Desktop/iris-master/iris-master/iris.csv")
 
//Indexación.
val labelIndexer = new StringIndexer().setInputCol("species").setOutputCol("indexedLabel").fit(df)
val indexed = labelIndexer.transform(df).drop("species").withColumnRenamed("indexedLabel", "label")
 
// Vector de las columnas de categoría numérica.
val vectorFeatures = (new VectorAssembler().setInputCols(Array("sepal_length","sepal_width","petal_length","petal_width")).setOutputCol("features"))
 
// Transformando el valor indexado.
val features = vectorFeatures.transform(indexed)
 
// Renombrar la columna y como etiqueta.
val featuresLabel = features.withColumnRenamed("species", "label")
 
// Unión de etiqueta y características como dataIndexed.
val dataIndexed = featuresLabel.select("label","features")
 
// Creación de labelIndexer y featureIndexer para la canalización, donde las entidades con valores distintos> 4, se tratan como continuas.
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(dataIndexed)
val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(dataIndexed)
 
////////////////////////////////////
val t1 = System.nanoTime
/* Código para inspeccionar el tiempo transcurrido */
////////////////////////////////////
//*****************************************************************************
// Datos de entrenamiento como 70% y datos de prueba como 30%.
val Array(trainingData, testData) = dataIndexed.randomSplit(Array(0.7, 0.3))
 
// Creación del objeto Árbol de decisión.
val decisionTree = new DecisionTreeClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures")
 
// Creando el objeto Index to String.
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels)
 
// Creando el pipeline con los objetos creados antes.
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, decisionTree, labelConverter))
 
// Ajuste del modelo con datos de entrenamiento.
val model = pipeline.fit(trainingData)
 
// Hacer las predicciones transformando el testData.
val predictions = model.transform(testData)
 
// Mostrando las predicciones.
predictions.select("predictedLabel", "label", "features").show(5)
 
// Creando el evaluador.
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")
 
//Exactitud.
val accuracy = evaluator.evaluate(predictions)
 
//****************************************************************
// Código finalizado, evaluar el tiempo
val duration = (System.nanoTime - t1) / 1e9d
//
// Exactitud y error de prueba.
println(s"Accuracy: ${(accuracy)}")
println(s"Test Error: ${(1.0 - accuracy)}")

ALGORITMO 2: K-means

Código:

//*********************************
//K MEANS
//*********************************
 
// Crea una sesión Spark
val spark = SparkSession.builder.getOrCreate()
 
// Cargue el conjunto de datos: "Datos completos de clientes"
val dataset = spark.read.option("header","true").option("inferSchema", "true").csv("C:/Users/jonat/Desktop/iris - iris.csv")
 
// Seleccione las siguientes columnas: Fresh, Milk, Grocery, Frozen, Detergents_Paper, Delicassen; y el nombre del conjunto como "feature_data"
val  feature_data  = dataset.select("sepal_length","sepal_width","petal_length","petal_width","species")
feature_data.show(2)
 
// Importar las bibliotecas VectorAssembler y Vector
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vector
 
// Cree un nuevo objeto Ensamblador de vectores para las columnas de características como un conjunto de entrada, teniendo en cuenta que no hay etiquetas
val assembler = new VectorAssembler().setInputCols(Array("sepal_length","sepal_width","petal_length","petal_width","species")).setOutputCol("features")
 
////////////////////////////////////
val t1 = System.nanoTime
/ * Código para inspeccionar el tiempo transcurrido * /
////////////////////////////////////
//*****************************************************************************
 
// Usa el ensamblador para transformar el conjunto "feature_data"
val  features = assembler.transform(feature_data)
 
// Crea el modelo KMEANS con k = 3
val kmeans = new KMeans().setK(3).setSeed(1L)
val model = kmeans.fit(features)
 
// Evaluar los clusters usando la suma de errores cuadrados dentro del conjunto e imprimir los centroides
val WSSSE = model.computeCost(features)
//****************************************************************
// Código finalizado, evaluar el tiempo
val duration = (System.nanoTime - t1) / 1e9d
//
 
println(s"Within set sum of Squared Errors = $WSSSE")
 
// Muestra los centroides.
println("Cluster Centers: ")
model.clusterCenters.foreach(println)

ALGORITMO 3: Máquinas de vectores de soporte

Código:

//********************************************
//SVM
//********************************************
 
// Sesión de Spark.
val spark = SparkSession.builder.appName("SVM").getOrCreate()
 
// Leyendo el archivo csv.
val df  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("C:/Users/jonat/Desktop/iris-master/iris-master/iris.csv")
 
//Indexación.
val labelIndexer = new StringIndexer().setInputCol("species").setOutputCol("indexedLabel").fit(df)
val indexed = labelIndexer.transform(df).drop("species").withColumnRenamed("indexedLabel", "label")
 
// Vector de las columnas de categoría numérica.
val vectorFeatures = (new VectorAssembler().setInputCols(Array("sepal_length","sepal_width","petal_length","petal_width")).setOutputCol("features"))
 
// Transformando el valor indexado.
val features = vectorFeatures.transform(indexed)
 
// Renombrar la columna species como etiqueta.
val featuresLabel = features.withColumnRenamed("species", "label")
 
// Unión de etiqueta y características como dataIndexed.
val dataIndexed = featuresLabel.select("label","features")
 
// Creación de labelIndexer y featureIndexer para la canalización, donde las entidades con valores distintos> 4, se tratan como continuas.
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(dataIndexed)
val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(dataIndexed)
 
////////////////////////////////////
val t1 = System.nanoTime
/ * Código para inspeccionar el tiempo transcurrido * /
////////////////////////////////////
//*****************************************************************************
 
// Datos de entrenamiento como 70% y datos de prueba como 30%.
val Array(training, test) = dataIndexed.randomSplit(Array(0.7, 0.3))
 
// Objeto Linear Support Vector Machine.
val supportVM = new LinearSVC().setMaxIter(10).setRegParam(0.1)
    
// Ajuste de trainingData en el modelo.
val model = supportVM.fit(trainingData)
 
// Transformando testData para las predicciones.
val predictions = model.transform(testData)
 
// Obtención de las métricas.
val predictionAndLabels = predictions.select($"prediction",$"label").as[(Double, Double)].rdd
val metrics = new MulticlassMetrics(predictionAndLabels)
 
//****************************************************************
// Código finalizado, evaluar el tiempo
val duration = (System.nanoTime - t1) / 1e9d
//
 
//Matriz de confusión.
println("Confusion matrix:")
println(metrics.confusionMatrix)
 
// Exactitud y error de prueba.
println("Accuracy: " + metrics.accuracy) 
println(s"Test Error = ${(1.0 - metrics.accuracy)}")
 
 

TABLAS COMPARATIVAS

Árbol de decisión
Iteración
Tiempo de operación
Precisión
Porcentaje de error
Número 1
28.5263376
0.958334
0.041663
Número 2
20.4485362
0.906976
0.093823
Número 3
11.5558438
0.90
0.099998
Número 4
13.4832505
0.9215686
0.0784313
Número 5
10.1979552
0.9615384
0.038461
Número 6
9.4949682
0.910714
0.089285
Número 7
9.4414784
0.948717
0.0512820
Número 8
9.0186761
0.9791666
0.0208333
Número 9
7.9300112
0.9433962
0.0566037
Número 10
7.699765
0.9069767
0.09302325
Promedio
12,7796822
0,9337388
0,0663404

K-Means
Iteración
Tiempo de operación
Precisión
Porcentaje de error
Número 1
4.798856
0.95517502841
N/D
Número 2
7.7968926
0.95517502841
N/D
Número 3
21.1646744
0.95517502841
N/D
Número 4
12.6549279
0.95517502841
N/D
Número 5
8.7740196
0.95517502841
N/D
Número 6
7.8807497
0.95517502841
N/D
Número 7
9.1715922
0.95517502841
N/D
Número 8
9.2965151
0.95517502841
N/D
Número 9
6.3101421
0.95517502841
N/D
Número 10
7.728929
0.95517502841
N/D
Promedio
9,5577299
0.95517502841
N/D

Máquinas de Soporte de Vectores
Iteración
Tiempo de operación
Precisión
Porcentaje de error
Número 1
13.5191042
0.80
0.199996
Número 2
8.129923
0.80
0.199996
Número 3
7.5622892
0.80
0.199996
Número 4
6.8572732
0.80
0.199996
Número 5
6.2705583
0.80
0.199996
Número 6
5.745827
0.80
0.199996
Número 7
5.7166136
0.80
0.199996
Número 8
5.6281809
0.80
0.199996
Número 9
5.9922737
0.80
0.199996
Número 10
2.2126733
0.80
0.199996
Promedio
6,76
0.80
0,199996

Tabla Comparativa Final
Algoritmo
Tiempo de operación
Precisión
Porcentaje de error
Arbol de decision
12,7796822 s
93.3%
6.6%
K-Means
9,5577299 s
95.5%
N/D
Máquinas de Soporte de Vectores
6,76 s
80%
19.99%


CONCLUSIONES

Tenemos tres tipos de algoritmos en comparación para el mismo set de datos, uno por nodos, el segundo por clusters y el tercero por vectores, cabe destacar que cada algoritmo se lleva su mérito en una de las 3 categorías que son:

Tiempo de operación (promedio).
Precisión.
Y porcentaje de error.

Las observaciones que obtuvimos para el tiempo de operación:

A pesar de que el algoritmo de árbol de decisión tuvo una buena respuesta en cuanto a reducción del tiempo y se creyó que sería el que respondería mejor en el menor tiempo, nos llevamos la sorpresa de que el algoritmo de máquinas de vectores de soporte tuvo la ventaja en cuanto a tiempo con un promedio de 6.76 segundos, el menor tiempo que obtuvo para el procesado de datos fue de 2.21 segundos lo cual es demasiado rápido.

Las observaciones que obtuvimos para la precisión:

En este caso el algoritmo de k-means tuvo una muy buena respuesta al obtener un promedio de 95.5% de precisión en el procesado de los datos.

Las observaciones que obtuvimos para el porcentaje de error:

Esta prueba también estuvo a favor del algoritmo de árbol de decisión el cual tuvo solo el 6% de error en promedio, siendo un 2% el menor porcentaje de error obtenido. En este caso el nivel de error en el algoritmo de k-means es despreciable.


Entonces, en las circunstancias en que fue desarrollado el proyecto, con los algoritmos comparados y con el set de datos utilizado, podemos concluir que el algoritmo de árbol de decisión fue el que mejor proceso los datos y el cual es el más confiable al obtener un mayor grado de precisión y menor porcentaje de error.
