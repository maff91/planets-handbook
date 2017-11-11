package com.maff.planetshandbook.data

import android.support.annotation.NonNull
import com.google.gson.Gson
import hugo.weaving.DebugLog
import timber.log.Timber
import java.io.*

/**
 * Created by maff on 10/31/2017.
 */

class JsonRepository @DebugLog constructor(@NonNull inputStream: InputStream) : Repository
{
    private var planets: Map<String, Planet>
    private var categories: Map<String, Category>
    private var probes: Map<String, Probe>
//    private var probesByPlanet: MutableMap<String, MutableList<Probe>>
    private var parameters: MutableMap<String, Parameter>

    init
    {
        try {
            val db = Gson().fromJson(InputStreamReader(inputStream), JsonDatabase::class.java)

            planets = db.planets
            categories = db.categories
            probes = db.probes
        }
        catch (ex : Exception) {
            planets = LinkedHashMap()
            categories = LinkedHashMap()
            probes = LinkedHashMap()

            Timber.e(ex)
        }

        //probesByPlanet = LinkedHashMap()
        parameters = LinkedHashMap()

        // Perform mapping operations
        fillNames()
        fillParametersMap()
//        fillProbesPlanetMap()
    }

    private fun fillNames()
    {
        for(entry in planets) {
            entry.value.name = entry.key
        }

        for(entry in categories) {
            entry.value.name = entry.key
        }

        for(entry in probes) {
            entry.value.name = entry.key
        }
    }

    private fun fillParametersMap()
    {
        for(category in categories.values) {
            parameters.putAll(category.parameters)
        }
    }

//    private fun fillProbesPlanetMap()
//    {
//        probesByPlanet.putAll(planets.keys.map { Pair(it, ArrayList<Probe>()) })
//
//        for(probe in probes.values) {
//            for(planet in probe.planets) {
//                probesByPlanet[planet]?.add(probe)
//            }
//        }
//    }


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
    override fun getProbesByPlanet(name: String): Collection<Probe> {
        return probes.values.filter { it.name.equals(name, true) }
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
            for(param in category.parameters.values) {
                params.add(Pair(param, param.values[name] ?: continue))
            }

            // Add non-empty category
            if(params.size > 0) {
                result.add(PlanetCategory(category.name ?: continue, params))
            }
        }
        return result
    }

    // Support class representing database JSON file structure
    data class JsonDatabase(
            val planets: Map<String, Planet>,
            val categories: Map<String, Category>,
            val probes: Map<String, Probe>)
}