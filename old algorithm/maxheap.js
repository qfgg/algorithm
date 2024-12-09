function insertHeap(heap, n, fn = function(a, b) { return a - b; }) {
    heap.push(n);

    goUp(heap, heap.length - 1, fn);
}

function goUp(heap, i, fn) {
    var parent = Math.floor((i - 1) / 2);

    while (parent >= 0) {
        if (fn(heap[parent], heap[i]) < 0) {
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

function goDown(heap, i, max, fn = function(a, b) { return a - b; }) {
    var left = 2 * i + 1;
    var right = left + 1;
    var len = heap.length;
    var target;

    while (left < len) {
        if (right < len && fn(heap[right], heap[i]) > 0) {
            if (fn(heap[left], heap[right]) > 0) {
                target = left;
            } else {
                target = right;
            }
            swapHeap(heap, i, target);
            i = target;
        } else if (fn(heap[left], heap[i]) > 0) {
            swapHeap(heap, i, left);
            i = left
        } else {
            break;
        }
        left = 2 * i + 1;
        right = left + 1;
    }
}

function swapHeap(heap, i, j) {
    var tmp = heap[i];
    heap[i] = heap[j];
    heap[j] = tmp;
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

var heap = [79,66,43,83,30,87,38,55,91,72,49,9];
createHeap(heap);
heapSort(heap);
console.log(heap);