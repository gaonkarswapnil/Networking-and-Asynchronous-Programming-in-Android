package com.example.networkingandasynchronousprogramming.thread

fun main(){
    val thread = Thread{
        try{
            for(i in 1..5){
                Thread.sleep(1000)
                println("Task Executed")
            }
        }catch (e: InterruptedException){
            e.printStackTrace()
        }
    }


    thread.start()
}