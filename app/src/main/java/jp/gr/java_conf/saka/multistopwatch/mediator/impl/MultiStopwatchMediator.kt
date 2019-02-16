package jp.gr.java_conf.saka.multistopwatch.mediator.impl

import android.graphics.drawable.Drawable
import android.widget.LinearLayout
import jp.gr.java_conf.saka.multistopwatch.mediator.base.*
import jp.gr.java_conf.saka.multistopwatch.stopwatch.IStopwatch

class MultiStopwatchMediator(
    private val layout: LinearLayout,
    private var activeBackground: Drawable,
    private var runMode: MultiStopwatchRunModeEnum,
    private val stopwatchList: List<IMultiStopwatchColleague>
) : IMultiStopwatchMediator {
    private var activeColleague: IMultiStopwatchColleague
    private var activeStopwatch: IStopwatch

    init {
        stopwatchList.stream().forEach { c -> c.setMediator(this) }
        activeColleague = stopwatchList[0]
        activeStopwatch = activeColleague.getStopwatch()
    }

    companion object StaticFactory {
        fun newInstance(
            layout: LinearLayout,
            activeBackground: Drawable,
            runMode: MultiStopwatchRunModeEnum,
            factory: IMultiStopwatchColleagueFactory,
            numOfStopwatch: Int
        ): IMultiStopwatchMediator {
            val stopwatchList =
                IntProgression.fromClosedRange(0, numOfStopwatch - 1, 1)
                    .map { _ -> factory.newInstance() }.map { c ->
                        layout.addView(c.getMainView())
                        c
                    }
            return MultiStopwatchMediator(layout, activeBackground, runMode, stopwatchList)
        }
    }

    override fun notifyCollegueChanged(colleague: IMultiStopwatchColleague) {
        changeColleague(colleague, runMode, stopwatchList)
    }

    override fun getActiveStopwatch(): IStopwatch {
        return activeStopwatch
    }

    override fun refreshActiveStopwatchText() {
        activeColleague.refreshStopwatchTextView()
    }

    override fun refreshAllStopwatchText() {
        stopwatchList.forEach { s -> s.refreshStopwatchTextView() }
    }

    override fun getTotalCurrentTime(): Long {
        return stopwatchList.map { s -> s.getStopwatch().getCurrent() }.sum()
    }

    override fun setLapTimeSortType(lapTimeSortType: LapTimeSortTypeEnum) {
        stopwatchList.map { s -> s.setLapTimeSortType(lapTimeSortType) }
    }

    override fun setRunMode(runMode: MultiStopwatchRunModeEnum) {
        this.runMode = runMode
        changeColleague(activeColleague, runMode, stopwatchList)
    }

    override fun reload() {
        stopwatchList.forEach { s -> s.reLoad() }
    }

    private fun changeColleague(
        colleague: IMultiStopwatchColleague,
        runMode: MultiStopwatchRunModeEnum,
        stopwatchList: List<IMultiStopwatchColleague>
    ) {
        stopwatchList.forEach {
            runMode.changeColleague(it === colleague, it, activeBackground)
        }
        activeColleague = stopwatchList.first { c -> c === colleague }
        activeStopwatch = activeColleague.getStopwatch()
    }
}
