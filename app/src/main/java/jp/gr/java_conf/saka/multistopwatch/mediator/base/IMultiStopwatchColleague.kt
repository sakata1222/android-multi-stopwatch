package jp.gr.java_conf.saka.multistopwatch.mediator.base

import android.view.View
import jp.gr.java_conf.saka.multistopwatch.stopwatch.IStopwatch


interface IMultiStopwatchColleague {
    fun setMediator(mediator: IMultiStopwatchMediator)

    fun setColleagueEnabled(enable: Boolean)

    fun getMainView(): View

    fun getStopwatch(): IStopwatch

    fun refreshStopwatchTextView()

    fun isEnable(): Boolean

    fun getStopwatchTextView(): View

    fun setLapTimeSortType(lapTimeOrderType: LapTimeSortTypeEnum)

    fun reLoad()
}
