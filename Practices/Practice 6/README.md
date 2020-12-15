# Practice 6

## Tenemos que importar las siguientes bibliotecas
  import org.apache.spark.ml.classification.{LogisticRegression, OneVsRest}
  import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

## Importando nuestros datos
var inputData = spark.read.format("libsvm").load("/opt/spark/data/mllib/sample_multiclass_classification_data.txt")

## Tenemos que dividir los datos en el conjunto de entrenamiento (80%) y prueba (20%)
val Array(train, test) = inputData.randomSplit(Array(0.8, 0.2))

## Crea nuestro clasificador
val classifier = new LogisticRegression().setMaxIter(10).setTol(1E-6).setFitIntercept(true)

## Crea nuestro clasificador OneVsRest
val ovr = new OneVsRest().setClassifier(classifier)

## Entrena el modelo con los datos de entrenamiento
val ovrModel = ovr.fit(train)

## Haz nuestras predicciones con el conjunto de pruebas en el modelo
val predictions = ovrModel.transform(test)

## Crea nuestro evaluador
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")

## Calcula la precisión de nuestro modelo
val accuracy = evaluator.evaluate(predictions)

## Y finalmente imprime el resultado
println(s"Test Error = ${1 - accuracy}")

