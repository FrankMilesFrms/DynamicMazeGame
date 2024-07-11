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

/**
 *
 * * Email : FrankMiles@qq.com
 * * Date  : 2024/07/11, 下午 12:42
 * @author Frms(Frank Miles)
 */
class GameMessage
{
	private val messageList = arrayListOf<String>()
	private val observers = hashSetOf<(String) -> Unit>()

	fun addObserveNewMessage(consumer: (String) -> Unit)
	{
		observers.add(consumer)
	}

	fun addMessage(message: String)
	{
		messageList.add(message)
		notifyObserver(message)
	}

	private fun notifyObserver(message: String)
	{
		observers.forEach {
			it(message)
		}
	}
}