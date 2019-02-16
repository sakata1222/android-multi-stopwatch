package jp.gr.java_conf.saka.multistopwatch.formatter

class TimeFormatters {
    companion object {
        fun newDefaultInstance(): ITimeFormatter {
            return TimeFormatterDateTimeFormatterImpl()
        }
    }
}
