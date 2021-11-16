/*
 * Copyright 2021 Paul Rybitskyi, paul.rybitskyi.work@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.paulrybitskyi.gamedge.commons.ui.widgets.games

sealed class GamesUiState {
    data class Empty(val iconId: Int, val title: String) : GamesUiState()
    object Loading : GamesUiState()
    data class Result(val items: List<GameModel>) : GamesUiState()
}

data class GamesViewState(
    val isLoading: Boolean,
    val infoIconId: Int,
    val infoTitle: String,
    val games: List<GameModel>,
)

val GamesViewState.isInEmptyState: Boolean
    get() = (!isLoading && games.isEmpty())

val GamesViewState.isInLoadingState: Boolean
    get() = (isLoading && games.isEmpty())

val GamesViewState.isInRefreshingState: Boolean
    get() = (isLoading && games.isNotEmpty())

val GamesViewState.isInSuccessState: Boolean
    get() = games.isNotEmpty()
