package jp.gr.java_conf.saka.multistopwatch.formatter

interface ITimeFormatter {
    fun format(timeInMillis: Long): String
    fun formatList(timeInMillisList: List<Long>): List<String>
}
