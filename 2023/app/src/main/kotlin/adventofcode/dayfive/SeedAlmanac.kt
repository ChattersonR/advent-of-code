package adventofcode.dayfive

class SeedAlmanac(input: List<String>) {
    class MappingRange(destStartIn: Long, sourceRangeIn: LongRange){
        val destStart: Long = destStartIn
        val sourceRange: LongRange = sourceRangeIn
    }
    /*

    val seedToSoil: List<MappingRange>
    val soilToFertilizer: List<MappingRange>
    val fertilizerToWater: List<MappingRange>
    val waterToLight: List<MappingRange>
     */
    private val seeds: List<Long>
    private val seedsRanges: List<LongRange>
    private val mappings: List<List<MappingRange>>
    init {
        val mutableSeeds: MutableList<Long> = mutableListOf()
        val mutableSeedRanges: MutableList<LongRange> = mutableListOf()
        val mutableMappings: MutableList<List<MappingRange>> = mutableListOf()

        var mappingList: MutableList<MappingRange> = mutableListOf()
        input.forEach {line ->
            if(line.startsWith("seeds")) {
                val seedLine = line.substringAfter(":").split(" ").filter { it.isNotEmpty() }.map { it.toLong() }

                mutableSeeds.addAll( seedLine )

                for(i in seedLine.indices.step(2) ) {
                    mutableSeedRanges.add(seedLine[i]..<seedLine[i]+seedLine[i+1])
                }

            } else if(line.contains("map:")) {
                if(mappingList.isNotEmpty()) mutableMappings.add(mappingList)
                mappingList = mutableListOf()
            } else if(line.isNotBlank()) {
                val mapLine = line.split(" ")
                mappingList.add(MappingRange(mapLine[0].toLong(),
                    mapLine[1].toLong()..<mapLine[1].toLong()+mapLine[2].toLong()
                ))
            }
        }
        if(mappingList.isNotEmpty()) mutableMappings.add(mappingList)

        seeds = mutableSeeds
        seedsRanges = mutableSeedRanges
        mappings = mutableMappings
    }

    private val locationCalculation: (Long, List<MappingRange>) -> Long = { position, mappingRanges ->
        mappingRanges
            .filter { position in it.sourceRange }
            .ifEmpty { listOf(MappingRange(position, position..position)) }
            .fold(position) {acc2, mapping -> calculateMapping(mapping, acc2)}
    }
    private fun calculateMapping(mappingRange: MappingRange, input: Long): Long {
        return mappingRange.destStart + (input - mappingRange.sourceRange.first)
    }
    fun lowestLocation(): Long? {
        return seeds.minOfOrNull { seed -> mappings.fold(seed, locationCalculation) }
    }

    fun lowestLocationOfRanges(): Long {
        val sortedMappings = mappings.map { one -> one.sortedBy { two -> two.sourceRange.first } }

        var intermediateRanges = seedsRanges
        for(mapping in sortedMappings) {
            val tmpRanges: MutableList<LongRange> = mutableListOf()
            for(range in intermediateRanges) {
                var testRange: LongRange? = range
                var index = 0
                while(testRange != null && index < mapping.size) {
                    if( testRange.first in mapping[index].sourceRange ) {
                        if( testRange.last in mapping[index].sourceRange) {
                            tmpRanges.add(calculateMapping(mapping[index], testRange.first)..calculateMapping(mapping[index], testRange.last))
                            testRange = null
                        } else {
                            tmpRanges.add(calculateMapping(mapping[index], testRange.first)..calculateMapping(mapping[index], mapping[index].sourceRange.last))
                            testRange = mapping[index].sourceRange.last+1..testRange.last
                        }
                    } else if (testRange.last in mapping[index].sourceRange) {
                        tmpRanges.add(calculateMapping(mapping[index], mapping[index].sourceRange.first)..calculateMapping(mapping[index], testRange.last))
                        testRange = testRange.first..<mapping[index].sourceRange.first
                    }
                    index++
                }
                if(testRange != null) {
                    tmpRanges.add(testRange)
                }
            }
            intermediateRanges = tmpRanges
        }
        return intermediateRanges.minOf { it.first }
        //return seedsRanges.flatMap { seedRange -> seedRange.map { seed -> mappings.fold(seed, locationCalculation) } }.min()
    }

}

