function binarysearch(arr, target) {
    return search(arr, 0, arr.length - 1, target);
}

function search(arr, s, e, tg) {
    var l = s;
    var r = e;
    var m;

    while(true) {
        if (l === r) {
            return arr[l] === tg ? l : -1;
        }
        if (l + 1 === r) {
            if (arr[l] === tg) {
                return l;
            }
            if (arr[r] === tg) {
                return r;
            }
            return -1;
        }
        m = Math.floor((l + r) / 2);
        if (arr[m] === tg) {
            return arr[m] === tg ? m : -1;
        } else if (arr[m] < tg) {
            l = m;
            m = Math.floor((m + r) / 2);
        } else {
            r = m;
            m = Math.floor((l + m) / 2);
        }
    }
}