package jp.gr.java_conf.saka.multistopwatch.mediator.impl

import jp.gr.java_conf.saka.multistopwatch.mediator.base.ILapTimeContainer

class LapTimeTextElement(
    private val lapNumber: Int,
    val type: String,
    val lapTime: String,
    private val lapTimeValue: Long
) : ILapTimeContainer {
    companion object {
        fun lapTimeComparator(): Comparator<LapTimeTextElement> {
            return compareBy { it.lapTimeValue }
        }

        fun lapNumberComparator(): Comparator<LapTimeTextElement> {
            return compareBy { it.lapNumber }
        }
    }

    override fun getLapNumber(): Int {
        return lapNumber
    }

    override fun getLapTimeValue(): Long {
        return lapTimeValue
    }
}
