package jp.gr.java_conf.saka.multistopwatch.mediator.base

import jp.gr.java_conf.saka.multistopwatch.stopwatch.IStopwatch


interface IMultiStopwatchMediator {
    fun notifyCollegueChanged(colleague: IMultiStopwatchColleague)

    fun getActiveStopwatch(): IStopwatch
    fun refreshActiveStopwatchText()
    fun refreshAllStopwatchText()

    fun getTotalCurrentTime(): Long
    fun setLapTimeSortType(lapTimeSortType: LapTimeSortTypeEnum)
    fun setRunMode(runMode: MultiStopwatchRunModeEnum)
    fun reload()
}
