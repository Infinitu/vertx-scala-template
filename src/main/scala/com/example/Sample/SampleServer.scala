package com.example.Sample

import org.vertx.scala.platform.Verticle


class SampleServer extends Verticle
{
  override def start(){
    vertx.createHttpServer().requestHandler(_.response().end("Hello Mod-Lang-Scala!")) listen 8080
  }
}