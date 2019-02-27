package jp.gr.java_conf.saka.multistopwatch.mediator.base


enum class LapTimeSortTypeEnum(
    val description: String,
    val lapTimeComparator: Comparator<ILapTimeContainer>
) {
    NEWER("From newest to oldest", compareBy { lap -> lap.getLapNumber() }),
    OLDER("From oldest to newest", compareByDescending { lap -> lap.getLapNumber() }),
    FASTER("From fastest to slowest", compareBy { lap -> lap.getLapTimeValue() }),
    LATER("From slowest to fastest", compareByDescending { lap -> lap.getLapTimeValue() });

    companion object {
        fun getDefault(): LapTimeSortTypeEnum {
            return NEWER
        }
    }
}
