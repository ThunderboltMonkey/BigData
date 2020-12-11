// Importamos las siguientes dos bibliotecas que son necesarias para utilizar el MLPC
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

// Importamos la siguiente biblioteca que nos permitirá realizar el etiquetado correspondiente
import org.apache.spark.ml.feature.StringIndexer 

// Importamos la siguiente biblioteca que vamos a necesitar para unir todas las columnas en un solo vector
import org.apache.spark.ml.feature.VectorAssembler

// Despues iniciamos nuestra sesión de spark
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()

// Importamos nuestros datos y los guardamos en el dataframe "df"
 val df = spark.read.option("header", "true").option("inferSchema","true")csv("C:/Users/Monkey/Desktop/iris.csv")

// Imprimimos los nombres de las columnas
df.columns

// Imprimimos el esquema de los datos
df.printSchema()

// Imprimimos las primeras 5 columnas
df.columns.take(5)  

// Usamo el método describe para aprender más sobre los datos
df.describe().show

// Usamos el método StringIndexer para transformar una columna de cadenas de etiquetas en una columna de índices de etiquetas
// En este caso la columna que vamos a transformar es la columna "species" y le vamos a asignar el nombre de "indexedLabel" a
// la nueva columna, por último le vamos a ajustar los datos del dataframe "df"
val labelIndexer = new StringIndexer().setInputCol("species").setOutputCol("indexedLabel").fit(df)

// Ahora lo que haremos es sustituir la columna "species" con nuestra columna "indexedLabel" y la vamos a mostrar con el
// nombre de "label"
val indexed = labelIndexer.transform(df).drop("species").withColumnRenamed("indexedLabel", "label")
// Utilizamos los métodos "describe" y "show" para visualizar la nueva estructura con los cambios ya mencionados
indexed.describe().show()

// Usamos el método VectorAssembler para unir varias columnas en un vector
// Nuestro assembler toma las columnas del dataset y las transforma en el vector llamado "features"
val assembler = new VectorAssembler().setInputCols(Array("sepal_length","sepal_width","petal_length","petal_width")).setOutputCol("features")
// Entonces creamos nuestro valor de features y con la función transform de nuestro assembler transformamos nuestro set de datos "indexed"
// que recordemos es el que ya está procesado por así decirlo
val  features = assembler.transform(indexed)

// Usamos el método StringIndexed nuevamente para pasarle la columna "label" y obtener la columna "indexedLabel"
// por último le ajustamos los datos procesados del dataframe "indexed"
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(indexed)
// Imprimimos las etiquetas que fueron encontradas
println(s"Found labels: ${labelIndexer.labels.mkString("[", ", ", "]")}")
// Imprimimos nuestro dataframe y podemos observar que la última columna es el conjunto de todas las columnas anteriores
features.show

// Separamos nuestro dataset un 70% en datos de entrenamiento y el 30% en datos de prueba, establecemos la semilla de aleatoriedad
val splits = features.randomSplit(Array(0.7, 0.3), seed = 1234L)
val train = splits(0)
val test = splits(1)

// Establecemos nuestras capas para la red neuronal, de tamaño 4 para la capa de entrada (features), dos en la capa intermedia (hidden layer) de tamaño 5 y 4,
// por último nuestra capa de salida de tamaño 3 (classes)
val layers = Array[Int](4, 5, 4, 3)

// Usamos el método "MultilayerPerceptronClassifier" para crear nuestro entrenador y le pasamos sus respectivos parámetros como
// las capas, el tamaño del bloque, la semilla de aleatoriedad y el máximo de iteraciones
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)

// Entrenamos el modelo con nuestro entrenador y le pasamos los datos de entrenamiento (70%)
val model = trainer.fit(train)

// Ponemos a prueba nuestro modelo con los datos de prueba (30%)
val result = model.transform(test)

// Hacemos nuestra predicción y la mostramos
val predictionAndLabels = result.select("prediction", "label")
predictionAndLabels.show

// Evaluamos la precisión de nuestro modelo y por último imprimimos dicho valor
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")
println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")