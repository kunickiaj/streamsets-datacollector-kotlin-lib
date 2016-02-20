package com.adamkunicki.streamsets.datacollector.stage.processor.kotlin

import com.streamsets.pipeline.api.Processor

class KotlinScriptingDProcessor : DProcessor() {
    override fun createProcessor(): Processor {
        return KotlinScriptingProcessor()
    }
}