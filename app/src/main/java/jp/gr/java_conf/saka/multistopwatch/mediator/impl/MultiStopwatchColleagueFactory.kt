package jp.gr.java_conf.saka.multistopwatch.mediator.impl

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import jp.gr.java_conf.saka.multistopwatch.R
import jp.gr.java_conf.saka.multistopwatch.formatter.TimeFormatters
import jp.gr.java_conf.saka.multistopwatch.mediator.base.IMultiStopwatchColleague
import jp.gr.java_conf.saka.multistopwatch.mediator.base.IMultiStopwatchColleagueFactory
import jp.gr.java_conf.saka.multistopwatch.mediator.base.LapTimeSortTypeEnum
import jp.gr.java_conf.saka.multistopwatch.stopwatch.StopwatchImpl


class MultiStopwatchColleagueFactory(
    private val layoutId: Int,
    private val context: Context,
    private val numOfStopwatches: Int,
    private val totalWidth: Int,
    private val sortTypeEnum: LapTimeSortTypeEnum
) : IMultiStopwatchColleagueFactory {
    override fun newInstance(): IMultiStopwatchColleague {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val stopwatchView = inflater.inflate(layoutId, null) as View
        val lapTimeView = inflater.inflate(R.layout.elem_laptime, null) as View

        val textView = stopwatchView.findViewById<TextView>(R.id.singleTimerText)
        val upperButton = stopwatchView.findViewById(R.id.upperButton) as Button
        val lowerButton = stopwatchView.findViewById(R.id.lowwerButton) as Button
        val lapTimeListView = stopwatchView.findViewById(R.id.lapListView) as ListView

        val width = totalWidth / numOfStopwatches
        val textSize = getFittedTextSize(textView.textSize, numOfStopwatches)
        val buttonTextSize = getFittedButtonTextSize(upperButton.textSize, numOfStopwatches)
        val elementLayout = LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT)

        textView.layoutParams = elementLayout
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)

        upperButton.layoutParams = elementLayout
        upperButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, buttonTextSize)

        lowerButton.layoutParams = elementLayout
        lowerButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, buttonTextSize)

        lapTimeListView.layoutParams = elementLayout
        val lapTimeSize = getFittedTextSize(
            (lapTimeView.findViewById(R.id.lapTimeText) as TextView).textSize,
            numOfStopwatches
        )
        val lapTypeSize = getFittedTextSize(
            (lapTimeView.findViewById(R.id.lapType) as TextView).textSize,
            numOfStopwatches
        )

        val container = TextSizeContainer(TypedValue.COMPLEX_UNIT_PX, lapTimeSize)
        val lapTypeSizeCon = TextSizeContainer(TypedValue.COMPLEX_UNIT_PX, lapTypeSize)
        val lapTimeTextAdapter =
            LapTimeTextAdapter(
                context,
                R.layout.elem_laptime,
                container,
                lapTypeSizeCon,
                lapTypeSizeCon
            )
        lapTimeListView.adapter = lapTimeTextAdapter
        return MultiStopwatchColleague(
            StopwatchImpl(),
            stopwatchView,
            textView,
            upperButton,
            lowerButton,
            lapTimeListView,
            lapTimeTextAdapter,
            TimeFormatters.newDefaultInstance()
        )
    }

    private fun getFittedTextSize(maxSize: Float, numOfStopwatches: Int): Float {
        return if (numOfStopwatches < 3) {
            maxSize
        } else {
            (maxSize * 2) / numOfStopwatches
        }
    }

    private fun getFittedButtonTextSize(maxSize: Float, numOfStopwatch: Int): Float {
        return if (numOfStopwatch < 4) {
            maxSize
        } else {
            maxSize * 3 / numOfStopwatch.toFloat()
        }
    }
}
