//lets say l is the average length of words
//n is the number of words in array
//time complexity O(nl) => O(n) since l is constant
//space complexity O(1) constant alphabets

class Solution {
    HashMap<Character, Integer> orderMap;
    public boolean isAlienSorted(String[] words, String order) {
        orderMap = new HashMap<>();
        for(int j = 0; j < order.length(); j++){
            char c = order.charAt(j);
            orderMap.put(c, j);
        }
        for(int i =  0; i < words.length - 1; i++){
            if(!sorted(words[i], words[i+1])) return false;
        }
        return true;
    }
    private boolean sorted(String s1, String s2){
        int len1 = s1.length(); int len2 = s2.length();
        for(int i = 0; i < Math.min(len1, len2); i++){
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if(c1 != c2){
                if(orderMap.get(c1) < orderMap.get(c2)) return true;
                else return false;
            }
        }
	//this was really tricky for me to understand
	//so lets take the example of apple and app or app and apple
	//in both cases the control won't enter the if statement ln 22
	//which means we need to return true in case of app and apple
	//since in dictionary this is considered sorted if app comes before apple i.e. len1 <len2
	//but unsorted if the other way around and len1 < len2 will return false
        return len1 < len2;
    }
}
