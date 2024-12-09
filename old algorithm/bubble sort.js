function bubbleSort(input) {
  for (var i = input.length; i > 1; i--) {
    for (var j = 1; j < i; j++) {
      if (input[j] < input[j - 1]) {
        var tmp = input[j - 1];
        input[j - 1] = input[j];
        input[j] = tmp;
      }
    }
  }
}
