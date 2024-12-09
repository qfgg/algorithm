var postorderTraversal = function(root) {
    var result = [];

    if (!root) {
        return result;
    }
    
    var cur = root;
    var stack = [];
    var top;

    while (cur) {
        stack.push(cur);
        cur = cur.left;
    }
    
    while (stack.length > 0) {
        top = stack[stack.length - 1];
        if (top.right && top.right !== cur) {
            cur = top.right;
            while (cur) {
                stack.push(cur);
                cur = cur.left;
            }
        } else {
            cur = stack.pop();
            result.push(cur.val);
        }
    }

    return result;
};