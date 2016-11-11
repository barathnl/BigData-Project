package com.pb.team24

import scala.collection.JavaConversions._
import scala.collection.convert.wrapAll._
import scala.collection.convert.decorateAll._
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.{StructType,StructField,StringType}
import org.apache.spark.sql.SparkSession
import scala.io.Source

object SparkSQLQueryRDD2 {
  def main(args: Array[String]) {
  val conf = new SparkConf().setAppName("SparkSQL").setMaster("local[*]")
  val sc = new SparkContext(conf)
  
  val spark = SparkSession
  .builder()
  .appName("SparkSQL")
  .config("spark.some.conif.option","some-value")
  .getOrCreate()
  
  val df=spark.read.json("src/com/pb/team24/resource/tweet_JSON_data.json")
  df.createOrReplaceTempView("MainTable")
  //Collecting the Tweeted Language
  val SQLquery = spark.sql("SELECT lang as Tweet_Language, COUNT(*) AS Language_Count FROM MainTable GROUP BY lang").rdd
  val rddData = SQLquery.map{case item:Row => 
    val language = item.getString(0)
    val count = item.getLong(1)
    
    (language,count)
  }
  val rddDataCollected=rddData.collect()
  rddDataCollected.foreach{case (language,count) => println("< "+language+" , "+count+" >") }
  }
}