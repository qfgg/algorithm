function insertSort(input) {
  for (var i = 1, len = input.length; i < len; i++) {
    var tmp = input[i];
    var j = i - 1;
    while (j >= 0 && input[j] > tmp) {
      input[j + 1] = input[j];
      j--;
    }
    input[j + 1] = tmp;
  }
}
