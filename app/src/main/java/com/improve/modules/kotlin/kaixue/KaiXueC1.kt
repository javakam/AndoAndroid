package com.improve.modules.kotlin.kaixue

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.TextView
import com.improve.R
import com.improve.base.fragment.BaseSwipeFragment

/**
 * Title: 第一课$
 * <p>
 * Description:
 * </p>
 * @author Changbao
 * @date 2019/8/26  16:36
 */
class KaiXueC1 : BaseSwipeFragment() {

    companion object {
        const val TAG = "KaiXueC1"
    }

    private lateinit var mTvHomework: View

    override fun getLayoutResId(): Int {
        return R.layout.fragment_kaixue_c1
    }

    override fun initViews(v: View?) {

        mTvHomework = rootView.findViewById(R.id.tv_kaixue_c1)
        setBg(mTvHomework)
        test(mTvHomework)

        test(null)
    }


    private fun setBg(view: View?) {
        view?.setBackgroundColor(Color.YELLOW)
    }

    private fun test(view: View?) {
        try {
            val textView = view as? TextView
            Log.d(TAG, "${view?.javaClass?.canonicalName} 通过 as? 转换为 TextView，是否为空 = ${textView == null}")
        } catch (e: Exception) {
            Log.d(TAG, "${view?.javaClass?.canonicalName} 通过 as? 转换为 TextView，转换出现异常 ${e}")
            e.printStackTrace()
        }
        try {
            val textView = view as TextView?
            Log.d(TAG, "${view?.javaClass?.canonicalName} 通过 as TextView? 转换为 TextView，是否为空 = ${textView == null}")
        } catch (e: Exception) {
            Log.d(TAG, "${view?.javaClass?.canonicalName} 通过 as TextView? 转换为 TextView，转换出现异常 ${e}")
            e.printStackTrace()
        }
        try {
            val textView = view as? TextView
            Log.d(TAG, "${view?.javaClass?.canonicalName} 通过 as? TextView? 转换为 TextView，是否为空 = ${textView == null}")
        } catch (e: Exception) {
            Log.d(TAG, "${view?.javaClass?.canonicalName} 通过 as? TextView? 转换为 TextView，转换出现异常 ${e}")
            e.printStackTrace()
        }
        Log.d(TAG, "========================分割线=========================")
    }

    // 可以修改
    public override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }
}