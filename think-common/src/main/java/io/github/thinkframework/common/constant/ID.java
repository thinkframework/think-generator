package io.github.thinkframework.common.constant;

/**
 * 身份证验证
 */
public class ID {

    private static final int[] is = new int[]{7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};

    private static final char[] vs = new char[]{'1','0','X','9','8','7','6','5','4','3','2'};

    private char[] cs;

    private int n = 11;

    public ID(String id){
        cs = id.toCharArray();
    }

    /**
     *
     * @return
     */
    public Boolean validate(){
        return cs[17] == test();
    }

    /**
     *
     * @return
     */
    protected char test(){
        return  vs[(a()%n)];
    }

    protected int a(){
        return Integer.valueOf(String.valueOf(cs[0]))*is[0]+
            Integer.valueOf(String.valueOf(cs[1]))*is[1]+
            Integer.valueOf(String.valueOf(cs[2]))*is[2]+
            Integer.valueOf(String.valueOf(cs[3]))*is[3]+
            Integer.valueOf(String.valueOf(cs[4]))*is[4]+
            Integer.valueOf(String.valueOf(cs[5]))*is[5]+
            Integer.valueOf(String.valueOf(cs[6]))*is[6]+
            Integer.valueOf(String.valueOf(cs[7]))*is[7]+
            Integer.valueOf(String.valueOf(cs[8]))*is[8]+
            Integer.valueOf(String.valueOf(cs[9]))*is[9]+
            Integer.valueOf(String.valueOf(cs[10]))*is[10]+
            Integer.valueOf(String.valueOf(cs[11]))*is[11]+
            Integer.valueOf(String.valueOf(cs[12]))*is[12]+
            Integer.valueOf(String.valueOf(cs[13]))*is[13]+
            Integer.valueOf(String.valueOf(cs[14]))*is[14]+
            Integer.valueOf(String.valueOf(cs[15]))*is[15]+
            Integer.valueOf(String.valueOf(cs[16]))*is[16];
    }
}
