package utils

import GameMessage
import kotlin.random.Random

/**
 *
 *<p>
 * Email : FrankMiles@qq.com
 * Date  : 2024/07/11, 上午 11:01
 *</p>
 * @author Frms(Frank Miles)
 */
/**
 * Game launch config
 *
 * @property dimensionCount 维度
 * @property stepLength 步子长
 * @property gameMessage 信息源
 * @property mapSizeProbability 地图大小概率，越接近1，则越接近无限大小(但受机器性能影响)，越接近0则地图大小趋于0
 * @property seed 种子
 * @constructor Create empty Game launch config
 */
data class GameLaunchConfig(
	val dimensionCount: Int,
	var stepLength: Int = 1,
	var gameMessage: GameMessage,
	val mapSizeProbability : Float = 0.5f,
	val seed : Long = Random.nextLong()
)
