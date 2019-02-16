package jp.gr.java_conf.saka.multistopwatch.formatter

import java.time.Instant
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder

class TimeFormatterDateTimeFormatterImpl(
    private val actualTimeFormatter: DateTimeFormatter = DateTimeFormatterBuilder().appendPattern(
        "mm:ss.S"
    ).toFormatter()
) : ITimeFormatter {


    override fun format(timeInMillis: Long): String {
        return actualTimeFormatter.format(
            ZonedDateTime.ofInstant(
                Instant.ofEpochMilli(timeInMillis),
                ZoneOffset.UTC
            )
        )
    }

    override fun formatList(timeInMillisList: List<Long>): List<String> {
        return timeInMillisList.map { t -> format(t) }
    }
}
