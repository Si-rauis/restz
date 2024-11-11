package AlgorithmStudy;

import java.util.*;

/**
 * @title  : 캐시
 * @author : SI YOUNG
 */

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        if(cacheSize == 0) return cities.length * 5;

        List<String> cache = new ArrayList<>();
        for(String city : cities) {
            String lowcity = city.toLowerCase();
            if(cache.contains(lowcity)) {
                cache.remove(lowcity);
                cache.add(lowcity);
                answer += 1;
            } else {
                if (cache.size() >= cacheSize) cache.remove(0);
                cache.add(lowcity);
                answer += 5;
            }
        }
        return answer;
    }
}