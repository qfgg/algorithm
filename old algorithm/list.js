function ListNode(val) {
    this.val = val;
    this.next = null;
}

function makeList(arr) {
    if (arr.length === 0) {
        return null;
    }
    var head = new ListNode();
    var cur = head;
    for (var i = 0, len = arr.length; i < len; i++) {
        cur.val = arr[i];
        cur.next = i < len - 1 ? new ListNode() : null;
        cur = cur.next;
    }
    return head;
}

function printList(l) {
    var arr = [];
    while (l !== null) {
        arr.push(l.val);
        l = l.next;
    }
    console.log(arr);
}
