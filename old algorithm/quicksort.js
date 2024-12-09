function swap(arr, i, j) {
    var tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
}
function qsort(arr, s, e, fn) {
    if (s >= e) {
        return;
    }
    var i = s;
    var j = e;
    while(j > i) {
        while(j > i && ((fn && fn(arr[j], arr[s]) >=0) || (!fn && arr[j] >= arr[s]))) {
            j--;
        }
        while(j > i && ((fn && fn(arr[i], arr[s]) <=0) || (!fn && arr[i] <= arr[s]))) {
            i++
        }
        if (i !== j) {
            swap(arr, i, j);
        }
    }
    if (s !== j) {
        swap(arr, s, j);
    }
    qsort(arr, s, j - 1, fn);
    qsort(arr, j + 1, e, fn);
}
function quicksort(arr, fn) {
    qsort(arr, 0, arr.length - 1, fn);
}