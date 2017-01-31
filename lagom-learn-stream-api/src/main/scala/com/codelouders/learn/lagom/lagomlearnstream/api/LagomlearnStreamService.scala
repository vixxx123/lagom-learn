package com.codelouders.learn.lagom.lagomlearnstream.api

import akka.NotUsed
import akka.stream.scaladsl.Source
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}

/**
  * The lagom-learn stream interface.
  *
  * This describes everything that Lagom needs to know about how to serve and
  * consume the LagomlearnStream service.
  */
trait LagomlearnStreamService extends Service {

  def stream: ServiceCall[Source[String, NotUsed], Source[String, NotUsed]]

  override final def descriptor = {
    import Service._

    named("lagom-learn-stream")
      .withCalls(
        namedCall("stream", stream)
      ).withAutoAcl(true)
  }
}

