package com.example.test.data

import com.example.test.TestActivityTemplates
import com.example.test.TestDetectingSensorActivity
import com.example.test.TestLifeCycleActivity
import com.example.test.model.ActivityItemModel

object ActivityItems {
    val items = listOf(
        ActivityItemModel(TestLifeCycleActivity::class.java, TestLifeCycleActivity.name),
        ActivityItemModel(TestDetectingSensorActivity::class.java, TestDetectingSensorActivity.name),
        ActivityItemModel(TestActivityTemplates::class.java, TestActivityTemplates.name),
    )
}