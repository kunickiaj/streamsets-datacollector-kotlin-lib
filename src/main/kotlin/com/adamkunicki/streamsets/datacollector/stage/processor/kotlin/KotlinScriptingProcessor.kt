package com.adamkunicki.streamsets.datacollector.stage.processor.kotlin

import com.streamsets.pipeline.api.Batch
import com.streamsets.pipeline.api.Field
import com.streamsets.pipeline.api.Processor
import com.streamsets.pipeline.api.Stage
import com.streamsets.pipeline.api.base.SingleLaneProcessor

class KotlinScriptingProcessor : SingleLaneProcessor() {

  override fun init(info: Stage.Info?, context: Processor.Context?): MutableList<Stage.ConfigIssue>? {
    return super.init(info, context)
  }

  override fun destroy() {
    super.destroy()
  }

  override fun process(batch: Batch?, batchMaker: SingleLaneBatchMaker?) {
    batch?.records?.iterator()?.forEach { record ->
      record.set("/test", Field.create("value"))
    }
  }
}