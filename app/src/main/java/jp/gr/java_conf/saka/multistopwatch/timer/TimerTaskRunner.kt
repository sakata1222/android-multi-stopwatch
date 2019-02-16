package jp.gr.java_conf.saka.multistopwatch.timer

import android.os.Handler

class TimerTaskRunner(val handler: Handler = Handler()) {
    fun start(runnable: Runnable, delayMillis: Long) {
        val task = object : Runnable {
            override fun run() {
                runnable.run()
                handler.postDelayed(this, delayMillis)
            }
        }
        handler.postDelayed(task, delayMillis)
    }

    fun stop() {
        handler.removeCallbacks(null)
    }
}
