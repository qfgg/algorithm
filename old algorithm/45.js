/**
 * @param {number[]} nums
 * @return {number}
 */
var jump = function(nums) {
    var last = nums.length - 1;
    var step = 0;
    var currentMaxIdx = 0;
    var i = 0;
    var nextMaxIdx;
    var tmp;
    
    while (i <= last) {
        nextMaxIdx = currentMaxIdx;
        while (i <= currentMaxIdx) {
            if (i === last) {
                return step;
            }
            tmp = i + nums[i];
            if (tmp > nextMaxIdx) {
                nextMaxIdx = tmp;
            }
            i++;
        }
        currentMaxIdx = nextMaxIdx;
        step++;
    }
};
