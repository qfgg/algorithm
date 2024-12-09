var matrix = [['a', 'b'], ['c', 'd', 'e'], ['f', 'g', 'h', 'i']];
// "acf", "acg", "ach", "aci",
// "adf", "adg", "adh", "adi",
// "aef", "aeg", "aeh", "aei",
// "bcf", "bcg", "bch", "bci",
// "bdf", "bdg", "bdh", "bdi",
// "bef", "beg", "beh", "bei"
// 递归
function cmb(mat) {
    if (!mat || Object.prototype.toString.call(mat) !== '[object Array]') {
        return [];
    }
    if (mat.length === 1) {
        return mat[0];
    } else {
        var list = [];
        var rest = cmb(mat.slice(1));

        for(var i = 0, len = mat[0].length; i < len; i++) {
            for(var j = 0, l = rest.length; j < l; j++) {
                list.push(mat[0][i] + rest[j]);
            }
        }
        return list;
    }
}
// 非递归
function cmb2(mat) {
    if (!mat || Object.prototype.toString.call(mat) !== '[object Array]') {
        return [];
    }
    var list = mat.slice();
    var total = mat.length;
    while(total > 1){
        var tmp = [];
        for(var i = 0, l1 = list[0].length; i < l1; i++) {
            for (var j = 0, l2 = list[1].length; j < l2; j++) {
                tmp.push(list[0][i] + list[1][j]);
            }
        }
        list.splice(0, 2, tmp);
        total--;
    }
    return list[0];
}
var res = cmb(matrix);
var res2 = cmb2(matrix);
console.log(res);
console.log(res2);