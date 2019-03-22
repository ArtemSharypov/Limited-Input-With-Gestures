package com.omimi.limitedinput

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.paolorotolo.appintro.AppIntro
import com.github.paolorotolo.appintro.AppIntroFragment
import com.github.paolorotolo.appintro.model.SliderPage

class TutorialIntro : AppIntro() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var introSlider = SliderPage()
        introSlider.title = "Gesture Overview"
        introSlider.description = "Quick overview of the gestures available that will allow you to do actions without having to press a button"

        addSlide(AppIntroFragment.newInstance(introSlider))

        var openNavGesture = SliderPage()
        openNavGesture.title = "Opening the Navigation Drawer"
        openNavGesture.description = "Do the shown gesture above, to open the Navigation Drawer (Left side menu) "
        openNavGesture.imageDrawable = R.drawable.l_gesture

        addSlide(AppIntroFragment.newInstance(openNavGesture))

        var openOptionsMenuGesture = SliderPage()
        openOptionsMenuGesture.title = "Opening the Toolbar Options Menu"
        openOptionsMenuGesture.description = "Do the shown gesture above, to open the Toolbar Options Menu as a Dialog"
        openOptionsMenuGesture.imageDrawable = R.drawable.reverse_l

        addSlide(AppIntroFragment.newInstance(openOptionsMenuGesture))

        var openTab1BottomNav = SliderPage()
        openTab1BottomNav.title = "Switching to the first tab of the Bottom Nav Bar"
        openTab1BottomNav.description = "Do the shown gesture above, to switch to the first tab"
        openTab1BottomNav.imageDrawable = R.drawable.open_right

        addSlide(AppIntroFragment.newInstance(openTab1BottomNav))

        var openTab2BottomNav = SliderPage()
        openTab2BottomNav.title = "Switching to the second tab of the Bottom Nav Bar"
        openTab2BottomNav.description = "Do the shown gesture above, to switch to the second tab"
        openTab2BottomNav.imageDrawable = R.drawable.open_bottom

        addSlide(AppIntroFragment.newInstance(openTab2BottomNav))

        var openTab3BottomNav = SliderPage()
        openTab3BottomNav.title = "Switching to the third tab of the Bottom Nav Bar"
        openTab3BottomNav.description = "Do the shown gesture above, to switch to the third tab"
        openTab3BottomNav.imageDrawable = R.drawable.closed

        addSlide(AppIntroFragment.newInstance(openTab3BottomNav))

        var openTab4BottomNav = SliderPage()
        openTab4BottomNav.title = "Switching to the fourth tab of the Bottom Nav Bar"
        openTab4BottomNav.description = "Do the shown gesture above, to switch to the fourth tab"
        openTab4BottomNav.imageDrawable = R.drawable.open_top

        addSlide(AppIntroFragment.newInstance(openTab4BottomNav))

        var openTab5BottomNav = SliderPage()
        openTab5BottomNav.title = "Switching to the fifth tab of the Bottom Nav Bar"
        openTab5BottomNav.description = "Do the shown gesture above, to switch to the fifth tab"
        openTab5BottomNav.imageDrawable = R.drawable.open_left

        addSlide(AppIntroFragment.newInstance(openTab5BottomNav))
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        finish()
    }
}