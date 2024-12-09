function genArr(maxLen = 10, max = 100) {
	var len = Math.floor(Math.random() * maxLen) + 1;
	var arr = [];
	
	for (var i = 0; i < len; i++) {
		arr.push(Math.floor(Math.random() * max));
	}

	return arr;
}