package jp.gr.java_conf.saka.multistopwatch.preference

import android.content.Context
import jp.gr.java_conf.saka.multistopwatch.mediator.base.LapTimeSortTypeEnum
import jp.gr.java_conf.saka.multistopwatch.mediator.base.MultiStopwatchRunModeEnum

class StopwatchPreference() {
    private companion object {
        val PREFERENCE: PreferenceAccessor =
            PreferenceAccessors.newAccessor("stopwatch_preference", Context.MODE_PRIVATE)

        const val KEY_NUM_OF_WATCHES: String = "num_of_watches_int"
        const val KEY_LAP_TIME_SORT_TYPE = "lap_time_sort_type_string"
        const val KEY_RUN_MODE = "run_mode_string"

    }

    fun saveNumOfStopwatches(context: Context, numOfStopwatches: Int) {
        PREFERENCE.saveInt(context, KEY_NUM_OF_WATCHES, numOfStopwatches)
    }

    fun loadNumOfStopwatches(
        context: Context,
        defaultValue: Int
    ): Int {
        return PREFERENCE.loadInt(
            context,
            KEY_NUM_OF_WATCHES,
            defaultValue
        )
    }

    fun saveSortType(context: Context, sortType: LapTimeSortTypeEnum) {
        PREFERENCE.saveString(context, KEY_LAP_TIME_SORT_TYPE, sortType.name)
    }

    fun loadSortType(
        context: Context,
        defaultValue: LapTimeSortTypeEnum
    ): LapTimeSortTypeEnum {
        return LapTimeSortTypeEnum.valueOf(
            PREFERENCE.loadString(
                context,
                KEY_LAP_TIME_SORT_TYPE,
                defaultValue.name
            )
        )
    }


    fun saveRunMode(context: Context, runMode: MultiStopwatchRunModeEnum) {
        PREFERENCE.saveString(context, KEY_RUN_MODE, runMode.name)
    }

    fun loadRunMode(
        context: Context,
        defaultValue: MultiStopwatchRunModeEnum
    ): MultiStopwatchRunModeEnum {
        return MultiStopwatchRunModeEnum.valueOf(
            PREFERENCE.loadString(
                context,
                KEY_RUN_MODE,
                defaultValue.name
            )
        )
    }

}
