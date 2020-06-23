package site.teamo.biu.dasam.learning.stack;

/**
 * @author 爱做梦的锤子
 * @create 2020/6/19
 */
public class Solution {


    public static void main(String[] args) {
        System.out.println(new Solution().solution("[]({[]}){}"));
        System.out.println(new Solution().solution("[({[]}){}"));
    }

    public boolean solution(String s) {
        Stack<Character> stock = new ArrayStack<>();
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (current == '(' || current == '[' || current == '{') {
                stock.push(current);
                continue;
            }
            if (!stock.isEmpty()) {
                char top = stock.pull();
                if (current == ')' && top != '(') {
                    return false;
                }
                if (current == ']' && top != '[') {
                    return false;
                }
                if (current == '}' && top != '{') {
                    return false;
                }
            }
        }
        return stock.isEmpty();
    }

}
