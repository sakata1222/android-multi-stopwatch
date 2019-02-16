package jp.gr.java_conf.saka.multistopwatch.mediator.base

import android.graphics.drawable.Drawable

enum class MultiStopwatchRunModeEnum(val description: String) : IColleagueModifier {
    Independently("Independently") {
        override fun changeColleague(
            isChanged: Boolean,
            stopwatchColleague: IMultiStopwatchColleague,
            activeBackground: Drawable
        ) {
            stopwatchColleague.setColleagueEnabled(true);
            stopwatchColleague.getStopwatchTextView().background = null;
        }
    },
    Concertedly("Concertedly") {
        override fun changeColleague(
            isChanged: Boolean,
            stopwatchColleague: IMultiStopwatchColleague,
            activeBackground: Drawable
        ) {
            if (isChanged) {
                stopwatchColleague.setColleagueEnabled(true);
                stopwatchColleague.getStopwatchTextView().background = activeBackground;
            } else {
                stopwatchColleague.setColleagueEnabled(false);
                stopwatchColleague.getStopwatchTextView().background = null;
            }
        }
    };

    companion object {
        fun getDefaultValue(): MultiStopwatchRunModeEnum {
            return Concertedly
        }
    }
}
