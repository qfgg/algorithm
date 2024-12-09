function insertHeap(heap, n, fn = function(a, b) { return a - b; }) {
    heap.push(n);

    goUp(heap, heap.length - 1, fn);
}

function goUp(heap, i, fn) {
    var parent = Math.floor((i - 1) / 2);

    while (parent >= 0) {
        if (fn(heap[parent], heap[i]) > 0) {
            var tmp = heap[parent];
            heap[parent] = heap[i];
            heap[i] = tmp;
        }
        i = parent;
        parent = Math.floor((i - 1) / 2);
    }
}

function popHeap(nums, fn = function(a, b) { return a - b; }) {
    if (nums.length === 1) {
        return nums.pop();
    }
    
    var r = nums[0];
    nums[0] = nums.pop();
    goDown(nums, 0, nums.length - 1, fn);

    return r;
}

function createHeap(nums, fn = function(a, b) { return a - b; }) {
    var max = nums.length - 1;
    var start = Math.floor((max - 1) / 2);

    for(var i = start; i >= 0; i--) {
        goDown(nums, i, max, fn);
    }
}

function goDown(nums, i, max, fn) {
    var tmp, swapIdx;
    var leftChild = 2 * i + 1;
    var rightChild = 2 * i + 2;

    while (leftChild <= max) {
        if (rightChild > max) {
            if (fn(nums[leftChild], nums[i]) < 0) {
                swapIdx = leftChild;
            }
        } else {
            if (fn(nums[leftChild], nums[i]) < 0 && fn(nums[rightChild], nums[i]) < 0) {
                swapIdx = fn(nums[leftChild], nums[rightChild]) < 0 ?
                    leftChild : rightChild;
            } else if (fn(nums[leftChild], nums[i]) < 0) {
                swapIdx = leftChild;
            } else if(fn(nums[rightChild], nums[i]) < 0) {
                swapIdx = rightChild;
            }
        }
        if (swapIdx !== undefined) {
            tmp = nums[swapIdx];
            nums[swapIdx] = nums[i];
            nums[i] = tmp;
            i = swapIdx;
            leftChild = 2 * i + 1;
            rightChild = 2 * i + 2;
            swapIdx = undefined;
        } else  {
            break;
        }
    }
}

function heapSort(heap, fn = function(a, b) { return a - b; }) {
    var heapSize = heap.length;
    var i = heapSize - 1;
    
    while (i > 0) {
        var tmp = heap[i];
        heap[i] = heap[0];
        heap[0] = tmp;

        heapSize--;
        goDown(heap, 0, heapSize - 1, fn);
        i--;
    }
}