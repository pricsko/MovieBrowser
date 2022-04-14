package com.mbh.moviebrowser.util

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View

object AnimationUtils {

    fun getAlphaAnimatorOfViews(views: Array<View>, duration: Long): AnimatorSet {
        val animators = views.map { view ->
            ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
                .setDuration(duration)
        }
        val animatorSet = AnimatorSet()
        animatorSet.duration = duration
        animatorSet.playTogether(animators)
        return animatorSet
    }

}