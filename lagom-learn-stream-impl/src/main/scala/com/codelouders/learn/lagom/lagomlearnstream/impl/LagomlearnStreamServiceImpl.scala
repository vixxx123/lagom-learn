package com.codelouders.learn.lagom.lagomlearnstream.impl

import akka.stream.scaladsl.Keep
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.codelouders.learn.lagom.lagomlearnstream.api.LagomlearnStreamService
import com.codelouders.learn.lagom.lagomlearn.api.{GreetingMessage, LagomlearnService}

import scala.concurrent.Future

/**
  * Implementation of the LagomlearnStreamService.
  */
class LagomlearnStreamServiceImpl(lagomlearnService: LagomlearnService) extends LagomlearnStreamService {
  def stream = ServiceCall { hellos =>
    Future.successful(hellos.mapAsync(8){ command ⇒
      if (command.startsWith("change:")){
        val id = command.split(":")(1)
        lagomlearnService.useGreeting(id).invoke(request = GreetingMessage(id))
        Future.successful("Done")
      } else {
        lagomlearnService.hello(command).invoke()
      }
    })
  }
}
