package com.pb.team24

import java.lang.System._
import scala.collection.JavaConversions._
import scala.collection.convert.wrapAll._
import scala.collection.convert.decorateAll._
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.{StructType,StructField,StringType}
import scala.io.Source

object SparkSQLQueryDFF2 {
  def main(args: Array[String]) {
    println("****************************")
    println("Team 24 - PB Project Phase 2")
    println("****************************")
    
    setProperty("hadoop.home.dir", "c:\\Barath\\Software\\hadoop-2.6.2")
    
    val conf = new SparkConf().setAppName("SparkSQL").setMaster("local").set("com.spark.executor", "")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    
    //registering the tweets into table
    val dfs = sqlContext.read.json("src/com/pb/team24/resource/tweet_JSON_data.json")
    dfs.registerTempTable("MainTable")
    
    val hashTagTable = sqlContext.read.text("src/com/pb/team24/resource/hashTag.txt")
    hashTagTable.registerTempTable("HashTable")
    dfs.show()
    
      
    //val SQLquery = sqlContext.sql("SELECT text as Main_Text,entities.hashtags.text AS hashtag FROM MainTable")
    //val SQLquery = sqlContext.sql("SELECT value AS hashtag FROM HashTable where value='Dhoni'")
    val SQLquery = sqlContext.sql("SELECT text as Main_Text FROM MainTable INNER JOIN HashTable ON MainTable.entities.hashtags.text=HashTable.value")
    SQLquery.show()
          
         
    }
}