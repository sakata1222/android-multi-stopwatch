package jp.gr.java_conf.saka.multistopwatch.mediator.impl

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import jp.gr.java_conf.saka.multistopwatch.R
import jp.gr.java_conf.saka.multistopwatch.common.CustomBaseAdapter


class LapTimeTextAdapter(
    context: Context,
    resourceId: Int,
    val lapNumberSize: TextSizeContainer,
    val typeSize: TextSizeContainer,
    val lapTimeSize: TextSizeContainer
) :
    CustomBaseAdapter<LapTimeTextElement>(context, resourceId) {

    override fun convertView(
        position: Int,
        v: View,
        parent: ViewGroup,
        elem: LapTimeTextElement
    ): View {
        val lapNumberText = v.findViewById(R.id.lapNumber) as TextView
        val lapType = v.findViewById(R.id.lapType) as TextView
        val lapTimeText = v.findViewById(R.id.lapTimeText) as TextView

        lapNumberText.typeface = Typeface.create(Typeface.SERIF, Typeface.BOLD);
        lapNumberText.text = elem.getLapNumber().toString()
        lapType.text = elem.type
        lapTimeText.text = elem.lapTime

        emphasizeNewestOne(position, lapNumberText)
        emphasizeNewestOne(position, lapType)
        emphasizeNewestOne(position, lapTimeText)

        lapNumberText.setTextSize(lapNumberSize.sizeUnit, lapNumberSize.textSize)
        lapType.setTextSize(typeSize.sizeUnit, typeSize.textSize)
        lapTimeText.setTextSize(lapTimeSize.sizeUnit, lapTimeSize.textSize)

        lapNumberText.isEnabled = isEnable;
        lapType.isEnabled = isEnable;
        lapTimeText.isEnabled = isEnable;
        return v
    }

    fun emphasizeNewestOne(position: Int, target: TextView) {
        val paint = target.paint
        paint.isUnderlineText = position == getNewestPosition()
    }

    private fun getNewestPosition(): Int {
        return getElements().indexOf(getElements().sortedBy { e -> e.getLapNumber() }.first())
    }
}
