import java.util.*;

public class Main {
    public static boolean findOne(String recipe, Map<String, List<String>> recToIng, Set<String> sup, Set<String> visited) {
        if (visited.contains(recipe)) {
            return sup.contains(recipe);
        }
        visited.add(recipe);
        if (!recToIng.containsKey(recipe)) {
            return false;
        }
        List<String> ing = recToIng.get(recipe);
        for (String i : ing) {
            if (!sup.contains(i)) {
                if(!findOne(i, recToIng, sup, visited)) {
                    return false;
                }
            }
        }
        sup.add(recipe);
        return true;
    }
    public static List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, List<String>> recToIng = new HashMap<>();
        int l = recipes.length;
        for (int i = 0; i < l; i++) {
            recToIng.put(recipes[i], ingredients.get(i));
        }
        Set<String> sup = new HashSet<>(Arrays.asList(supplies));
        Set<String> visited = new HashSet<>();
        List<String> ans = new ArrayList<>();
        for (String recipe: recipes) {
            if (findOne(recipe, recToIng, sup, visited)) {
                ans.add(recipe);
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        String[] recipes = new String[]{"bread","sandwich","burger"};
        List<List<String>> ingredients = new ArrayList<>();
        ingredients.add(new ArrayList<>(Arrays.asList("yeast","flour")));
        ingredients.add(new ArrayList<>(Arrays.asList("bread","meat")));
        ingredients.add(new ArrayList<>(Arrays.asList("sandwich","meat","bread")));
        String[] supplies = new String[]{"yeast","flour","meat"};
        List<String> ans = findAllRecipes(recipes, ingredients, supplies);
        System.out.println(Arrays.toString(ans.toArray()));
    }
}