//let n be the no of words and l is the avg length
//time complexity not sure
//space complexity not sure

class Solution {
    //create an indegrees array for showing no. of dependencies
    int[] indegrees;
    //create hashmap for storing the independent ele vs dependents
    HashMap<Character, HashSet<Character>> map;
    public String alienOrder(String[] words) {
        map = new HashMap<>();
        //a queue to implement bfs ie when taking the independent
        //node out, add the dependent nodes inside the queue
        Queue<Character> q = new LinkedList<>();
        //sb to store the result
        StringBuilder sb = new StringBuilder();
        indegrees = new int[26];
        //this method builds the dependency graph in the form of
        //adjacency list and also updates indegrees array
        buildGraph(words);
        //iterating over set to find which nodes are independent
        for(char c: map.keySet()){
            if(indegrees[c - 'a'] == 0){
                //add the independent char in sb;
                sb.append(c);
                q.add(c);
            }
        }
        //bfs
        while(!q.isEmpty()){
            char c = q.poll();
            if(map.get(c) == null || map.get(c).size() == 0) continue;
            //iterate over the edges in hashset
            for(char e: map.get(c)){
                indegrees[e - 'a']--;
                if(indegrees[e - 'a'] == 0){
                    q.add(e);
                    sb.append(e);
                }
            }
        }
        if(sb.length() < map.size()) return "";
        return sb.toString();

    }
    private void buildGraph(String[] words){
        for(String word : words){
            for(Character c : word.toCharArray()){
                if(!map.containsKey(c)){
                    map.put(c, new HashSet<>());
                }
            }
        }
        for(int i = 0; i < words.length - 1; i++){
            String first = words[i];
            String second = words[i+1];
            int m = first.length(); int n = second.length();
            //edge case of apple and app, out != in will fail.
            if (m > n && first.startsWith(second)) {
                 map.clear();
            }
            for(int j = 0; j < m && j < n; j++){
                char out = first.charAt(j);
                char in = second.charAt(j);
                if(out != in){
                    if(!map.get(out).contains(in)){
                        map.get(out).add(in);
                        //creating a dependency
                        indegrees[in - 'a']++;
                    }
                    //as soon as we find the unequal ele
                    //we break because we know the first string
                    //is sorted and comes before second string
                    //the latter characters dont' matter
                    //eg: apple and application
                    //this means e comes before i and the rest
                    //doest matter.
                    break;
                }
            }
        }
    }
}
