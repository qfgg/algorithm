/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * @return a random integer in the range 1 to 7
 */
class Solution extends SolBase {
    public int rand10() {
        int n1 = rand7() - 1;
        int n2 = rand7() - 1;
        int n = n1 * 7 + n2 + 1;
        while (n > 40) {
            n1 = rand7() - 1;
            n2 = rand7() - 1;
            n = n1 * 7 + n2 + 1;
        }
        return n % 10 + 1;
    }
}
