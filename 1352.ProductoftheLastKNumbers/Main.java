import java.util.*;

class ProductOfNumbers {
    int n0;
    int n1;
    int n2;
    int p1;
    List<Integer> preProduct;
    public ProductOfNumbers() {
        n0 = 0;
        n1 = 0;
        n2 = 0;
        p1 = 1;
        preProduct = new ArrayList<>();
    }

    public void add(int num) {
        if (num == 0) {
            n0 = 1;
            n1 = 0;
            n2 = 0;
            if (!preProduct.isEmpty()) {
                preProduct.clear();
            }
        } else {
            if (n0 != 0) {
                n0++;
            }
            if (preProduct.isEmpty()) {
                preProduct.add(num);
                n2++;
            } else {
                int pre = preProduct.get(preProduct.size() - 1);
                if (pre > Integer.MAX_VALUE / num) {
                    p1 = pre;
                    n1 = n2;
                    n2 = 1;
                    preProduct.add(num);
                } else {
                    preProduct.add(pre * num);
                    n2++;
                }
            }
        }
    }

    public int getProduct(int k) {
        if (n0 != 0 && k >= n0) {
            return 0;
        }
        int cur = preProduct.size() - 1;
        if (k > n2 && k <= n2 + n1) {
            return k == n2 + n1 ? p1 * preProduct.get(cur) :  p1 / preProduct.get(cur - k) * preProduct.get(cur);
        }
        return k == n2 ? preProduct.get(cur) : preProduct.get(cur) / preProduct.get(cur - k);
    }
}
public class Main {
    public static void main(String[] args) {
        ProductOfNumbers productOfNumbers = new ProductOfNumbers();
        productOfNumbers.add(3);        // [3]
        productOfNumbers.add(0);        // [3,0]
        productOfNumbers.add(2);        // [3,0,2]
        productOfNumbers.add(5);        // [3,0,2,5]
        productOfNumbers.add(4);        // [3,0,2,5,4]
        System.out.println(productOfNumbers.getProduct(2)); // return 20. The product of the last 2 numbers is 5 * 4 = 20
        System.out.println(productOfNumbers.getProduct(3)); // return 40. The product of the last 3 numbers is 2 * 5 * 4 = 40
        System.out.println(productOfNumbers.getProduct(4)); // return 0. The product of the last 4 numbers is 0 * 2 * 5 * 4 = 0
        productOfNumbers.add(8);        // [3,0,2,5,4,8]
        System.out.println(productOfNumbers.getProduct(2)); // return 32. The product of the last 2 numbers is 4 * 8 = 32
    }
}
