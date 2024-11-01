package org.powbot.krulvis.dagannothkings

import org.powbot.api.Tile
import org.powbot.api.rt4.GameObject
import org.powbot.api.rt4.Npc
import org.powbot.api.rt4.Objects
import org.powbot.api.rt4.Prayer
import org.powbot.krulvis.api.extensions.items.Item.Companion.RARE_DROP_TABLE
import org.powbot.krulvis.api.extensions.Timer
import org.powbot.krulvis.api.extensions.requirements.EquipmentRequirement

object Data {

	const val SAFESPOT_REX = "SafespotRex"
	const val KILL_PREFIX_OPTION = "Kill"
	const val EQUIPMENT_PREFIX_OPTION = "Equipment"
	const val INVENTORY_OPTION = "Inventory"
	const val OFFENSIVE_PRAY_PREFIX_OPTION = "OffensivePrayer"
	const val HEAL_ON_SPINOLYP_OPTION = "Heal on Spinolyp"
	const val HEAL_GEAR_OPTION = "Spinolyp Equipment"

	val KING_DEATH_ANIM = 2856
	val PEEK_TILE = Tile(1917, 4363, 0)
	val ROOT_TILE = Tile(1918, 4366, 0)
	val KINGS_LADDER_UP_TILE = Tile(1911, 4367, 0)

	fun getRoot() = Objects.stream(ROOT_TILE, GameObject.Type.INTERACTIVE).name("Root").first()
	fun getKingsLadderUp() =
		Objects.stream(KINGS_LADDER_UP_TILE, GameObject.Type.INTERACTIVE).name("Kings' ladder").first()

	fun getKingsLadderDown() =
		Objects.stream().type(GameObject.Type.INTERACTIVE).within(30).name("Kings' ladder").action("Climb-up").first()

	enum class King(
		val offensiveAnim: Int,
		val protectionPrayer: Prayer.Effect,
		var offensivePrayer: Prayer.Effect?,
		var npc: Npc,
		var equipment: List<EquipmentRequirement>,
		var killTile: Tile,
		var kill: Boolean
	) {
		Prime(2854, Prayer.Effect.PROTECT_FROM_MAGIC, Prayer.Effect.EAGLE_EYE, Npc.Nil, emptyList(), Tile.Nil, false),
		Supreme(2855, Prayer.Effect.PROTECT_FROM_MISSILES, Prayer.Effect.PIETY, Npc.Nil, emptyList(), Tile.Nil, false),
		Rex(2853, Prayer.Effect.PROTECT_FROM_MELEE, Prayer.Effect.MYSTIC_MIGHT, Npc.Nil, emptyList(), Tile.Nil, false),
		;

		val respawnTimer = Timer(90 * 1000)

		companion object {
			fun Npc.king() = values().firstOrNull { it.name.lowercase() in name.lowercase() }
		}
	}

	val LOOT = arrayOf(
		"Berserker ring",
		"Warrior ring",
		"Archer ring",
		"Seers ring",
		"Dragon axe",
		"Dagannoth bones",
		"Rock-shell plate",
		"Rock-shell legs",
		"Iron ore",
		"Coal",
		"Torstol seed",
		"Seercull",
		"Mithril ore",
		"Steel bar",
		*RARE_DROP_TABLE
	)
}