package com.example.filterwords.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.filterwords.DetailActivity
import com.example.filterwords.R

class LetterAdapter: RecyclerView.Adapter<LetterAdapter.LetterViewHolder>() {

    private val list = ('A').rangeTo('Z').toList()

    class LetterViewHolder( val view: View ): RecyclerView.ViewHolder(view) {
        val button: Button = view.findViewById(R.id.button_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        layout.accessibilityDelegate = Accessibility
        return LetterViewHolder(layout)
    }

    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
        val item = list[position] //list.get(position)
        holder.button.text = item.toString()

        // Needed to call startActivity
        val context = holder.view.context

        //Conf. el intent en el onClickListener para cada letra
        holder.button.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra( DetailActivity.LETTER, holder.button.text.toString())
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = list.size


    // Setup custom accessibility delegate to set the text read with an accessibility service
    // Conf. el delegado de accesibilidad personalizado para conf. el texto leído con un servicio de accesibilidad
    companion object Accessibility : View.AccessibilityDelegate() {
        override fun onInitializeAccessibilityNodeInfo(host: View, info: AccessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(host, info)
            // With `null` as the second argument to [AccessibilityAction], the
            // accessibility service announces "double tap to activate".
            // If a custom string is provided,
            // it announces "double tap to <custom string>".
            val customString = host.context?.getString(R.string.look_up_words)
            val customClick =
                AccessibilityNodeInfo.AccessibilityAction(
                    AccessibilityNodeInfo.ACTION_CLICK,
                    customString
                )
            info.addAction(customClick)
        }
    }
}


