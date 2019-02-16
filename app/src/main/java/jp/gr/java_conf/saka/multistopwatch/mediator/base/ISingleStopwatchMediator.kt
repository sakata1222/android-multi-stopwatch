package jp.gr.java_conf.saka.multistopwatch.mediator.base

import android.view.View
import jp.gr.java_conf.saka.multistopwatch.stopwatch.IStopwatch


interface ISingleStopwatchMediator {
    fun collegueChanged(colleague: View.OnClickListener)
    fun getStopwatch(): IStopwatch
}
