package com.adamkunicki.streamsets.datacollector.stage.processor.kotlin

import com.streamsets.pipeline.api.Stage

abstract class DStage<C : Stage.Context> : Stage<C> {
  var stage: Stage<C>? = null
    private set

  abstract fun createStage(): Stage<C>

  override final fun init(info: Stage.Info, context: C): List<Stage.ConfigIssue> {
    if (stage == null) {
      stage = createStage()
    }
    return stage?.init(info, context).orEmpty()
  }

  override final fun destroy(): Unit {
    stage?.destroy()
  }

}