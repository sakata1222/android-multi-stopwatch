package jp.gr.java_conf.saka.multistopwatch.components

import android.os.Bundle
import android.preference.ListPreference
import android.preference.PreferenceFragment
import jp.gr.java_conf.saka.multistopwatch.R
import jp.gr.java_conf.saka.multistopwatch.mediator.base.LapTimeSortTypeEnum
import jp.gr.java_conf.saka.multistopwatch.mediator.base.MultiStopwatchRunModeEnum
import jp.gr.java_conf.saka.multistopwatch.preference.StopwatchPreference
import java.util.concurrent.atomic.AtomicBoolean


class MainPreferenceFragment : PreferenceFragment() {
    private var stopwatchPreference: StopwatchPreference? = null
    private val initialized = AtomicBoolean(false)

    fun init(stopwatchPreference: StopwatchPreference): MainPreferenceFragment {
        this.stopwatchPreference = stopwatchPreference
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun init() {
        if (initialized.getAndSet(true).not()) {
            initLapTimeSortOrder()
            initStopwatchesRunMode()
        }
    }

    private fun initLapTimeSortOrder() {
        val preference =
            findPreference(getString(R.string.prefer_lapTimeSort_key)) as ListPreference
        preference.entries = LapTimeSortTypeEnum.values().map { e -> e.name }.toTypedArray()
        preference.entryValues =
            LapTimeSortTypeEnum.values().map { e -> e.description }.toTypedArray()
        preference.setDefaultValue(LapTimeSortTypeEnum.getDefault().description)
    }

    private fun initStopwatchesRunMode() {
        val preference = findPreference(getString(R.string.prefer_runmode_key)) as ListPreference
        preference.entries =
            MultiStopwatchRunModeEnum.values().map { e -> e.name }.toTypedArray()
        preference.entryValues =
            MultiStopwatchRunModeEnum.values().map { e -> e.description }.toTypedArray()
        preference.setDefaultValue(MultiStopwatchRunModeEnum.getDefaultValue())
    }
}
