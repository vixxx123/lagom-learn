package com.codelouders.learn.lagom.lagomlearnstream.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.server._
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import play.api.libs.ws.ahc.AhcWSComponents
import com.codelouders.learn.lagom.lagomlearnstream.api.LagomlearnStreamService
import com.codelouders.learn.lagom.lagomlearn.api.LagomlearnService
import com.softwaremill.macwire._

class LagomlearnStreamLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new LagomlearnStreamApplication(context) {
      override def serviceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new LagomlearnStreamApplication(context) with LagomDevModeComponents
}

abstract class LagomlearnStreamApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents {

  // Bind the services that this server provides
  override lazy val lagomServer = LagomServer.forServices(
    bindService[LagomlearnStreamService].to(wire[LagomlearnStreamServiceImpl])
  )

  // Bind the LagomlearnService client
  lazy val lagomlearnService = serviceClient.implement[LagomlearnService]
}
