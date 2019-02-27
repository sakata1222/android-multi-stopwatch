package jp.gr.java_conf.saka.multistopwatch.mediator.impl

import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import jp.gr.java_conf.saka.multistopwatch.formatter.ITimeFormatter
import jp.gr.java_conf.saka.multistopwatch.mediator.base.*
import jp.gr.java_conf.saka.multistopwatch.stopwatch.IStopwatch


class MultiStopwatchColleague(
    private val stopwatch: IStopwatch,
    private val stopwatchView: View,
    private val stopwatchTextView: TextView,
    private val upperButton: Button,
    private val lowerButton: Button,
    private val lapTimeListView: ListView,
    private val lapTimeTextAdapter: LapTimeTextAdapter,
    private val timeFormatter: ITimeFormatter
) :
    IMultiStopwatchColleague,
    ISingleStopwatchMediator {

    private var multiMediator: IMultiStopwatchMediator? = null
    private var isEnable: Boolean = true

    private val startListener: View.OnClickListener
    private val stopListener: View.OnClickListener
    private val lapListener: View.OnClickListener
    private val resetListener: View.OnClickListener

    init {
        startListener = object : View.OnClickListener {
            override fun onClick(p0: View?) {
                stopwatch.start()
                collegueChanged(this)
            }
        }
        stopListener = object : View.OnClickListener {
            override fun onClick(p0: View?) {
                stopwatch.stop()
                collegueChanged(this)
            }
        }
        lapListener = object : View.OnClickListener {
            override fun onClick(p0: View?) {
                stopwatch.lap()
                collegueChanged(this)
            }

        }
        resetListener = object : View.OnClickListener {
            override fun onClick(p0: View?) {
                stopwatch.reset()
                collegueChanged(this)
            }
        }
        upperButton.setOnClickListener(startListener);
        lowerButton.setOnClickListener(resetListener);
        upperButton.text = ButtonTypeEnum.START.label;
        lowerButton.text = ButtonTypeEnum.RESET.label;
        lowerButton.isEnabled = false;

    }

    override fun setMediator(mediator: IMultiStopwatchMediator) {
        this.multiMediator = mediator
    }

    override fun setColleagueEnabled(enable: Boolean) {
        this.isEnable = enable
        if (enable) {
            stopwatchTextView.isEnabled = true
            lapTimeListView.isEnabled = true;
            lapTimeTextAdapter.notifyDataSetChanged();
        } else {
            disable()
        }
    }

    override fun getMainView(): View {
        return stopwatchView
    }

    override fun getStopwatch(): IStopwatch {
        return stopwatch
    }

    override fun refreshStopwatchTextView() {
        stopwatchTextView.text = timeFormatter.format(stopwatch.getCurrent())
    }

    override fun isEnable(): Boolean {
        return isEnable
    }

    override fun getStopwatchTextView(): View {
        return stopwatchTextView
    }

    override fun setLapTimeSortType(lapTimeOrderType: LapTimeSortTypeEnum) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun reload() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun collegueChanged(colleague: View.OnClickListener) {
        if (colleague == startListener) {
            changeButtonType(upperButton, ButtonTypeEnum.STOP);
            changeButtonType(lowerButton, ButtonTypeEnum.LAP);
            lowerButton.isEnabled = true;
        } else if (colleague == stopListener) {
            changeButtonType(upperButton, ButtonTypeEnum.START);
            changeButtonType(lowerButton, ButtonTypeEnum.RESET);
        } else if (colleague == lapListener) {
            reloadLapTime()
        } else if (colleague == resetListener) {
            lapTimeTextAdapter.clear();
            lapTimeTextAdapter.notifyDataSetChanged()
            lowerButton.isEnabled = false;
            stopwatchTextView.text = timeFormatter.format(stopwatch.getCurrent());
            return;
        }
        multiMediator?.notifyCollegueChanged(this)
    }

    private fun reloadLapTime() {
        lapTimeTextAdapter.clear()
        val elementList = createLapElementList(stopwatch.getLapList())
        lapTimeTextAdapter.addAll(elementList)
        lapTimeTextAdapter.notifyDataSetChanged()
    }

    private fun createLapElementList(
        lapTimeList: List<Long>
    ): List<LapTimeTextElement> {
        val min = lapTimeList.min() ?: Long.MIN_VALUE
        val isMin = { value: Long -> value == min }
        val max = lapTimeList.max() ?: Long.MIN_VALUE
        val isMax = { value: Long -> value == max }
        val resolveType = fun(value: Long): LapTypeEnum {
            return when {
                isMin(value) -> LapTypeEnum.FASTEST
                isMax(value) -> LapTypeEnum.SLOWEST
                else -> LapTypeEnum.NONE
            }
        }
        return lapTimeList.mapIndexed { i, value ->
            LapTimeTextElement(
                i + 1,
                resolveType(value).displayName,
                timeFormatter.format(value),
                value
            )
        }
            .sortedWith(LapTimeSortTypeEnum.getDefault().lapTimeComparator) // TODO  make sortType configurable
    }


    private fun changeButtonType(button: Button, type: ButtonTypeEnum) {
        button.text = type.label
        when (type) {
            ButtonTypeEnum.START -> button.setOnClickListener(startListener)
            ButtonTypeEnum.STOP -> button.setOnClickListener(stopListener)
            ButtonTypeEnum.LAP -> button.setOnClickListener(lapListener)
            ButtonTypeEnum.RESET -> button.setOnClickListener(resetListener)
        }
    }

    private fun disable() {
        stopwatch.stop()
        changeButtonType(upperButton, ButtonTypeEnum.START)
        changeButtonType(lowerButton, ButtonTypeEnum.RESET)
        stopwatchTextView.text = timeFormatter.format(stopwatch.getCurrent())
        stopwatchTextView.isEnabled = false
    }

    enum class ButtonTypeEnum(val label: String) {
        START("Start"),
        STOP("Stop"),
        LAP("Lap"),
        RESET("Reset")
    }
}
