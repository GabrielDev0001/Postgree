package com.ti2cc;
import static spark.Spark.*;

public class HelloWorld{
	public static void main(String[] arg){
		port(4568);
		get("/hello",(request,response)->"Hello World!");
	}
}