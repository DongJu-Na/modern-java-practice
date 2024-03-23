package org.ndj;

public class SwitchNewStatement {

    public static void main(String[] args) {

        // old way
        int olcDayNum = 6;
        String oldDay = "";

        switch(olcDayNum) {
            case 1:
                oldDay = "Monday";
                break;
            case 2:
                oldDay = "Tuesday";
                break;
            case 3:
                oldDay = "Wednesday";
                break;
            case 4:
                oldDay = "Thursday";
                break;
            case 5:
                oldDay = "Friday";
                break;
            case 6:
                oldDay = "Saturday";
                break;
            case 7:
                oldDay = "Sunday";
                break;
            default:
                oldDay = "Not Valid";
                break;
        }

        // new way
        int newDayNum = 6;
        String newDay = switch (newDayNum) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6 -> "Saturday";
            case 7 -> "Sunday";
            default -> "Not Valid";
        };
        
        System.out.println("oldDay > " + oldDay);
        System.out.println("newDay > " + newDay);


    }

}
