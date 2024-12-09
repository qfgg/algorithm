function TreeNode(val) {
    this.val = val;
    this.left = this.right = null;
}

function buildTree(arr) {
    if (!arr) {
        return null;
    }

    var root = new TreeNode(arr[0]);
    var nodeArr = [root];
    var left;
    var right;
    for (var i = 0, len = arr.length; i < len; i++) {
        if (nodeArr[i]) {
            left = i * 2 + 1;
            if (left >= len) {
                break;
            }
            nodeArr[left] = arr[left] === null ? null : new TreeNode(arr[left]);
            nodeArr[i].left = nodeArr[left];
            right = left + 1;
            if (right >= len) {
                break;
            }
            nodeArr[right] = arr[right] === null ? null : new TreeNode(arr[right]);
            nodeArr[i].right = nodeArr[right];
        }
    }

    return root;
}

var tree = buildTree([10,5,15,null,null,6,20]);
