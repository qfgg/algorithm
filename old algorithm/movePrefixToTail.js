function swap(str, i, j) {
    var tmp = str[i];
    str[i] = str[j];
    str[j] = tmp;
}

function multiSwap(str, start, end) {
    var i = start;
    var j = end;
    while (i < end) {
        swap(str, i, j);
        i++;
        j++;
    }
}

function moveBack(str, k) {
    if (k <= 0) {
        return str;
    }
    var start = 0;
    var end = str.length - 1;
    var len;
    var direction = true;
    while (true) {
        len = end - start + 1;
        if (k <= Math.floor(len / 2)) {
            if (direction) {
                multiSwap(str, start, start + k);
                start += k;
            } else {
                multiSwap(str, end - 2 * k + 1, end - k + 1);
                end -= k;
            }
            if (2 * k === len) {
                break;
            }
        } else {
            k = len - k;
            direction = !direction;
        }
    }
}
