package jp.gr.java_conf.saka.multistopwatch.mediator.base

interface ILapTimeContainer {
    fun getLapNumber(): Int

    fun getLapTimeValue(): Long
}
