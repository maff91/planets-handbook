package com.maff.planetshandbook.data

/**
 * Created by maff on 11/5/2017.
 */

object FakeData
{
    fun getPlanet() : Planet
    {
        return Planet("Earth", "EA", "https://en.wikipedia.org/wiki/Earth")
    }

    fun getPlanets() : List<Planet>
    {
        return listOf(getPlanet())
    }

    fun getParams() : List<PlanetCategory>
    {
        val param = getParam()
        val category = PlanetCategory("General", listOf(Pair(param, 1.0)))

        return listOf(category)
    }

    fun getProbe() : Probe
    {
       return Probe(
               "Cassini",
               "https://en.wikipedia.org/wiki/Cassini%E2%80%93Huygens",
               listOf("US"),
               listOf("Jupiter", "Saturn"))
    }

    fun getProbes() : List<Probe>
    {
        return listOf(getProbe())
    }

    fun getParam(): Parameter
    {
        return Parameter("Size", "%d", hashMapOf(Pair("Earth", 1.0)))
    }
}