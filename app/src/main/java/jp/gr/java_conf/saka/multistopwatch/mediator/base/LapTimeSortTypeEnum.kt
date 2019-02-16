package jp.gr.java_conf.saka.multistopwatch.mediator.base


enum class LapTimeSortTypeEnum(val description: String) {
    NEWER("From newest to oldest"),
    OLDER("From oldest to newest"),
    FASTER("From fastest to latest"),
    LATER("From latest to fastest");

    companion object {
        fun getDefault(): LapTimeSortTypeEnum {
            return NEWER
        }
    }
}
