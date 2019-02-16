package jp.gr.java_conf.saka.multistopwatch.mediator.impl

class LapTimeTextElement(
    val lapNumber: Int,
    val type: String,
    val lapTime: String,
    val lapTimeValue: Long
) {
    companion object {
        fun lapTimeComparator(): Comparator<LapTimeTextElement> {
            return compareBy { it.lapTimeValue }
        }

        fun lapNumberComparator(): Comparator<LapTimeTextElement> {
            return compareBy { it.lapNumber }
        }
    }
}
