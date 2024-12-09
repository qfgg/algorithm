function selectionSort(input) {
  var j = 0;
  var len = input.length;
  while (j < len - 1) {
    var min = j;
    for (var i = j + 1; i < len; i++) {
      if (input[i] < input[min]) {
        min = i;
      }
    }
    if (min !== j) {
      var tmp = input[j];
      input[j] = input[min];
      input[min] = tmp;
    }
    j++;
  }
}
