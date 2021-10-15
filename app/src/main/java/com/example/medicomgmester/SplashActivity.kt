package com.example.medicomgmester
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast

class SplashActivity : AppCompatActivity() {
    private val handler: Handler = Handler()
    private lateinit var runner: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        //checkDataPreferences()
        runner = Runnable {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

   private fun checkDataPreferences(){
       runner = Runnable {
           val share = getSharedPreferences("LOGIN_DATA", MODE_PRIVATE)
           var i: String? = share.getString("name", null)
           if(i === null || i == ""){
               val intent = Intent(this, LoginActivity::class.java)
               startActivity(intent)
               finish()
           }else{
               val intent = Intent(this, MenuActivity::class.java)
               startActivity(intent)
               finish()
               Toast.makeText(this, "คุณลงทะเบียนด้วยชื่อ : $i", Toast.LENGTH_SHORT).show()
           }
       }
    }

    public override fun onResume() {
        super.onResume()
        handler.postDelayed(runner, 3000)
    }

    public override fun onStop() {
        super.onStop()
        handler.removeCallbacks(runner)
    }

}