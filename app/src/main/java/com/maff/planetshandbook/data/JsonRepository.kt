package com.maff.planetshandbook.data

import androidx.annotation.NonNull
import com.google.gson.Gson
import hugo.weaving.DebugLog
import timber.log.Timber
import java.io.*

/**
 * Created by maff on 10/31/2017.
 */

class JsonRepository @DebugLog constructor(@NonNull inputStream: InputStream) : Repository
{
    private val planets: Map<String, Planet>
    private val categories: Map<String, Category>
    private val probes: Map<String, Probe>
//    private var probesByPlanet: MutableMap<String, MutableList<Probe>>
    private val parameters: MutableMap<String, Parameter>

    init
    {
        planets = LinkedHashMap()
        categories = LinkedHashMap()
        probes = LinkedHashMap()

        try {
            val db = Gson().fromJson(InputStreamReader(inputStream), JsonDatabase::class.java)

            for(planet in db.planets) {
                planets.put(planet.name, planet)
            }

            for(category in db.categories) {
                categories.put(category.name, category)
            }

            for(probe in db.probes) {
                probes.put(probe.name, probe)
            }
        }
        catch (ex : Exception) {
            Timber.e(ex)
        }

        parameters = LinkedHashMap()
        fillParametersMap()
    }

    private fun fillParametersMap()
    {
        for(category in categories.values) {
            category.parameters.forEach { parameters.put(it.name, it) }
        }
    }

    override fun getAllPlanets(): Collection<Planet> {
        return planets.values
    }

    override fun getPlanet(name: String): Planet? {
        return planets[name]
    }

    override fun getProbe(name: String): Probe? {
        return probes[name]
    }

    override fun getParameter(name: String): Parameter? {
        return parameters[name]
    }

    @DebugLog
    override fun getProbesByPlanet(planetName: String): Collection<Probe> {
        return probes.values.filter { it.planets.contains(planetName) }
    }

    @DebugLog
    override fun getPlanetsByProbe(name: String): Collection<Planet> {
        val result = ArrayList<Planet>()
        val planetNames = probes[name]?.planets

        if(planetNames != null) {
            for (planetName in planetNames) {
                result.add(planets[planetName] ?: continue)
            }
        }

        return result
    }

    @DebugLog
    override fun getParametersByPlanet(name: String) : Collection<PlanetCategory> {
        val result = ArrayList<PlanetCategory>()

        for(category in categories.values)
        {
            // Remap the category params with their values
            val params = ArrayList<Pair<Parameter, Double>>()
            for(param in category.parameters) {
                params.add(Pair(param, param.values[name] ?: continue))
            }

            // Add non-empty category
            if(params.size > 0) {
                result.add(PlanetCategory(category.name, params))
            }
        }
        return result
    }

    // Support class representing database JSON file structure
    data class JsonDatabase(
            val planets: List<Planet>,
            val categories: List<Category>,
            val probes: List<Probe>)
}