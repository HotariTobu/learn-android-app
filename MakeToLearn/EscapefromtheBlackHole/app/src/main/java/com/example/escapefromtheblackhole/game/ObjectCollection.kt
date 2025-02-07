package com.example.escapefromtheblackhole.game

import android.graphics.Canvas

class ObjectCollection {
    private val layerList = mutableListOf<MutableList<IObject>>()

    fun add(iObject: IObject) {
        val level = iObject.layerLevel
        while (layerList.size <= level) {
            layerList.add(mutableListOf())
        }
        layerList[level].add(iObject)
    }

    fun remove(iObject: IObject) {
        layerList[iObject.layerLevel].remove(iObject)
    }

    fun init(surfaceSize: Size){
        for (mutableList in layerList) {
            for (iObject in mutableList) {
                iObject.init(surfaceSize)
            }
        }
    }

    fun dispose(){
        for (mutableList in layerList) {
            for (iObject in mutableList) {
                iObject.dispose()
            }
        }
    }

    fun update(delta: Int) {
        for (mutableList in layerList) {
            for (iObject in mutableList) {
                iObject.update(delta)
            }
        }
    }

    fun draw(canvas: Canvas) {
        for (mutableList in layerList) {
            for (iObject in mutableList) {
                iObject.draw(canvas)
            }
        }
    }
}