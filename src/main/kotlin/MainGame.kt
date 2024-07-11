/*
 * Copyright (C) 2023 - 2024 Frms, All Rights Reserved.
 * This file is part of DynamicMazeGame.
 *
 * DynamicMazeGame is free software:
 * you can redistribute it and/or modify it under the terms of the GNU General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * DynamicMazeGame is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with DynamicMazeGame.
 * If not, see <https://www.gnu.org/licenses/>.
 */

import utils.Coordinates
import utils.GameLaunchConfig
import utils.GameOperation
import kotlin.random.Random


/**
 *
 * * Email : FrankMiles@qq.com
 * * Date  : 2024/07/11, 上午 10:58
 * @author Frms(Frank Miles)
 */
class MainGame(
	gameLaunchConfig: GameLaunchConfig
): GameOperation
{
	private val player = Coordinates(gameLaunchConfig.dimensionCount)
	private val mGameOperation = gameLaunchConfig
	private val randomCreator = Random(mGameOperation.seed)

	override fun operate(operationType: GameOperation.OperationType, index: Int): Boolean
	{
		if(illegalOperate(index)) {
			return false
		}

		val value = GameOperation.getOperationTypeValue(operationType) * mGameOperation.stepLength

		val state = coreOperation(index, value)

		if(state)
		{
			changePotentialTrack()

			playerTrackSet.add(player.getCloneList())

			mGameOperation.gameMessage.addMessage(
				"玩家已经到达。$player"
			)
		}
		return state
	}

	private fun changePotentialTrack()
	{
		deletePotentialTrack()

		val native = player.getCloneList()
		val direction = arrayOf(-1, 1)

		repeat(player.dimensionCount)
		{
			direction.forEach { arrow ->
				val new = native.toMutableList()
				new[it] += arrow * mGameOperation.stepLength

				if(isNewLane(new)) {
					potentialTrackSet.add(new)
				}
			}
		}
	}

	private fun deletePotentialTrack()
	{
		if(potentialTrackSet.contains(player.getCloneList()))
		{
			potentialTrackSet.remove(player.getCloneList())
		}
	}

	private fun isNewLane(new: List<Int>): Boolean
	{
		return playerTrackSet.contains(new).not()
				&& wallTrackSet.contains(new).not()
	}

	private val playerTrackSet = hashSetOf<List<Int>>()

	private val wallTrackSet = hashSetOf<List<Int>>()

	private val potentialTrackSet = hashSetOf<List<Int>>()


	private fun coreOperation(index: Int, value: Int): Boolean
	{
		player.add(index, value)

		if(isOldPlayerPath(player)) {
			return true
		}

		if(isWall(player)) {
			player.undo()
			tellsPlayerThatWallBlocking()
			return false
		}

		if(mustBePassable()) {
			return true
		}

		val state =  dynamicCreate()

		if(state.not()) {
			wallTrackSet.add(player.getCloneList())
			tellsPlayerThatWallBlocking()
			player.undo()

		}

		return state
	}

	private fun dynamicCreate(): Boolean
	{
		if (randomCreator.nextFloat() >= mGameOperation.mapSizeProbability)
		{
			// 胜利条件
			mGameOperation.gameMessage.addMessage(
				"已经到达终点，终点不止一条，你可以继续探索！"
			)
			return true
		}

		val state = randomCreator.nextFloat() < mGameOperation.mapSizeProbability

		return state
	}

	private fun mustBePassable(): Boolean
	{
		return potentialTrackSet.isEmpty()
	}

	private fun tellsPlayerThatWallBlocking()
	{
		mGameOperation.gameMessage.addMessage(
			"${player}存在墙壁，无法操作！"
		)
	}

	private fun isOldPlayerPath(player: Coordinates): Boolean
	{
		return playerTrackSet.contains(player.getCloneList())
	}

	private fun isWall(player: Coordinates): Boolean
	{
		return wallTrackSet.contains(player.getCloneList())
	}

	private fun illegalOperate(index: Int): Boolean
	{
		return (index in (0 until mGameOperation.dimensionCount)).not()
	}


}