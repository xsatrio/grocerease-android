package com.exal.grocerease.view.activity

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.exal.grocerease.R
import com.exal.grocerease.helper.manager.IntroManager
import com.github.appintro.AppIntroCustomLayoutFragment.Companion.newInstance
import com.github.appintro.AppIntro
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class IntroActivity : AppIntro() {
    @Inject
    lateinit var introManager: IntroManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        if (introManager.isIntroCompleted()) {
//            navigateToLanding()
//            return
//        }

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        addSlide(newInstance(R.layout.fragment_intro1))
        addSlide(newInstance(R.layout.fragment_intro2))
        addSlide(newInstance(R.layout.fragment_intro3))


        if (isDarkMode()) {
            setNextArrowColor(getColor(R.color.BlueLight))
            setColorSkipButton(getColor(R.color.BlueLight))
            setColorDoneText(getColor(R.color.BlueLight))

            setIndicatorColor(
                selectedIndicatorColor = getColor(R.color.BlueLight),
                unselectedIndicatorColor = getColor(R.color.BlueSecond)
            )
        } else {
            setNextArrowColor(getColor(R.color.Blue))
            setColorSkipButton(getColor(R.color.Blue))
            setColorDoneText(getColor(R.color.Blue))

            setIndicatorColor(
                selectedIndicatorColor = getColor(R.color.black),
                unselectedIndicatorColor = getColor(R.color.BlueLightSecond)
            )
        }
    }

    private fun navigateToLanding() {
        val intent = Intent(this, LandingActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun isDarkMode(): Boolean {
        return when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> true
            Configuration.UI_MODE_NIGHT_NO -> false
            else -> false
        }
    }

    public override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        introManager.setIntroCompleted()
        val intent = Intent(this, LandingActivity::class.java)
        startActivity(intent)
        finish()
    }

    public override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        introManager.setIntroCompleted()
        val intent = Intent(this, LandingActivity::class.java)
        startActivity(intent)
        finish()
    }
}