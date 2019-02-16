package jp.gr.java_conf.saka.multistopwatch

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import jp.gr.java_conf.saka.multistopwatch.formatter.TimeFormatters
import jp.gr.java_conf.saka.multistopwatch.mediator.base.LapTimeSortTypeEnum
import jp.gr.java_conf.saka.multistopwatch.mediator.base.MultiStopwatchRunModeEnum
import jp.gr.java_conf.saka.multistopwatch.preference.StopwatchPreference


class MainActivity : AppCompatActivity() {

    var screen: MainScreen? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    private fun initialize() {
        val stopwatchPreference = StopwatchPreference()
        val usableWidth = getUsableWidth()
        screen = MainScreen.newInstance(
            this,
            findViewById<TextView>(R.id.mainTimerText),
            findViewById<LinearLayout>(R.id.stopwatchLayout),
            resources.getDrawable(R.drawable.active_background, theme),
            usableWidth,
            TimeFormatters.newDefaultInstance(),
            MainScreen.Option(
                stopwatchPreference.loadNumOfStopwatches(this, 2),
                stopwatchPreference.loadSortType(this, LapTimeSortTypeEnum.getDefault()),
                stopwatchPreference.loadRunMode(
                    this, MultiStopwatchRunModeEnum.getDefaultValue()
                )
            )
        ).start()
    }

    private fun getUsableWidth(): Int {
        val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val displaySize = Point()
        display.getSize(displaySize)

        val mainView = findViewById<LinearLayout>(R.id.mainLayout)
        val paddingLeft = mainView.paddingLeft
        val paddingRight = mainView.paddingRight

        return displaySize.x - (paddingLeft + paddingRight)
    }
}
