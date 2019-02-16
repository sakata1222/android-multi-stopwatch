package jp.gr.java_conf.saka.multistopwatch

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.LinearLayout
import android.widget.TextView
import jp.gr.java_conf.saka.multistopwatch.formatter.ITimeFormatter
import jp.gr.java_conf.saka.multistopwatch.mediator.base.IMultiStopwatchMediator
import jp.gr.java_conf.saka.multistopwatch.mediator.base.LapTimeSortTypeEnum
import jp.gr.java_conf.saka.multistopwatch.mediator.base.MultiStopwatchRunModeEnum
import jp.gr.java_conf.saka.multistopwatch.mediator.impl.MultiStopwatchColleagueFactory
import jp.gr.java_conf.saka.multistopwatch.mediator.impl.MultiStopwatchMediator
import jp.gr.java_conf.saka.multistopwatch.timer.TimerTaskRunner


class MainScreen(
    val mainTimerText: TextView,
    val timeFormatter: ITimeFormatter,
    val mediator: IMultiStopwatchMediator
) {
    companion object StaticFactory {
        fun newInstance(
            context: Context,
            mainTimerText: TextView,
            stopwatches: LinearLayout,
            activeBackground: Drawable,
            width: Int,
            timeFormatter: ITimeFormatter,
            option: Option
        ): MainScreen {
            return MainScreen(
                mainTimerText,
                timeFormatter,
                MultiStopwatchMediator.newInstance(
                    stopwatches,
                    activeBackground,
                    option.runMode,
                    MultiStopwatchColleagueFactory(
                        R.layout.elem_stopwatch,
                        context,
                        option.numOfStopwatch,
                        width,
                        option.sortType
                    ),
                    option.numOfStopwatch
                )
            )
        }
    }

    fun start(): MainScreen {
        TimerTaskRunner().start(Runnable {
            mediator.refreshAllStopwatchText()
            mainTimerText.text = timeFormatter.format(mediator.getTotalCurrentTime())
        }, 50)
        return this
    }

    class Option(
        val numOfStopwatch: Int,
        val sortType: LapTimeSortTypeEnum,
        val runMode: MultiStopwatchRunModeEnum
    )
}
