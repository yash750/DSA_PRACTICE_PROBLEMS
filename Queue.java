import java.util.PriorityQueue;

public class Queue {

    class MyQueue {
        Stack<Integer> s1;
        Stack<Integer> s2;
    
        public MyQueue() {
            this.s1 = new Stack<>();
            this.s2 = new Stack<>();
        }
    
        public void push(int x) {
            s1.push(x);
        }
    
        public int pop() {
            if (s1.empty() && s2.empty()) {
                return -1; 
            }
            if (s2.empty()) {
                while (!s1.empty()) {
                    s2.push(s1.pop());
                }
            }
            return s2.pop();
        }
    
        public int peek() {
            if (s1.empty() && s2.empty()) {
                return -1; 
            }
            if (s2.empty()) {
                while (!s1.empty()) {
                    s2.push(s1.pop());
                }
            }
            return s2.peek();
        }
    
        public boolean empty() {
            return s1.empty() && s2.empty();
        }
    }

    class RecentCounter {
        private static final int[] records = new int[10000]; //
        private int start;
        private int end;
    
        public RecentCounter() {        
            start = 0;
            end = 0;
        }
        
        public int ping(int t) {
            while (start < end && (t - records[start] > 3000)) { 
                start++; // if the difference in time is greater than 3000ms, 
                // than increase the value of start unitl it's equal or less than 3000ms.
            }
            records[end++] = t; // Inserting the current time at the end
            return end - start; // Returning the answer including the element added just now.
        }
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int num: nums){
            minHeap.offer(num);
            if(minHeap.size() > k){
                minHeap.poll();
            }
        }

        return minHeap.poll();
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((a,b) -> b[0]-a[0]);
        int[] res = new int[nums.length - k + 1];
        int idx = 0;
        for(int i = 0; i < nums.length; i++){
            heap.offer(new int[]{nums[i], i});
            if(i >= k - 1){
                while(heap.peek()[1] <= i-k){
                    heap.poll();
                }
                res[idx] = heap.peek()[0];
                idx++;
            }
            
        }
        return res;
    }

    class MyCircularQueue {
        final int[] a;
        int front = 0, rear = -1, len = 0;

        public MyCircularQueue(int k) { a = new int[k];}

        public boolean enQueue(int val) {
            if (!isFull()) {
                rear = (rear + 1) % a.length;
                a[rear] = val;
                len++;
                return true;
            } else return false;
        }

        public boolean deQueue() {
            if (!isEmpty()) {
                front = (front + 1) % a.length;
                len--;
                return true;
            } else return false;
        }

        public int Front() { return isEmpty() ? -1 : a[front];}

        public int Rear() {return isEmpty() ? -1 : a[rear];}

        public boolean isEmpty() { return len == 0;}

        public boolean isFull() { return len == a.length;}
    }
}
