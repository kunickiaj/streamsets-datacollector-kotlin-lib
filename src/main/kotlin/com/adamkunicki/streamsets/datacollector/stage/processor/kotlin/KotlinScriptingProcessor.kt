package com.adamkunicki.streamsets.datacollector.stage.processor.kotlin

import com.streamsets.pipeline.api.Batch
import com.streamsets.pipeline.api.Field
import com.streamsets.pipeline.api.Processor
import com.streamsets.pipeline.api.Stage
import com.streamsets.pipeline.api.base.SingleLaneProcessor
import mu.KLogging
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

class KotlinScriptingProcessor : SingleLaneProcessor() {
  var engine: ScriptEngine? = null

  override fun init(info: Stage.Info?, context: Processor.Context?): MutableList<Stage.ConfigIssue>? {
    val issues = super.init(info, context)

    // Initialize Script Engine
    val factory = ScriptEngineManager().getEngineByExtension("kts").factory
    engine = factory.scriptEngine
    val bindings = engine?.createBindings()

    return issues
  }

  override fun destroy() {
    super.destroy()
  }

  override fun process(batch: Batch?, batchMaker: SingleLaneBatchMaker?) {
    batch?.records?.iterator()?.forEach { record ->
      record.set("/test", Field.create("value"))
    }
  }

  companion object: KLogging()
}