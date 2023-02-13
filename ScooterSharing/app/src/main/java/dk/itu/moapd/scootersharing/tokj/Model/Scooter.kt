/**
 * MIT License

Copyright (c) 2023 Tore Asbjørn Tonnisen Kjelds

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package dk.itu.moapd.scootersharing.tokj.Model

import java.text.SimpleDateFormat
import java.util.Date

/**
 * A data class of a Scooter.
 *
 * This is only used for storing data, and providing a simple toString method.
 * @param name Represents the name of the scooter
 * @param location Represents the location of the scooter
 */
data class Scooter(val name: String, var location: String, var timestamp: Long) {
    /**
     * @return Returns a formated string the of the scooter.
     */
    override fun toString(): String {
        return "ScooterId = $name , ScooterLocation = $location, TimeStamp = ${convertLongToTime()}."
    }

    fun convertLongToTime(): String {
        val date = Date(this.timestamp)
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm")
        return format.format(date)
    }
}