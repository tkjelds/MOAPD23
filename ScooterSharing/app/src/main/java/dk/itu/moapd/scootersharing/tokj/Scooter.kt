package dk.itu.moapd.scootersharing.tokj

class Scooter(var name: String, var location: String)
{
    override fun toString() : String {
        return "ScooterId = $name , ScooterLocation = $location"
    }
}