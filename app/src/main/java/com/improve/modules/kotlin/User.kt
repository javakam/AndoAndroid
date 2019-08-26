package com.improve.modules.kotlin

/**
 * Title: User
 * <p>
 * Description:
 * </p>
 * @author Changbao
 * @date 2019/8/26  14:17
 */
class User {

    var name: String? = null
        get() {
            return "姓名: " + field
        }
        set(value) {
            field
        }

    var age: Int? = null

    companion object {
        val TAG = "User"
    }

    object Data {
        val TAG = "User"
    }
}
