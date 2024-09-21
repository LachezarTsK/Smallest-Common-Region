
/**
 * @param {string[][]} regions
 * @param {string} firstRegion
 * @param {string} secondRegion
 * @return {string}
 */
var findSmallestRegion = function (regions, firstRegion, secondRegion) {
    this.directedGraphRegionToParentRegion = createDirectedGraphRegionToParentRegion(regions);
    return findFirstCommonParentOfTwoRegions(firstRegion, secondRegion);
};

/**
 * @param {string} firstRegion
 * @param {string} secondRegion
 * @return {string}
 */
function findFirstCommonParentOfTwoRegions(firstRegion, secondRegion) {
    let stepFromFirstRegion = firstRegion;
    let stepFromSecondRegion = secondRegion;

    const visitedFromFirstRegion = new Set();
    const visitedFromSecondRegion = new Set();
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

/**
 * @param {string[][]} regions
 * @return {Map<string, string>}
 */
function createDirectedGraphRegionToParentRegion(regions) {
    const regionToParentRegion = new Map();
    for (let groupOfRegions of regions) {
        const parentRegion = groupOfRegions[0];
        for (let i = 1; i < groupOfRegions.length; ++i) {
            regionToParentRegion.set(groupOfRegions[i], parentRegion);
        }
    }
    return regionToParentRegion;
}
