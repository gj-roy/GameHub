package ca.on.hojat.gamenews.shared.core

import ca.on.hojat.gamenews.shared.domain.games.entities.Game
import com.paulrybitskyi.hiltbinder.BindType
import javax.inject.Inject

interface GameLikeCountCalculator {
    fun calculateLikeCount(game: Game): Int
}

@BindType
internal class GameLikeCountCalculatorImpl @Inject constructor() : GameLikeCountCalculator {

    override fun calculateLikeCount(game: Game): Int {
        val followerCount = (game.followerCount ?: 0)
        val hypeCount = (game.hypeCount ?: 0)
        val likeCount = (followerCount + hypeCount)

        return likeCount
    }
}
