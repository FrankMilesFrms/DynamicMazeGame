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
import utils.GameLaunchConfig
import utils.GameOperation
import java.util.*
import kotlin.random.Random


/**
 *
 * * Email : FrankMiles@qq.com
 * * Date  : 2024/07/11, 上午 10:49
 * @author Frms(Frank Miles)
 */

fun main()
{
	val gameMessage = GameMessage()

	val gameOperation = GameLaunchConfig(
		dimensionCount = 1,
		stepLength = 1,
		mapSizeProbability = 0.5f,
		gameMessage = gameMessage
	)

	val game = MainGame(gameOperation)

	gameMessage.addObserveNewMessage {
		System.err.println("消息：$it")
	}

	val scanner = Scanner(System.`in`)

	while (true)
	{
		println("输入两个数字，第一个数表示行动，第二个数表示行动方向")
		println("行动合法输入：1(前进) -1(后退) 其他(不变)")
		println("方向合法输入范围：[0, ${gameOperation.dimensionCount})")

		val input = scanner.nextInt()
		val dimension = scanner.nextInt()

		val state = when (input)
		{
			1    ->
			{
				game.operate(GameOperation.OperationType.FORWARD, dimension)
			}
			-1   ->
			{
				game.operate(GameOperation.OperationType.BACKWARD, dimension)
			}
			else ->
			{
				false
			}
		}

		if(state.not())
		{
			System.err.println("input=$input, 没有行动")
		}
	}

}