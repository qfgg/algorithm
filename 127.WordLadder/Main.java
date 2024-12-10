import java.util.*;


public class Main {
  public static String replaceALetter(String s, int idx, char lt) {
    if (idx == 0) {
      return lt + s.substring(1);
    }
    int last = s.length() - 1;
    if (idx == last) {
      return s.substring(0, last) + lt;
    }
    return s.substring(0, idx) + lt + s.substring(idx + 1);
  }
  public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
    Set<String> list = new HashSet<>(wordList);
    if (!list.contains(endWord)) {
      return 0;
    }
    Set<String> sUsed = new HashSet<>();
    Set<String> eUsed = new HashSet<>();
    Queue<String> sq = new LinkedList<>();
    Queue<String> eq = new LinkedList<>();
    int sl = 1, el = 1, swl, ewl, i, j, sqSize = 1, eqSize = 1;
    boolean sHasNext = false, eHasNext = false;
    String curE, curS, next;
    sq.add(beginWord);
    eq.add(endWord);
    sUsed.add(beginWord);
    eUsed.add(endWord);
    while(!eq.isEmpty() || !sq.isEmpty()) {
      while (!eq.isEmpty()) {
        curE = eq.poll();
        eqSize--;
        ewl = curE.length();
        for (i = 0; i < ewl; i++) {
          for (j = 0; j < 26; j++) {
            next = replaceALetter(curE, i, (char)('a' + j));
            if (sUsed.contains(next)) {
              return sl + el;
            }
            if (!eUsed.contains(next) && list.contains(next)) {
              eq.add(next);
              eUsed.add(next);
              eHasNext = true;
            }
          }
        }
        if (eqSize == 0) {
          if (eHasNext) {
            el++;
            eHasNext = false;
          }
          eqSize = eq.size();
          break;
        }
      }
      while (!sq.isEmpty()) {
        curS = sq.poll();
        sqSize--;
        swl = curS.length();
        for (i = 0; i < swl; i++) {
          for (j = 0; j < 26; j++) {
            next = replaceALetter(curS, i, (char)('a' + j));
            if (eUsed.contains(next)) {
              return sl + el;
            }
            if (!sUsed.contains(next) && list.contains(next)) {
              sq.add(next);
              sUsed.add(next);
              sHasNext = true;
            }
          }
        }
        if (sqSize == 0) {
          if (sHasNext) {
            sl++;
            sHasNext = false;
          }
          sqSize = sq.size();
          break;
        }
      }
    }
    return 0;
  }
  public static void main(String[] args) {
    String beginWord = "magic", endWord = "pearl";
    String[] l = {"flail","halon","lexus","joint","pears","slabs","lorie","lapse","wroth","yalow","swear","cavil","piety","yogis","dhaka","laxer","tatum","provo","truss","tends","deana","dried","hutch","basho","flyby","miler","fries","floes","lingo","wider","scary","marks","perry","igloo","melts","lanny","satan","foamy","perks","denim","plugs","cloak","cyril","women","issue","rocky","marry","trash","merry","topic","hicks","dicky","prado","casio","lapel","diane","serer","paige","parry","elope","balds","dated","copra","earth","marty","slake","balms","daryl","loves","civet","sweat","daley","touch","maria","dacca","muggy","chore","felix","ogled","acids","terse","cults","darla","snubs","boats","recta","cohan","purse","joist","grosz","sheri","steam","manic","luisa","gluts","spits","boxer","abner","cooke","scowl","kenya","hasps","roger","edwin","black","terns","folks","demur","dingo","party","brian","numbs","forgo","gunny","waled","bucks","titan","ruffs","pizza","ravel","poole","suits","stoic","segre","white","lemur","belts","scums","parks","gusts","ozark","umped","heard","lorna","emile","orbit","onset","cruet","amiss","fumed","gelds","italy","rakes","loxed","kilts","mania","tombs","gaped","merge","molar","smith","tangs","misty","wefts","yawns","smile","scuff","width","paris","coded","sodom","shits","benny","pudgy","mayer","peary","curve","tulsa","ramos","thick","dogie","gourd","strop","ahmad","clove","tract","calyx","maris","wants","lipid","pearl","maybe","banjo","south","blend","diana","lanai","waged","shari","magic","duchy","decca","wried","maine","nutty","turns","satyr","holds","finks","twits","peaks","teems","peace","melon","czars","robby","tabby","shove","minty","marta","dregs","lacks","casts","aruba","stall","nurse","jewry","knuth"};
    List<String> wordList = new ArrayList<>(Arrays.asList(l));
    int res = ladderLength(beginWord, endWord, wordList);
    System.out.println(res);
  }
}
