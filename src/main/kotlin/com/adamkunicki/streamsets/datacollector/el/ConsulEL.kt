package com.adamkunicki.streamsets.datacollector.el

import com.google.common.base.Joiner
import com.orbitz.consul.Consul
import com.streamsets.pipeline.api.ElDef
import com.streamsets.pipeline.api.ElFunction
import com.streamsets.pipeline.api.ElParam

@ElDef
class ConsulEL {
  companion object Constants {
    private const val CONSUL = "consul"
  }

  private val JOINER: Joiner = Joiner.on(",")

  @ElFunction(
      prefix = Constants.CONSUL,
      name = "getHealthyInstances",
      description = "Returns a comma-separated list of up to N healthy instances for the specified service."
  )
  fun getHealthyInstances(
      @ElParam("consulUrl") consulUrl: String,
      @ElParam("serviceName") serviceName: String,
      @ElParam("maxInstances") maxInstances: Int
  ): String? {
    val consul = Consul.builder().withUrl(consulUrl).build()
    val healthClient = consul.healthClient()
    val nodes = healthClient.getHealthyServiceInstances(serviceName).response

    if (nodes.isEmpty()) {
      return null
    }

    return JOINER.join(nodes.subList(0, Math.min(maxInstances, nodes.size) - 1))
  }

  @ElFunction(
      prefix = Constants.CONSUL,
      name = "get",
      description = "Fetches the value associated with the given key from Consul's Key/Value store."
  )
  fun getKV(@ElParam("consulUrl") consulUrl: String, @ElParam("key") key: String): String? {
    val consul = Consul.builder().withUrl(consulUrl).build()
    val kvClient = consul.keyValueClient()

    return kvClient.getValueAsString(key).orNull()
  }

  @ElFunction(
      prefix = Constants.CONSUL,
      name = "put",
      description = "Fetches the value associated with the given key from Consul's Key/Value store."
  )
  fun putKV(
      @ElParam("consulUrl") consulUrl: String,
      @ElParam("key") key: String,
      @ElParam("value") value: String
  ) {
    val consul = Consul.builder().withUrl(consulUrl).build()
    val kvClient = consul.keyValueClient()
    kvClient.putValue(key, value)
  }
}