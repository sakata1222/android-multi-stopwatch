package jp.gr.java_conf.saka.multistopwatch.stopwatch

interface IStopwatch {
    fun start()
    fun stop(): Long
    fun reset()
    fun lap(): Long
    fun getCurrent(): Long
    fun notifyAlarm()
    fun getLapList(): List<Long>
    fun isInitialState(): Boolean
}
