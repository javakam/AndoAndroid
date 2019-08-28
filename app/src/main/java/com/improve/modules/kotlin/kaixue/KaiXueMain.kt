package com.improve.modules.kotlin.kaixue

import com.improve.modules.kotlin.kaixue.chapter2.KaiXue2
import com.improve.modules.kotlin.kaixue.chapter3.Person

/**
 * Title: KaiXue2Main$
 * <p>
 * Description:
 * </p>
 * @author Changbao
 * @date 2019/8/26  16:47
 */
class KaiXueMain {

    fun test(x: String) {
        val kaixue2 = KaiXue2.getInstance()
        kaixue2.calculateWithArray()
        kaixue2.calculateWithIntArray()
        kaixue2.calculateWithList()

        val kaixue3 = Person("", 1)
    }

}