package com.pb.team24

import java.lang.System._
import scala.collection.JavaConversions._
import scala.collection.convert.wrapAll._
import scala.collection.convert.decorateAll._
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.{StructType,StructField,StringType}
import org.apache.spark.sql.SparkSession
import scala.io.Source

object SparkSQLQueriesRDD {
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
  //Location from where Tweets are tweeted
  val SQLquery = spark.sql("SELECT user.time_zone as Location, COUNT(*) AS LoctionCount FROM MainTable WHERE user.time_zone != 'null' GROUP BY user.time_zone ").rdd
  val rddData = SQLquery.map{case item:Row => 
    val location = item.getString(0)
    val count = item.getLong(1)
    
    (location,count)
  }
  val rddDataCollected=rddData.collect()
  rddDataCollected.foreach{case (location,count) => println("< "+location+" , "+count+" >") }
  }
}