
#include <span>
#include <vector>
#include <string>
#include <unordered_set>
#include <unordered_map>
using namespace std;

/*
 The code will run faster with ios::sync_with_stdio(0).
 However, this should not be used in production code and interactive problems.
 In this particular problem, it is ok to apply ios::sync_with_stdio(0).

 Many of the top-ranked C++ solutions for time on leetcode apply this trick,
 so, for a fairer assessment of the time percentile of my code
 you could outcomment the lambda expression below for a faster run.
*/

/*
 const static auto speedup = [] {
	ios::sync_with_stdio(0);
	return nullptr;
 }();
*/

class Solution {

    unordered_map<string, string> directedGraphRegionToParentRegion;

public:
    string findSmallestRegion(const vector<vector<string>>& regions, const string& firstRegion, const string& secondRegion) {
        directedGraphRegionToParentRegion = createDirectedGraphRegionToParentRegion(regions);
        return findFirstCommonParentOfTwoRegions(firstRegion, secondRegion);
    }

private:
    string findFirstCommonParentOfTwoRegions(const string& firstRegion, const string& secondRegion) {
        string stepFromFirstRegion = firstRegion;
        string stepFromSecondRegion = secondRegion;

        unordered_set<string> visitedFromFirstRegion;
        unordered_set<string> visitedFromSecondRegion;
        visitedFromFirstRegion.insert(firstRegion);
        visitedFromSecondRegion.insert(secondRegion);

        string firstCommonParentOfTwoRegions;

        while (true) {
            if (visitedFromFirstRegion.contains(stepFromSecondRegion)) {
                firstCommonParentOfTwoRegions = stepFromSecondRegion;
                break;
            }
            if (visitedFromSecondRegion.contains(stepFromFirstRegion)) {
                firstCommonParentOfTwoRegions = stepFromFirstRegion;
                break;
            }

            if (directedGraphRegionToParentRegion.contains(stepFromFirstRegion)) {
                stepFromFirstRegion = directedGraphRegionToParentRegion[stepFromFirstRegion];
                visitedFromFirstRegion.insert(stepFromFirstRegion);
            }
            if (directedGraphRegionToParentRegion.contains(stepFromSecondRegion)) {
                stepFromSecondRegion = directedGraphRegionToParentRegion[stepFromSecondRegion];
                visitedFromSecondRegion.insert(stepFromSecondRegion);
            }
        }

        return firstCommonParentOfTwoRegions;
    }

    unordered_map<string, string> createDirectedGraphRegionToParentRegion(span <const vector<string>> regions) const {
        unordered_map<string, string> regionToParentRegion;
        for (const auto& groupOfRegions : regions) {
            string parentRegion = groupOfRegions[0];
            for (size_t i = 1; i < groupOfRegions.size(); ++i) {
                regionToParentRegion[groupOfRegions[i]] = parentRegion;
            }
        }
        return regionToParentRegion;
    }
};
