
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class Solution {

    private lateinit var directedGraphRegionToParentRegion: HashMap<String, String>

    fun findSmallestRegion(regions: List<List<String>>, firstRegion: String, secondRegion: String): String {
        directedGraphRegionToParentRegion = createDirectedGraphRegionToParentRegion(regions)
        return findFirstCommonParentOfTwoRegions(firstRegion, secondRegion)
    }

    private fun findFirstCommonParentOfTwoRegions(firstRegion: String, secondRegion: String): String {
        var stepFromFirstRegion = firstRegion
        var stepFromSecondRegion = secondRegion

        val visitedFromFirstRegion = HashSet<String>()
        val visitedFromSecondRegion = HashSet<String>()
        visitedFromFirstRegion.add(firstRegion)
        visitedFromSecondRegion.add(secondRegion)

        val firstCommonParentOfTwoRegions: String

        while (true) {
            if (visitedFromFirstRegion.contains(stepFromSecondRegion)) {
                firstCommonParentOfTwoRegions = stepFromSecondRegion
                break
            }
            if (visitedFromSecondRegion.contains(stepFromFirstRegion)) {
                firstCommonParentOfTwoRegions = stepFromFirstRegion
                break
            }

            if (directedGraphRegionToParentRegion.containsKey(stepFromFirstRegion)) {
                stepFromFirstRegion = directedGraphRegionToParentRegion[stepFromFirstRegion]!!
                visitedFromFirstRegion.add(stepFromFirstRegion)
            }
            if (directedGraphRegionToParentRegion.containsKey(stepFromSecondRegion)) {
                stepFromSecondRegion = directedGraphRegionToParentRegion[stepFromSecondRegion]!!
                visitedFromSecondRegion.add(stepFromSecondRegion)
            }
        }

        return firstCommonParentOfTwoRegions
    }

    private fun createDirectedGraphRegionToParentRegion(regions: List<List<String>>): HashMap<String, String> {
        val regionToParentRegion = HashMap<String, String>()
        for (groupOfRegions in regions) {
            val parentRegion = groupOfRegions[0]
            for (i in 1..<groupOfRegions.size) {
                regionToParentRegion[groupOfRegions[i]] = parentRegion
            }
        }
        return regionToParentRegion
    }
}
