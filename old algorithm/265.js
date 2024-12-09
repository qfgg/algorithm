var minCostII = function(costs) {
    var min = Infinity;
    var h = costs.length;
    var p = costs[0].length;
    var dp = [costs[0]];
    var minWithoutCurrentPaint = [];
    computePrev(dp[0], minWithoutCurrentPaint);
    for (var i = 1; i < h; i++) {
        for (var j = 0; j < p; j++) {
            if (!dp[i]) {
                dp[i] = [];
            }
            dp[i][j] = minWithoutCurrentPaint[j] + costs[i][j];
            if (i === h - 1 && dp[i][j] < min) {
                min = dp[i][j];
            }
        }
        computePrev(dp[i], minWithoutCurrentPaint);
    }
    return min;
};

function computePrev(arr, res) {
    var min1;
    var min1Pos;
    var min2;
    var min2Pos;
    var len = arr.length;
    if (arr[0] <= arr[1]) {
        min1 = arr[0];
        min1Pos = 0;
        min2 = arr[1];
        min2Pos = 1;
    } else {
        min1 = arr[1];
        min1Pos = 1;
        min2 = arr[0];
        min2Pos = 0;
    }
    for (var i = 2; i < len; i++) {
        if (arr[i] < min1) {
            min2 = min1;
            min2Pos = min1Pos;
            min1 = arr[i];
            min1Pos = i;
        } else if (arr[i] < min2) {
            min2 = arr[i];
            min2Pos = i;
        }
    }
    for (i = 0; i < len; i++) {
        if (i !== min1Pos) {
            res[i] = min1;
        } else {
            res[i] = min2;
        }
    }
}
