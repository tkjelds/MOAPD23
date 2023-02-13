package dk.itu.moapd.scootersharing.tokj.Model

import android.content.Context
import java.util.Random

class RidesDB private constructor(context: Context) {
    private val rides = ArrayList<Scooter>()
    private var currentRide: Scooter

    companion object : RidesDBHolder<RidesDB, Context>(::RidesDB)

    init {
        rides.add(Scooter("TestScooter1", "TestLocation1", randomDate()))
        rides.add(Scooter("TestScooter2", "TestLocation2", randomDate()))
        rides.add(Scooter("TestScooter3", "TestLocation3", randomDate()))
        currentRide = rides[0]
    }

    fun getRidesList(): List<Scooter> {
        return rides
    }

    fun addScooter(name: String, location: String) {
        rides.add(Scooter(name, location, System.currentTimeMillis()))
    }

    fun updateCurrentScooter(location: String) {
        currentRide.location = location
    }

    fun getCurrentScooter(): Scooter {
        return currentRide
    }

    fun getCurrentScooterInfo(): String {
        return currentRide.toString()
    }

    fun setCurrentScooter(scooter: Scooter) {
        currentRide = scooter
    }

    private fun randomDate(): Long {
        val random = Random()
        var now = System.currentTimeMillis()
        val year = random.nextDouble() * 1000 * 60 * 60 * 24 * 365
        return (now - year).toLong()
    }

}

open class RidesDBHolder<out T : Any, in A>(creator: (A) -> T) {
    private var creator: ((A) -> T)? = creator

    @Volatile
    private var instance: T? = null
    fun get(arg: A): T {
        val checkInstance = instance
        if (checkInstance != null)
            return checkInstance
        return synchronized(this) {
            3
            val checkInstanceAgain = instance
            if (checkInstanceAgain != null)
                checkInstanceAgain
            else {
                val created = creator!!(arg)
                instance = created
                creator = null
                created
            }
        }
    }
}