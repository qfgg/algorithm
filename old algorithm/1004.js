/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var longestOnes = function(nums, k) {
    var len = nums.length;
    var max = 0;
    var i = 0;
    var count = 0;
    // edge case
    if (k === 0) {
        for (i = 0; i < len; i++) {
            if (nums[i] === 1) {
                count++;
            }
            if ((i === len - 1 && nums[i] === 1) || (i < len - 1 && nums[i + 1] === 0)) {
                if (count > max) {
                    max = count;
                }
                count = 0;
            }
        }
        return max;
    }
    // initiate window
    var start = 0;
    var end;
    var rest = k;
    i = 0;
    while (i < len) {
        if (nums[i] === 0) {
            rest--;
            if (rest === 0) {
                i++;
            	while (i < len && nums[i] === 1) {
                    i++;
                }
                end = i;
                break;
            }
        }
        i++;
    }
    if (rest > 0) {
        end = len;
    }
    count = end - start;
    if (count > max) {
        max = count;
    }
    // slide window
    while (end < len) {
        while (start < len && nums[start] === 1) {
            start++;
        }
        start = start + 1;
        if (start >= len) {
            break;
        }
        while (nums[end] === 1) {
            if (end === len - 1) {
                break;
            }
            end++;
        }
        end++;
        while (end < len && nums[end] === 1) {
            end++;
        }
        count = end - start;
        if (count > max) {
            max = count;
        }
    }
    return max;
};
