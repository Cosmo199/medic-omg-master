package com.example.medicomgmester.ui.gallery.holder
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.medicomgmester.model.DataDoc
import kotlinx.android.synthetic.main.item_theme_data.view.*

class GalleryHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun onBind(data_doc: DataDoc) {
        itemView.apply {
            name_lesson.text = data_doc.name
            card_data_doc.setOnClickListener {
               itemView.context.startActivity(
                   Intent(itemView.context, GalleryDetailActivity::class.java)
                  .putExtra(GalleryDetailActivity.Key, data_doc))
          }
        }
    }

}