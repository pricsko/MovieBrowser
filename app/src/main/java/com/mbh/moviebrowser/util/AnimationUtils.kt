package com.mbh.moviebrowser.util

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View

object AnimationUtils {

    private const val DEFAULT_ALPHA_START = 0f
    private const val DEFAULT_ALPHA_END = 1f

    fun getAlphaAnimatorOfViews(
        views: Array<View>,
        duration: Long,
        startValue: Float = DEFAULT_ALPHA_START,
        endValue: Float = DEFAULT_ALPHA_END
    ): AnimatorSet {
        val animators = views.map { view ->
            ObjectAnimator.ofFloat(view, "alpha", startValue, endValue)
                .setDuration(duration)
        }
        val animatorSet = AnimatorSet()
        animatorSet.duration = duration
        animatorSet.playTogether(animators)
        return animatorSet
    }

}