/*
注意：
1.数组大，防止溢出。
*/
class Solution {
    public List<Boolean> prefixesDivBy5(int[] A) { 
        int ALen = A.length;
        //List是接口不能实例化
        List<Boolean> answer = new ArrayList<Boolean>();
        /*
        long temp = 0;
        for(int i=0; i < ALen; i++) {
            temp = temp * 2 + A[i];
            //A数组长度在30000之间，不能用变量存储中间数据，会溢出。
            if(temp % 5 == 0)
                answer.add(true);
            else
                answer.add(false);
        }
        */

        //只要保留余数，判断每一次的余数即可，若上一代可以整除，则下一代乘2后仍可整除。
        int remainder = 0;
        for(int i = 0; i < ALen; i++) {
            remainder = ((remainder << 1) + A[i]) % 5;
            if(remainder == 0) 
                answer.add(true);
            else
                answer.add(false);
        }
        return answer;
    }
}