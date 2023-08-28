package ca.on.hojat.gamehub.feature_info.domain

import ca.on.hojat.gamehub.core.domain.entities.Game
import com.paulrybitskyi.hiltbinder.BindType
import javax.inject.Inject

interface LikeCountCalculator {
    fun calculateLikeCount(game: Game): Int
}

@BindType
internal class LikeCountCalculatorImpl @Inject constructor() : LikeCountCalculator {

    override fun calculateLikeCount(game: Game): Int {
        val followerCount = (game.followerCount ?: 0)
        val hypeCount = (game.hypeCount ?: 0)

        return (followerCount + hypeCount)
    }
}
