package jp.gr.java_conf.saka.multistopwatch.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter


abstract class CustomBaseAdapter<E> : BaseAdapter {
    private var context: Context
    private var elements: MutableList<E>
    private var inflater: LayoutInflater
    private var resource: Int = 0
    private var dropDownViewResource: Int = 0
    var isEnable: Boolean = false

    constructor(
        context: Context,
        elements: MutableList<E>,
        inflater: LayoutInflater,
        resource: Int
    ) : super() {
        this.context = context
        this.elements = elements
        this.inflater = inflater
        this.resource = resource
        this.isEnable = true
    }

    constructor(context: Context) : super() {
        this.context = context
        this.elements = ArrayList()
        this.inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        this.isEnable = true
    }

    constructor(context: Context, resource: Int) : super() {
        this.context = context
        this.elements = ArrayList()
        this.resource = resource
        this.inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        this.isEnable = true
    }

    override fun isEmpty(): Boolean {
        return elements.isEmpty()
    }

    override fun getCount(): Int {
        return elements.size
    }

    override fun getItem(position: Int): E {
        return elements[position]
    }

    override fun getItemId(position: Int): Long {
        // TODO
        return position.toLong()
    }

    override fun areAllItemsEnabled(): Boolean {
        return false
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v: View? = convertView
        if (v == null) {
            v = inflater.inflate(resource, null)
        }
        val elem = elements[position]

        return convertView(position, v!!, parent, elem)

    }

    override fun getDropDownView(position: Int, convertView: View, parent: ViewGroup): View {
        var v: View? = convertView
        if (v == null) {
            v = inflater.inflate(dropDownViewResource, null)
        }
        val elem = elements[position]

        return convertDropDownView(position, v!!, parent, elem)
    }

    protected abstract fun convertView(position: Int, v: View, parent: ViewGroup, elem: E): View

    protected fun convertDropDownView(position: Int, v: View, parent: ViewGroup, elem: E): View {
        return convertView(position, v, parent, elem)
    }

    fun setDropDownViewResource(dropDownViewResource: Int) {
        this.dropDownViewResource = dropDownViewResource
    }

    fun add(elem: E) {
        elements.add(elem)
    }

    fun addAll(elements: Collection<E>) {
        this.elements.addAll(elements)
    }

    fun remove(elem: E): Boolean {
        return elements.remove(elem)
    }

    fun remove(index: Int): E {
        return elements.removeAt(index)
    }

    fun removeLast(): E {
        return elements.removeAt(elements.size - 1)
    }

    fun clear() {
        elements.clear()
    }

    fun getElements(): List<E> {
        return elements
    }

    fun setElements(elements: MutableList<E>) {
        this.elements = elements
    }

}
