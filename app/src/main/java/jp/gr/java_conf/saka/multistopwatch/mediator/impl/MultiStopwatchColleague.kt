package jp.gr.java_conf.saka.multistopwatch.mediator.impl

import android.view.View
import android.widget.Button
import android.widget.TextView
import jp.gr.java_conf.saka.multistopwatch.formatter.ITimeFormatter
import jp.gr.java_conf.saka.multistopwatch.mediator.base.IMultiStopwatchColleague
import jp.gr.java_conf.saka.multistopwatch.mediator.base.IMultiStopwatchMediator
import jp.gr.java_conf.saka.multistopwatch.mediator.base.ISingleStopwatchMediator
import jp.gr.java_conf.saka.multistopwatch.mediator.base.LapTimeSortTypeEnum
import jp.gr.java_conf.saka.multistopwatch.stopwatch.IStopwatch


class MultiStopwatchColleague(
    private val stopwatch: IStopwatch,
    private val stopwatchView: View,
    private val stopwatchTextView: TextView,
    private val upperButton: Button,
    private val lowerButton: Button,
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
                collegueChanged(this)
                stopwatch.start()
            }
        }
        stopListener = object : View.OnClickListener {
            override fun onClick(p0: View?) {
                collegueChanged(this)
                stopwatch.stop()
            }
        }
        lapListener = object : View.OnClickListener {
            override fun onClick(p0: View?) {
                collegueChanged(this)
                stopwatch.lap()
            }

        }
        resetListener = object : View.OnClickListener {
            override fun onClick(p0: View?) {
                collegueChanged(this)
                stopwatch.reset()
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
            stopwatchTextView.isEnabled = true;
        } else {
            disable();
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

    override fun reLoad() {
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
            // reLoadLapTime(); TODO
        } else if (colleague == resetListener) {
            // adapter.clear();TODO
            // adapter.notifyDataSetChanged();TODO
            lowerButton.isEnabled = false;
            stopwatchTextView.text = timeFormatter.format(stopwatch.getCurrent());
            return;
        }
        multiMediator?.notifyCollegueChanged(this)
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
        LAP("Not Implemented"), // TODO
        RESET("Reset")
    }
}
