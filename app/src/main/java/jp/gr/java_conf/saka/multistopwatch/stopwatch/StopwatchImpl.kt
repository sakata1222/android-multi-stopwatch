package jp.gr.java_conf.saka.multistopwatch.stopwatch

import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class StopwatchImpl() : IStopwatch {
    private val lock: ReentrantLock
    private val isStarted: AtomicBoolean
    private val isInitialState: AtomicBoolean
    private var startTime: Long
    private var totalTime: Long
    private val lapTimeList: MutableList<Long>

    init {
        lock = ReentrantLock()
        isStarted = AtomicBoolean(false)
        isInitialState = AtomicBoolean(true)
        startTime = 0L
        totalTime = 0L
        lapTimeList = ArrayList()
    }

    override fun start() {
        if (isStarted.getAndSet(true).not()) {
            lock.withLock {
                isStarted.set(true)
                isInitialState.set(false)
                startTime = getCurrentTimeMill()
            }
        }
    }

    override fun stop(): Long {
        if (isStarted.getAndSet(false)) {
            lock.withLock {
                totalTime += getCurrentTimeMill() - startTime
            }
        }
        return totalTime
    }

    override fun reset() {
        lock.withLock {
            isStarted.set(false)
            isInitialState.set(true)
            startTime = 0L
            totalTime = 0L
            lapTimeList.clear()
        }
    }

    override fun lap(): Long {
        if (isStarted.get()) {
            val current = getCurrentTimeMill()
            val currentLapTime = (current - startTime) + totalTime - lapTimeList.sum();
            lapTimeList.add(currentLapTime)
            return currentLapTime
        }
        return 0L
    }

    override fun getCurrent(): Long {
        if (isStarted.get()) {
            return totalTime + getCurrentTimeMill() - startTime;
        } else {
            return totalTime;
        }
    }

    override fun notifyAlarm() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLapList(): List<Long> {
        return lapTimeList
    }

    override fun isInitialState(): Boolean {
        return isInitialState.get()
    }

    private fun getCurrentTimeMill(): Long {
        return System.currentTimeMillis() / 100 * 100
    }
}
