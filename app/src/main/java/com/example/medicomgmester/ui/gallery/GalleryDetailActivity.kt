package com.example.medicomgmester.ui.gallery

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.medicomgmester.R
import com.example.medicomgmester.extension.load
import com.example.medicomgmester.model.DataDoc
import kotlinx.android.synthetic.main.activity_gallery_detail.*
import kotlinx.android.synthetic.main.view_detail.*


class GalleryDetailActivity : AppCompatActivity() {

    companion object{
        const val Key = "KEY DATA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_detail)
        setDetail()
        setSendMail()
        setUpVdo()
    }

    private fun setDetail() {
        val detail: DataDoc? = intent.getParcelableExtra(Key)
        doc_topic.text = detail?.sub_name
        doc_detail.text = detail?.detailData
        doc_detail_mock.text = detail?.symptoms
        image_view_doc1.load(detail?.image)
        doc_detail_recommend.text = detail?.suggestion
        medic_image.load(detail?.image_medic)
    }

    private fun setSendMail(){
        card_medic.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto: Example@gmail.com")
            intent.putExtra(Intent.EXTRA_EMAIL, "test")
            intent.putExtra(Intent.EXTRA_SUBJECT, "Hello World")
            startActivity(intent)

        }
    }

    private fun setUpVdo(){
      /*  video_view.setVideoPath("")
        video_view.start()
      */
    }

}