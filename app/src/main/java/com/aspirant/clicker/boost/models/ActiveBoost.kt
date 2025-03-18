package com.aspirant.clicker.boost.models
import android.content.SharedPreferences
import com.aspirant.clicker.boost.AbsBoost
import kotlin.math.ceil

open class ActiveBoost: AbsBoost() {
    var inc: Long = 0

    companion object {
        const val PRICE_COEFFICIENT = 2.0
        const val INC_COEFFICIENT = 1.5

        private var shared: SharedPreferences? = null
        private var sharedEditor: SharedPreferences.Editor? = null

        fun init(shared: SharedPreferences) {
            this.shared = shared
            sharedEditor = shared.edit()
        }

        fun load(id: Int): ActiveBoost {
            val gettedTitle = shared?.getString("boost_${id}_title", null)
            if (gettedTitle != null) {
                val instance = ActiveBoost()

                instance.id = id
                instance.title = gettedTitle
                instance.inc = shared?.getLong("boost_${id}_inc", 1)!!
                instance.price = shared?.getLong("boost_${id}_price", 1)!!
                instance.level = shared?.getInt("boost_${id}_level", 0)!!
                return instance
            } else {
                return when (id) {
                    0 -> DefaultActiveBoost0
                    1 -> DefaultActiveBoost1
                    2 -> DefaultActiveBoost2
                    3 -> DefaultActiveBoost3
                    else -> throw Exception("Boost with id=$id not found")
                }
            }
        }
    }

    override fun buy() {
        if (shared!!.getLong("score", 0) < price)
            return
        apply()
        level++
        price = ceil(price * PRICE_COEFFICIENT).toLong()
        inc = ceil(inc * INC_COEFFICIENT).toLong()
        save()
    }

    override fun save() {
        sharedEditor?.putString("boost_${id}_title", title)
        sharedEditor?.putLong("boost_${id}_inc", inc)
        sharedEditor?.putLong("boost_${id}_price", price)
        sharedEditor?.putInt("boost_${id}_level", level)
        sharedEditor?.commit()
    }

    override fun apply() {
        val oldInc = shared?.getLong("inc", 1) ?: 1
        val oldScore = shared?.getLong("score", 0) ?: 0
        sharedEditor?.putLong("inc", oldInc + inc)
        sharedEditor?.putLong("score", oldScore - price)
        sharedEditor?.commit()
    }
}