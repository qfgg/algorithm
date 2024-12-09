function cp(a, b) {
	if (a[1] === undefined) {
		return 1;
	} else if (b[1] === undefined) {
		return -1;
	} else {
		return a[1] - b[1];
	}
}

function dijkstra(chart, start = 0) {
	// store the shortest distance between start and the other points
	var short = [];
	var path = chart.map(function(item, index) {
		return index === start ? [start, start] : [start];
	});
	short[start] = 0;
	var i;
	var count = chart.length;
	var leftCount;

	// store the unsolved distance between start and the others
	var waitlist = [];

	for (var i = 0; i < count; i++) {
		if (i !== start) {
			waitlist.push([i, chart[start][i]]);
		}
	}
	// Using min heap to find the minimum
	createHeap(waitlist, cp);

	var cur, minIdx, minDist, minToTarget, tmp;

	while (waitlist.length > 0) {
		// fetch the minimum point of the waitlist
		cur = popHeap(waitlist, cp);
		minIdx = cur[0];
		minDist = cur[1];
		// add it to the shortest
		short[minIdx] = minDist;
		path[minIdx].push(minIdx);

		// if start->min + min->target < start->target, update the waitlist
		for (i = 0, leftCount = waitlist.length; i < leftCount; i++) {
			minToTarget = chart[minIdx][waitlist[i][0]];
			if (minToTarget !== undefined) {
				tmp = short[minIdx] + minToTarget;
				if (waitlist[i][1] === undefined || tmp < waitlist[i][1]) {
					waitlist[i][1] = tmp;
					path[waitlist[i][0]] = path[minIdx].slice();
					goUp(waitlist, i, cp);
				}
			}
		}
	}
	return [short, path];
}