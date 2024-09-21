
function findSmallestRegion(regions: string[][], firstRegion: string, secondRegion: string): string {
    this.directedGraphRegionToParentRegion = createDirectedGraphRegionToParentRegion(regions);
    return findFirstCommonParentOfTwoRegions(firstRegion, secondRegion);
};

function findFirstCommonParentOfTwoRegions(firstRegion: string, secondRegion: string): string {
    let stepFromFirstRegion = firstRegion;
    let stepFromSecondRegion = secondRegion;

    const visitedFromFirstRegion = new Set<String>();
    const visitedFromSecondRegion = new Set<String>();
    visitedFromFirstRegion.add(firstRegion);
    visitedFromSecondRegion.add(secondRegion);

    let firstCommonParentOfTwoRegions = "";

    while (true) {
        if (visitedFromFirstRegion.has(stepFromSecondRegion)) {
            firstCommonParentOfTwoRegions = stepFromSecondRegion;
            break;
        }
        if (visitedFromSecondRegion.has(stepFromFirstRegion)) {
            firstCommonParentOfTwoRegions = stepFromFirstRegion;
            break;
        }

        if (this.directedGraphRegionToParentRegion.has(stepFromFirstRegion)) {
            stepFromFirstRegion = this.directedGraphRegionToParentRegion.get(stepFromFirstRegion);
            visitedFromFirstRegion.add(stepFromFirstRegion);
        }
        if (this.directedGraphRegionToParentRegion.has(stepFromSecondRegion)) {
            stepFromSecondRegion = this.directedGraphRegionToParentRegion.get(stepFromSecondRegion);
            visitedFromSecondRegion.add(stepFromSecondRegion);
        }
    }

    return firstCommonParentOfTwoRegions;
}

function createDirectedGraphRegionToParentRegion(regions: string[][]): Map<string, string> {
    const regionToParentRegion = new Map<string, string>();
    for (let groupOfRegions of regions) {
        const parentRegion = groupOfRegions[0];
        for (let i = 1; i < groupOfRegions.length; ++i) {
            regionToParentRegion.set(groupOfRegions[i], parentRegion);
        }
    }
    return regionToParentRegion;
}
