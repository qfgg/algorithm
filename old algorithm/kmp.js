function computeTable(w) {
    let table = [];
    let len = w.length;
    let lastMax;
    table[0] = 0;
    for (let i = 1; i < len; i++) {
        table[i] = 0;
        lastMax = table[i - 1];
        while (true) {
            if (w[i] === w[lastMax]) {
                table[i] = lastMax + 1;
                break;
            }
            if (lastMax === 0) {
                break;
            }
            lastMax = table[lastMax - 1];
        }
    }
    return table;
}

function kmp(sentence, word) {
    let table = computeTable(word);
    let slen = sentence.length;
    let wlen = word.length;
    let i = 0;
    let j = 0;
    while (true) {
        // if the first letter match, then compare next letters
        while (sentence[i] !== word[j]) {
            if (i === slen - 1) {
                return false;
            }
            i++;
        }
        while (true) {
            do {
                if (j === wlen - 1) {
                    return i - wlen + 1;
                }
                if (i === slen - 1) {
                    return false;
                }
                i++;
                j++;
            } while (sentence[i] === word[j]);
            // sentence[i] !== word[j], so j - 1 is the last matched letter
            i--;
            j--;
            if (table[j] > 0) {
                j = table[j] - 1;
            } else {
                if (i === slen - 1) {
                    return false;
                }
                i++;
                j = 0;
                break;
            }
        }
    }
}

let sentence = 'ABC ABCDAB ABCDABCDABDE';
let word = 'ABCDABD';
let res = kmp(sentence, word);
console.log(res);
