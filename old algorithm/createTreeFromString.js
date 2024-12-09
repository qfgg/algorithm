function Node(val) {
    this.value = val;
    this.children = null;
}

// method1: recursion
function getTree(str, start, end) {
    let i = start;
    while (str[i] !== '(' && i < end) {
        i++;
    }
    const parent = new Node(str.slice(start, i));
    if (str[i] === '(') {
        const children = getChildren(str, i + 1, end - 1);
        parent.children = [];
        for (let j = 0; j < children.length; j++) {
            parent.children.push(getTree(children[j], 0, children[j].length));
        }
    }
    return parent;
}

function getChildren(str, start, end) {
    const children = [];
    let tmp = '';
    let leftCount = 0;
    for (let i = start; i < end; i++) {
        if (str[i] === '(') {
            tmp += str[i];
            leftCount++;
        } else if (str[i] === ')') {
            tmp += str[i];
            leftCount--;
        } else if ((leftCount === 0 && str[i] !== ',') || leftCount > 0) {
            tmp += str[i];
        } else if (leftCount === 0 && str[i] === ',') {
            children.push(tmp);
            tmp = '';
        }
    }
    if (tmp) {
        children.push(tmp);
    }
    return children;
}

function fn(str) {
    return getTree(str, 0, str.length);
}

// method2: traverse 1 time, stack
function addChild(parent, name) {
    if (!name) {
      return;
    }
    const child = new Node(name);
    if (parent.children) {
      parent.children.push(child);
    } else {
      parent.children = [child];
    }
    return child;
}
    
function optimizedFn(str) {
    let root = null;
    let currentParent = null;
    let parents = [];
    const len = str.length;
    let i = 0;
    let name = '';
    while (i < len) {
      if (str[i] === '(') {
        if (currentParent) {
          currentParent = addChild(currentParent, name);
        } else {
          currentParent = new Node(name);
          root = currentParent;
        }
        name = '';
        parents.push(currentParent);
      } else if (str[i] === ')') {
        addChild(currentParent, name);
        name = '';
        parents.pop()
        currentParent = parents[parents.length - 1];
      } else if (str[i] === ',') {
        addChild(currentParent, name);
        name = '';
      } else {
        name += str[i];
      }
      i++;
    }
    return root;
}

const input = 'A(B,C(C1,C2(C21,C22),C3),D(D1,D2))';
const r = optimizedFn(input);
console.log(r);
