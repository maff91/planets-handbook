package com.maff.planetshandbook.data

/**
 * Created by maff on 10/31/2017.
 */

interface Repository
{
    fun getAllPlanets(): Collection<Planet>

    fun getPlanet(name: String): Planet?
    fun getProbe(name: String): Probe?
    fun getParameter(name: String) : Parameter?

    fun getProbesByPlanet(name: String) : Collection<Probe>
    fun getPlanetsByProbe(name: String): Collection<Planet>
    fun getParametersByPlanet(name: String): Collection<PlanetCategory>
}

