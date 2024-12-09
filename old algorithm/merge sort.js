function merge(arr, s, m, e) {
  var l = [];
  var r = [];
  var tmp = [];
  for (var i = 0; i < m - s; i++) {
    l[i] = arr[i + s];
  }
  for (var j = 0; j <= e - m; j++) {
    r[j] = arr[j + m];
  }
  i = 0;
  j = 0;
  for (var k = 0; k <= e - s; k++) {
    if ((l[i] !== undefined && r[j] === undefined) || l[i] < r[j]) {
      arr[k + s] = l[i];
      i++;
    } else if ((l[i] === undefined && r[j] !== undefined) || l[i] >= r[j]) {
      arr[k + s] = r[j];
      j++;
    }
  }
}

function sort(arr, start, end) {
  if (start < end) {
    var mid = Math.ceil((end + start) / 2);
    sort(arr, start, mid - 1);
    sort(arr, mid, end);
    merge(arr, start, mid, end);
  }
}

function mergeSort(input) {
  sort(input, 0, input.length - 1);
}
