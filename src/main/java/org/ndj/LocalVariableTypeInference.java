package org.ndj;

import java.util.Arrays;

public class LocalVariableTypeInference {
    public static void main(String[] args)  {
        // old Java
        String oldName = "DONGJU";
        int[] oldNums = new int[]{1,2,3,4,5};

        // modern Java
        var newName = "DONGJU";
        var nums = new int[]{1,2,3,4,5};


        System.out.println("oldName > " + oldName + " Class  > " + oldName.getClass());
        System.out.println("oldNums > " + Arrays.toString(oldNums) + " Class  > " + oldNums.getClass());
        System.out.println("newName > " + newName + " Class  > " + newName.getClass());
        System.out.println("nums > " + Arrays.toString(nums) + " Class  > " + nums.getClass());

    }
}