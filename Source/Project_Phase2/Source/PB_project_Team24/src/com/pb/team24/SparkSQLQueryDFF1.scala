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

object SparkSQLQueries {
  def main(args: Array[String]) {
    println("****************************")
    println("Team 24 - PB Project Phase 2")
    println("****************************")
    
    setProperty("hadoop.home.dir", "c:\\Barath\\Software\\hadoop-2.6.2")
    
    
    
    val conf = new SparkConf().setAppName("SparkSQL").setMaster("local").set("com.spark.executor", "")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    //import sqlContext.implicits._
    
    val dfs = sqlContext.read.json("src/com/pb/team24/resource/tweet_JSON_data.json")
    dfs.registerTempTable("MainTable")
    //dfs.printSchema();
    //dfs.show()
    // registering the tweets into table
    
   
    println("************ Query 3 ****************")
    val SQLquery = sqlContext.sql("SELECT text as Main_Text,retweeted_status.text AS Retweet_Text,retweeted_status.retweet_count AS Retweet_Count FROM MainTable WHERE retweeted_status.retweet_count IS NOT NULL ORDER BY retweeted_status.retweet_count DESC")
    SQLquery.show()
    
    /*println("************ Query 4 ****************")
    val SQLquery4 = sqlContext.sql("select user.name AS Name,user.screen_name AS Screen_Name,user.followers_count,user.friends_count  FROM MainTable ORDER BY user.followers_count DESC LIMIT 5")
    SQLquery4.show()*/
   
         
    }
}