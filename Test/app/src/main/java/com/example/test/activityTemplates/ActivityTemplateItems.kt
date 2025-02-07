package com.example.test.activityTemplates

import com.example.test.activityTemplates.basic.BasicActivity
import com.example.test.model.ActivityItemModel

object ActivityTemplateItems {
    val items = listOf(
        ActivityItemModel(BasicActivity::class.java, BasicActivity.name)
    )
}