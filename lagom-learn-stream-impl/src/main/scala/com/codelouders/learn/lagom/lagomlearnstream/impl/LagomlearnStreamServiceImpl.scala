package com.codelouders.learn.lagom.lagomlearnstream.impl

import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.codelouders.learn.lagom.lagomlearnstream.api.LagomlearnStreamService
import com.codelouders.learn.lagom.lagomlearn.api.LagomlearnService

import scala.concurrent.Future

/**
  * Implementation of the LagomlearnStreamService.
  */
class LagomlearnStreamServiceImpl(lagomlearnService: LagomlearnService) extends LagomlearnStreamService {
  def stream = ServiceCall { hellos =>
    Future.successful(hellos.mapAsync(8)(lagomlearnService.hello(_).invoke()))
  }
}
