package com.codelouders.learn.lagom.lagomlearn.impl

import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.persistence.PersistentEntityRegistry
import com.codelouders.learn.lagom.lagomlearn.api.LagomlearnService

/**
  * Implementation of the LagomlearnService.
  */
class LagomlearnServiceImpl(persistentEntityRegistry: PersistentEntityRegistry) extends LagomlearnService {

  override def hello(id: String) = ServiceCall { _ =>
    // Look up the lagom-learn entity for the given ID.
    val ref = persistentEntityRegistry.refFor[LagomlearnEntity]("greet")
    // Ask the entity the Hello command.
    ref.ask(Hello(id, None))
  }

  override def useGreeting(id: String) = ServiceCall { request =>
    // Look up the lagom-learn entity for the given ID.
    val ref = persistentEntityRegistry.refFor[LagomlearnEntity]("greet")

    // Tell the entity to use the greeting message specified.
    ref.ask(UseGreetingMessage(request.message))
  }
}
