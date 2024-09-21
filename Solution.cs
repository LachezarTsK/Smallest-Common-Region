
using System;
using System.Collections.Generic;

public class Solution
{
    private Dictionary<string, string> directedGraphRegionToParentRegion;

    public string FindSmallestRegion(IList<IList<string>> regions, string firstRegion, string secondRegion)
    {
        directedGraphRegionToParentRegion = CreateDirectedGraphRegionToParentRegion(regions);
        return FindFirstCommonParentOfTwoRegions(firstRegion, secondRegion);
    }

    private string FindFirstCommonParentOfTwoRegions(string firstRegion, string secondRegion)
    {
        string stepFromFirstRegion = firstRegion;
        string stepFromSecondRegion = secondRegion;

        HashSet<string> visitedFromFirstRegion = new HashSet<string>();
        HashSet<string> visitedFromSecondRegion = new HashSet<string>();
        visitedFromFirstRegion.Add(firstRegion);
        visitedFromSecondRegion.Add(secondRegion);

        string firstCommonParentOfTwoRegions = "";

        while (true)
        {
            if (visitedFromFirstRegion.Contains(stepFromSecondRegion))
            {
                firstCommonParentOfTwoRegions = stepFromSecondRegion;
                break;
            }
            if (visitedFromSecondRegion.Contains(stepFromFirstRegion))
            {
                firstCommonParentOfTwoRegions = stepFromFirstRegion;
                break;
            }

            if (directedGraphRegionToParentRegion.ContainsKey(stepFromFirstRegion))
            {
                stepFromFirstRegion = directedGraphRegionToParentRegion[stepFromFirstRegion];
                visitedFromFirstRegion.Add(stepFromFirstRegion);
            }
            if (directedGraphRegionToParentRegion.ContainsKey(stepFromSecondRegion))
            {
                stepFromSecondRegion = directedGraphRegionToParentRegion[stepFromSecondRegion];
                visitedFromSecondRegion.Add(stepFromSecondRegion);
            }
        }

        return firstCommonParentOfTwoRegions;
    }

    private Dictionary<string, string> CreateDirectedGraphRegionToParentRegion(IList<IList<String>> regions)
    {
        Dictionary<string, string> regionToParentRegion = new Dictionary<string, string>();
        foreach (IList<string> groupOfRegions in regions)
        {
            string parentRegion = groupOfRegions[0];
            for (int i = 1; i < groupOfRegions.Count; ++i)
            {
                regionToParentRegion.Add(groupOfRegions[i], parentRegion);
            }
        }
        return regionToParentRegion;
    }
}
