var pre = [];

function find(x) {
    if (pre[x] === x) {
        return x;
    }
    var root = find(pre[x]);
    pre[x] = root;
    return root;
}
function union(x, y) {
    var root1 = find(x);
    var root2 = find(y);
    if (root1 !== root2) {
        pre[root2] = root1;
    }
}

