
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {

    private Map<String, String> directedGraphRegionToParentRegion;

    public String findSmallestRegion(List<List<String>> regions, String firstRegion, String secondRegion) {
        directedGraphRegionToParentRegion = createDirectedGraphRegionToParentRegion(regions);
        return findFirstCommonParentOfTwoRegions(firstRegion, secondRegion);
    }

    private String findFirstCommonParentOfTwoRegions(String firstRegion, String secondRegion) {
        String stepFromFirstRegion = firstRegion;
        String stepFromSecondRegion = secondRegion;

        Set<String> visitedFromFirstRegion = new HashSet<>();
        Set<String> visitedFromSecondRegion = new HashSet<>();
        visitedFromFirstRegion.add(firstRegion);
        visitedFromSecondRegion.add(secondRegion);

        String firstCommonParentOfTwoRegions = "";

        while (true) {
            if (visitedFromFirstRegion.contains(stepFromSecondRegion)) {
                firstCommonParentOfTwoRegions = stepFromSecondRegion;
                break;
            }
            if (visitedFromSecondRegion.contains(stepFromFirstRegion)) {
                firstCommonParentOfTwoRegions = stepFromFirstRegion;
                break;
            }

            if (directedGraphRegionToParentRegion.containsKey(stepFromFirstRegion)) {
                stepFromFirstRegion = directedGraphRegionToParentRegion.get(stepFromFirstRegion);
                visitedFromFirstRegion.add(stepFromFirstRegion);
            }
            if (directedGraphRegionToParentRegion.containsKey(stepFromSecondRegion)) {
                stepFromSecondRegion = directedGraphRegionToParentRegion.get(stepFromSecondRegion);
                visitedFromSecondRegion.add(stepFromSecondRegion);
            }
        }

        return firstCommonParentOfTwoRegions;
    }

    private Map<String, String> createDirectedGraphRegionToParentRegion(List<List<String>> regions) {
        Map<String, String> regionToParentRegion = new HashMap<>();
        for (List<String> groupOfRegions : regions) {
            String parentRegion = groupOfRegions.get(0);
            for (int i = 1; i < groupOfRegions.size(); ++i) {
                regionToParentRegion.put(groupOfRegions.get(i), parentRegion);
            }
        }
        return regionToParentRegion;
    }
}
