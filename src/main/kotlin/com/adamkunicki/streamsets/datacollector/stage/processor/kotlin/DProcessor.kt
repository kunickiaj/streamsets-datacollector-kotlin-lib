package com.adamkunicki.streamsets.datacollector.stage.processor.kotlin

import com.streamsets.pipeline.api.Batch
import com.streamsets.pipeline.api.BatchMaker
import com.streamsets.pipeline.api.Processor
import com.streamsets.pipeline.api.Stage

abstract class DProcessor : DStage<Processor.Context>(), Processor {
  protected abstract fun createProcessor(): Processor

  override fun createStage(): Stage<Processor.Context> {
    return createProcessor()
  }

  override fun process(batch: Batch?, batchMaker: BatchMaker?) {
    (stage as Processor).process(batch, batchMaker)
  }
}