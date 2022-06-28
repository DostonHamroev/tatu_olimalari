package uz.hamroev.tatuolimalari

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatDelegate
import uz.hamroev.tatuolimalari.activity.HomeActivity
import uz.hamroev.tatuolimalari.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, 1200)

        startAnimation()

    }

    private fun startAnimation() {

        binding.titleName.animateText("TATU Olimalari")
        binding.titleName.setCharacterDeley(30)

        val animTeam = AnimationUtils.loadAnimation(this, R.anim.anim_intro_team)
        val animVersion = AnimationUtils.loadAnimation(this, R.anim.anim_intro_version)
        val animImage = AnimationUtils.loadAnimation(this, R.anim.anim_intro_image)

        binding.teamTv.startAnimation(animTeam)
        binding.versionTv.startAnimation(animVersion)
        binding.image.startAnimation(animImage)


    }
}