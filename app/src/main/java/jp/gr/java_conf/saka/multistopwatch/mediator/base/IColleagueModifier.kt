package jp.gr.java_conf.saka.multistopwatch.mediator.base

import android.graphics.drawable.Drawable

interface IColleagueModifier {
    fun changeColleague(
        isChanged: Boolean,
        stopwatchColleague: IMultiStopwatchColleague,
        activeBackground: Drawable
    )
}
