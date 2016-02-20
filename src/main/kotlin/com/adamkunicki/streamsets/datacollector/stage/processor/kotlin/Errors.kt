package com.adamkunicki.streamsets.datacollector.stage.processor.kotlin

import com.streamsets.pipeline.api.ErrorCode
import com.streamsets.pipeline.api.GenerateResourceBundle

@GenerateResourceBundle
enum class Errors(val label: String) : ErrorCode {
  KOTLIN_01("Something went wrong...") {
    override fun getMessage(): String? {
      return label
    }

    override fun getCode(): String? {
      return name
    }
  }
}