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
 *
 * * Email : FrankMiles@qq.com
 * * Date  : 2024/07/11, 上午 11:05
 * @author Frms(Frank Miles)
 */
interface GameOperation
{
	/**
	 * Operation type
	 * 对于坐标的一个值来说，只可能对之进行二元计算，[STAY]表示不变。
	 * @constructor Create empty Operation type
	 */
	enum class OperationType {
		FORWARD, BACKWARD, STAY
	}

	companion object {
		fun getOperationTypeValue(operationType: OperationType) = when(operationType) {
			OperationType.FORWARD -> 1
			OperationType.BACKWARD -> -1

			else -> 0
		}
	}

	fun operate(operationType: OperationType, index: Int): Boolean
}