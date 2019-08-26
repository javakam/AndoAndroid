package com.improve.modules.kotlin.kaixue

/**
 * Title: KaiXue2
 * <p>
 * Description:
 * </p>
 * @author Changbao
 * @date 2019/8/26  16:43
 */
import android.util.Log

class KaiXue2 private constructor() {

    companion object {
        const val TAG = "KaiXue2"
        fun getInstance() = KaiXue2()
    }

    fun calculateWithArray() {
        val startTime = System.currentTimeMillis()
        val list: Array<Int> = Array(100000, init = { i ->
            //            Log.d(TAG, "$i")
            i + 1
        })
        var sum = 0L
        for (i in list) {
//            Log.d(TAG, "$i")
            sum += i
        }
        val av = sum / list.size
        Log.d(TAG, "平均值 = $av, 用时：${System.currentTimeMillis() - startTime}")
    }

    fun calculateWithIntArray() {
        val startTime = System.currentTimeMillis()
        val list: IntArray = IntArray(100000, init = { i ->
            //            Log.d(TAG, "$i")
            i + 1
        })
        var sum = 0L
        for (i in list) {
            sum += i
        }
        val av = sum / list.size
        Log.d(TAG, "平均值 = $av, 用时：${System.currentTimeMillis() - startTime}")
    }

    fun calculateWithList() {
        val startTime = System.currentTimeMillis()
        val list: List<Int> = List(100000, init = { i ->
            //            Log.d(TAG, "$i")
            i + 1
        })
        var sum = 0L
        for (i in list) {
            sum += i
        }
        val av = sum / list.size
        Log.d(TAG, "平均值 = $av, 用时：${System.currentTimeMillis() - startTime}")
    }
}