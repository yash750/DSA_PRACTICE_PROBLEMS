import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Stack {
    
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> closings = new HashMap<>();
        closings.put(')','(');
        closings.put('}','{');
        closings.put(']','[');
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(closings.containsKey(ch)){
                if(!stack.isEmpty()){
                    char open = stack.pop();
                    if(closings.get(ch) == open){
                        continue;
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            }else{
                stack.push(ch);
            }
        }
        if(!stack.isEmpty()){
            return false;
        }
        return true;
    }

    class MinStack {
        Stack<Integer> stack;
        Stack<Integer> minHolder;

        public MinStack() {
            this.stack = new Stack<>();
            this.minHolder = new Stack<>();
        }
        
        public void push(int val) {
            stack.push(val);
            if(!minHolder.isEmpty() && val <= minHolder.peek()){
                minHolder.push(val);
                return;
            }
            List<Integer> list = new ArrayList<>();
            while(!minHolder.isEmpty() && val > minHolder.peek()){
                list.add(minHolder.pop());
            }
            minHolder.push(val);
            while(list.size() > 0){
                minHolder.push(list.get(list.size()-1));
                list.remove(list.size()-1);
            }
            

        }
        
        public void pop() {
            int val = stack.peek();
            if(minHolder.peek() == val){
                stack.pop();
                minHolder.pop();
                return;
            }
            List<Integer> list = new ArrayList<>();
            while(!minHolder.isEmpty()){
                if(val == minHolder.peek()){
                    minHolder.pop();
                    stack.pop();
                    break;
                }
                list.add(minHolder.pop());
            }
            while(list.size() > 0){
                minHolder.push(list.get(list.size()-1));
                list.remove(list.size()-1);
            }
        }
        
        public int top() {
            return stack.peek();
        }
        
        public int getMin() {
            if(!minHolder.isEmpty()){
                return minHolder.peek();
            }else{
                return -1;
            }
        }
    }

    public int performOp(int num1, int num2, String token){
        if(token.equals("+")){
            return num1 + num2;
        }else if(token.equals("-")){
            return num2 - num1;
        }else if(token.equals("*")){
            return num1 * num2;
        }else{
            return num2 / num1;
        }
    }
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for(String token: tokens){
            if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")){
                int num1 = stack.pop();
                int num2 = stack.pop();
                int val = performOp(num1, num2, token);
                stack.push(val);
                continue;
            }
            int i = Integer.parseInt(token);
            stack.push(i);
        }
        if(!stack.isEmpty()){
            return stack.peek();
        }
        return 0;
    }

    public int[] dailyTemperatures(int[] temps) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[temps.length];
        for(int i = 0; i < temps.length; i++){
         while(!stack.isEmpty() && temps[i] > temps[stack.peek()]){
             int prevIndex = stack.pop();
             ans[prevIndex] = i - prevIndex;
         }
         stack.push(i);
        }
 
        return ans;
 
    }

    public String clearDigits(String s) {
        StringBuilder stack = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                if (stack.length() > 0) {
                    stack.deleteCharAt(stack.length() - 1);
                }
            } else {
                stack.append(c);
            }
        }
        return stack.toString();
    }

    class MyStack {
        Queue<Integer> q1;
        Queue<Integer> q2;
    
        public MyStack() {
            this.q1 = new LinkedList<>();
            this.q2 = new LinkedList<>();
        }
        
        public void push(int x) {
            if(q1.isEmpty()){
                q2.add(x);
            }else{
                q1.add(x);
            }
        }
        
        public int pop() {
            if(q1.size() == 0 && q2.size() ==0){
                return -1;
            }
            if(q2.isEmpty()){
                while(q1.size() > 1){
                    q2.add(q1.poll());
                }
                return q1.poll();
            }else{
                while(q2.size() > 1){
                    q1.add(q2.poll());
                }
                return q2.poll();
            } 
        }
        
        public int top() {
            if(q1.size() == 0 && q2.size() ==0){
                return -1;
            }
            if(q2.isEmpty()){
                while(q1.size() > 1){
                    q2.add(q1.poll());
                }
                int item = q1.peek();
                q2.add(q1.poll());
                return item;
            }else{
                while(q2.size() > 1){
                    q1.add(q2.poll());
                }
                int item = q2.peek();
                q1.add(q2.poll());
                return item;
            }
        }
        
        public boolean empty() {
            if(q1.size() == 0 && q2.size() == 0){
                return true;
            }else{
                return false;
            }
        }
    }
    
}
