package com.leandroiannotti.spark.SparkExample;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;

/**
 * Hello world!
 *
 */
public class CsvToJson 
{
    public static void main( String[] args )
    {
    	
        final SparkConf sparkConf = new SparkConf().setAppName("CsvToJson").setMaster("local");
        final SparkContext sparkContext = new SparkContext(sparkConf);
        final SparkSession sparkSession = new SparkSession(sparkContext);
        final SQLContext sqlContext = new SQLContext(sparkSession);
        
        
        //Change that path.
        final String path = "/home/leoian/eclipse-workspace/SparkExample/resources/bigdataexample.csv";
        
        Dataset<Row> datosLocacionesPedidos = sqlContext.read()
        		.format("com.databricks.spark.csv").option("inferSchema", true)
        		.option("header", true).option("delimiter", ";").load(path);
        
        
        datosLocacionesPedidos.printSchema();
        
        datosLocacionesPedidos.select("STORERKEY","LOT","LOC","ID","QTY");
        datosLocacionesPedidos = datosLocacionesPedidos.filter("STATUS == 'OK'");
        datosLocacionesPedidos = datosLocacionesPedidos.filter("ADDWHO == 'e_rarrua'");
        datosLocacionesPedidos = datosLocacionesPedidos.filter("QTY > '0' ");
        
        
        
        //datosLocacionesPedidos.createOrReplaceTempView("datosLocaciones");
        //datosLocacionesPedidos = sqlContext.sql("SELECT STORERKEY,SUM(QTY) FROM datosLocaciones WHERE STATUS == 'OK' AND ADDWHO = 'e_cretamal' GROUP BY STORERKEY ");
        
        
        //Change that path.
        datosLocacionesPedidos.write().json("/home/leoian/eclipse-workspace/SparkExample/resources/json");
        
        
        sparkSession.close();
    }
}
