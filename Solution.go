
package main

import "fmt"

type HashSet struct {
    container map[string]bool
}

var directedGraphRegionToParentRegion = map[string]string{}

func findSmallestRegion(regions [][]string, firstRegion string, secondRegion string) string {
    directedGraphRegionToParentRegion = createDirectedGraphRegionToParentRegion(regions)
    return findFirstCommonParentOfTwoRegions(firstRegion, secondRegion)
}

func findFirstCommonParentOfTwoRegions(firstRegion string, secondRegion string) string {
    stepFromFirstRegion := firstRegion
    stepFromSecondRegion := secondRegion

    visitedFromFirstRegion := HashSet{container: map[string]bool{}}
    visitedFromSecondRegion := HashSet{container: map[string]bool{}}
    visitedFromFirstRegion.container[firstRegion] = true
    visitedFromSecondRegion.container[secondRegion] = true

    firstCommonParentOfTwoRegions := ""

    for true {
        if visitedFromFirstRegion.container[stepFromSecondRegion] {
            firstCommonParentOfTwoRegions = stepFromSecondRegion
            break
        }
        if visitedFromSecondRegion.container[stepFromFirstRegion] {
            firstCommonParentOfTwoRegions = stepFromFirstRegion
            break
        }

        if _, contains := directedGraphRegionToParentRegion[stepFromFirstRegion]; contains {
            stepFromFirstRegion = directedGraphRegionToParentRegion[stepFromFirstRegion]
            visitedFromFirstRegion.container[stepFromFirstRegion] = true
        }
        if _, contains := directedGraphRegionToParentRegion[stepFromSecondRegion]; contains {
            stepFromSecondRegion = directedGraphRegionToParentRegion[stepFromSecondRegion]
            visitedFromSecondRegion.container[stepFromSecondRegion] = true
        }
    }

    return firstCommonParentOfTwoRegions
}

func createDirectedGraphRegionToParentRegion(regions [][]string) map[string]string {
    regionToParentRegion := map[string]string{}
    for _, groupOfRegions := range regions {
        parentRegion := groupOfRegions[0]
        for i := 1; i < len(groupOfRegions); i++ {
            regionToParentRegion[groupOfRegions[i]] = parentRegion
        }
    }
    return regionToParentRegion
}
