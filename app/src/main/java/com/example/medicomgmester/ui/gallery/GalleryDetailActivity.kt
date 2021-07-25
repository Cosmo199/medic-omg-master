package com.example.medicomgmester.ui.gallery
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.medicomgmester.R
import com.example.medicomgmester.extension.load
import com.example.medicomgmester.model.DataDoc
import com.example.medicomgmester.ui.medic.MenuActivity
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
        //setSendMail()
        setEvent()
    }

    private fun setDetail() {
        val detail: DataDoc? = intent.getParcelableExtra(Key)
        name_topic_1.text = detail?.name_topic_1
        name_topic_2.text = detail?.name_topic_2
        name_topic_3.text = detail?.name_topic_3
        name_topic_4.text = detail?.name_topic_4
        name_topic_5.text = detail?.name_topic_5
        name_topic_6.text = detail?.name_topic_6
        name_topic_7.text = detail?.name_topic_7
        detail_1.text = detail?.detail_1
        detail_2.text = detail?.detail_2
        detail_3.text = detail?.detail_3
        detail_4.text = detail?.detail_4
        detail_5.text = detail?.detail_5
        detail_6.text = detail?.detail_6
        detail_7.text = detail?.detail_7
        image_view_doc1.load(detail?.image)
        image_view_doc2.load(detail?.image_2)
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

    private fun setEvent(){
        action_back_to_home.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }


}