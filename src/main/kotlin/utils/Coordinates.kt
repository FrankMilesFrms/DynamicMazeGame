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

package utils

/**
 * 玩家位置
 * * Email : FrankMiles@qq.com
 * * Date  : 2024/07/11, 上午 10:50
 * @author Frms(Frank Miles)
 */
 
data class Coordinates(
	val dimensionCount: Int
) {
	private var undoValue = 0 to 0
	private var canUndo = false

	private val position = arrayListOf<Int>()

	init {
		position.ensureCapacity(dimensionCount)
		repeat(dimensionCount)
		{
			position.add(it, 0)
		}
	}

	fun add(index: Int, value: Int) {
		position[index] += value

		canUndo = true
		undoValue = index to value
	}

	/**
	 * Returns a new [MutableList] filled with all elements of this collection.
	 */
	fun getCloneList(): List<Int> {
		return position.toMutableList()
	}

	fun get(index: Int) = position[index]

	fun undo()
	{
		if (canUndo) {
			position[undoValue.first] = undoValue.second
			canUndo = false
		}
	}

	override fun toString(): String
	{
		val builder = StringBuilder("位置：")
		position.forEach {
			builder.append(it.toString()).append(',')
		}

		builder.deleteCharAt(builder.length - 1)

		return builder.toString()
	}
}