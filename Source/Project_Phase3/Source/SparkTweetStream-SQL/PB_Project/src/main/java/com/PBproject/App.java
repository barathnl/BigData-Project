package com.PBproject;

import java.util.ArrayList;
import java.util.List;
// $example off:programmatic_schema$
// $example on:create_ds$
import java.util.Arrays;
import java.util.Collections;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
// $example off:create_ds$
import java.io.UnsupportedEncodingException;

import org.apache.spark.SparkConf;
// $example on:schema_inferring$
// $example on:programmatic_schema$
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
// $example off:programmatic_schema$
// $example on:create_ds$
import org.apache.spark.api.java.function.MapFunction;
// $example on:create_df$
// $example on:run_sql$
// $example on:programmatic_schema$
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
// $example off:programmatic_schema$
// $example off:create_df$
// $example off:run_sql$
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
// $example off:create_ds$
// $example off:schema_inferring$
import org.apache.spark.sql.RowFactory;
// $example on:init_session$
import org.apache.spark.sql.SparkSession;
// $example off:init_session$
// $example on:programmatic_schema$
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
// $example off:programmatic_schema$
import org.apache.spark.sql.AnalysisException;

// $example on:untyped_ops$
// col("...") is preferable to df.col("...")
import static org.apache.spark.sql.functions.col;

public class App 
{
    public static void main( String[] args ) throws AnalysisException
    {
        System.out.println( "PB Team 24" );
        SparkConf conf = new SparkConf().setAppName("cust data").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        SparkSession spark = SparkSession
        	      .builder()
        	      .appName("Java Spark SQL basic")
        	      .config("spark.some.config.option", "some-value")
        	      .getOrCreate();
        
        runQuery1(spark);
        runQuery2(spark);
        runQuery3(spark);
        runQuery4(spark);
        
        spark.stop();
    }
    private static void runQuery1(SparkSession spark) throws AnalysisException {
    	Dataset<Row> df = spark.read().json("./src/main/java/com/PBproject/tweet_JSON_data.json");

        // Register the DataFrame as a SQL temporary view
        df.createOrReplaceTempView("tweetTable");

        //SQL query to collect location of tweet
        Dataset<Row> sqlDF = spark.sql("SELECT user.time_zone as Location, COUNT(*) AS LoctionCount FROM tweetTable WHERE user.time_zone != 'null' GROUP BY user.time_zone ");
        List<String> Output=sqlDF.toJSON().collectAsList();
        String queryOutput =Output.toString();
        System.out.println(queryOutput);
        
        //writing output to text file 
        PrintWriter writer=null;
		try {
			writer = new PrintWriter("./src/main/java/com/PBproject/intermediateOutput/intermediateOutput3.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        writer.print(queryOutput);
        writer.close();
    }
    private static void runQuery2(SparkSession spark) throws AnalysisException {
        Dataset<Row> df = spark.read().json("./src/main/java/com/PBproject/tweet_JSON_data.json");

        // Register the DataFrame as a SQL temporary view
        df.createOrReplaceTempView("tweetTable");

        //SQL query to collect tweets language and its count
        Dataset<Row> sqlDF = spark.sql("SELECT lang as Tweet_Language, COUNT(*) AS Language_Count FROM tweetTable GROUP BY lang");
        List<String> Output=sqlDF.toJSON().collectAsList();
        String queryOutput =Output.toString();
        System.out.println(queryOutput);
        
        //writing output to text file 
        PrintWriter writer=null;
		try {
			writer = new PrintWriter("./src/main/java/com/PBproject/intermediateOutput/intermediateOutput1.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        writer.print(queryOutput);
        writer.close();
        
     }
    private static void runQuery3(SparkSession spark) throws AnalysisException {
        Dataset<Row> df = spark.read().json("./src/main/java/com/PBproject/tweet_JSON_data.json");

        // Register the DataFrame as a SQL temporary view
        df.createOrReplaceTempView("tweetTable");

        //SQL query to collect top retweet text and retweet count
        Dataset<Row> sqlDF = spark.sql("SELECT text as Main_Text,retweeted_status.text AS Retweet_Text,retweeted_status.retweet_count AS Retweet_Count FROM tweetTable WHERE retweeted_status.retweet_count IS NOT NULL ORDER BY retweeted_status.retweet_count DESC");
        List<String> Output=sqlDF.toJSON().collectAsList();
        String queryOutput =Output.toString();
        System.out.println(queryOutput);
        
        //writing output to text file 
        PrintWriter writer=null;
		try {
			writer = new PrintWriter("./src/main/java/com/PBproject/intermediateOutput/intermediateOutput2.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        writer.print(queryOutput);
        writer.close();
        
                
     }
    private static void runQuery4(SparkSession spark) throws AnalysisException {
        Dataset<Row> df = spark.read().json("./src/main/java/com/PBproject/tweet_JSON_data.json");

        // Register the DataFrame as a SQL temporary view
        df.createOrReplaceTempView("tweetTable");

        //SQL query to collect type of source device used for tweet
        Dataset<Row> sqlDF1 = spark.sql("SELECT count(*) as android FROM tweetTable Where source LIKE '%android%'");
        Dataset<Row> sqlDF2 = spark.sql("SELECT count(*) as iphone FROM tweetTable Where source LIKE '%iphone%'");
        Dataset<Row> sqlDF3 = spark.sql("SELECT count(*) as windows FROM tweetTable Where source LIKE '%window%'");
        Dataset<Row> sqlDF4 = spark.sql("SELECT count(*) as web FROM tweetTable Where source NOT LIKE '%android%' AND source NOT LIKE '%iphone%' AND source NOT LIKE '%window%'");
        
        String part1=sqlDF1.toJSON().collectAsList().toString();
        String part2=sqlDF2.toJSON().collectAsList().toString();
        String part3=sqlDF3.toJSON().collectAsList().toString();
        String part4=sqlDF4.toJSON().collectAsList().toString();
        
        String queryOutput = "{device1:"+part1+",device2:"+part2+",device3:"+part3+",device4:"+part4+"}";
        System.out.println(queryOutput);
        
        //writing output to text file 
        PrintWriter writer=null;
		try {
			writer = new PrintWriter("./src/main/java/com/PBproject/intermediateOutput/intermediateOutput4.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        writer.print(queryOutput);
        writer.close();
                                
     }

}
