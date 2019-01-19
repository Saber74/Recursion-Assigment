/*Recursion assigment #2
Nizar Alrifai
Determines the shorest path and the time it takes to reach the bottom right (7,7) point starting from
(0,0) in an 8 by 8 grid
 */

public class question2 {
    public static int timeglobal=0; //global variable to keep track of time
    public static String pathglobal=""; //global variable to keep track of path
    public static void directions(int[][] route) { //method for declaration of required variables, takes the grid as paramter
        int total = 0; //to keep track of time with in each case
        timeglobal=1001; //resetting global to a value higher than max time
        String directions = ""; //keeping track od direction within each case
        int index1=0; //keeps track of how many right turns were taken
        int index2=0; //keeps track of how many down turns were taken
        directions(total,directions,index1,index2,route); //calling the recursive functions
    }
    //recursive function that navigates through the grid to find the shortest path
    public static void directions(int time,String path,int indexr,int indexd,int[][] route){
        if(indexr==7&&indexd==7){ //base case checks the person is at 7,7
            time+=route[7][7]; //adding the last square time
            if(time<timeglobal){
                pathglobal=path;
            timeglobal=Math.min(timeglobal,time); //checking to see what path is faster, the fastest previous path or the new path
            }
        }
        else {
            if (indexr == 7) { //if the person reached max amount of right turns
                //this means that the person can only go down afterwards
                directions(time + route[indexr][indexd], path + "D", indexr, indexd + 1, route);
            }
            else if (indexd == 7) { //max amount of down turns
                //meaning person is forced to go right
                directions(time + route[indexr][indexd], path + "R", indexr + 1, indexd, route);
            }
            else { /*for every node it gives the next 2 branches as either an addition of a right turn or left turn
                time increases to account for the step the person is on, then an r or d are added to path indicating
                direction of movement
                 1 is added to indexr if the person moves to the right, and 1 added to indexd if person moves down*/
                directions(time + route[indexr][indexd], path + "R", indexr + 1, indexd, route);
                directions(time + route[indexr][indexd], path + "D", indexr, indexd + 1, route);
            }
        }
        }

    public static void main(String[] args){
        int[][] hi= new int[][]{{0,21,20,5,25,25,35,15},
                {12,26,43,29,15,26,15,12},
                {7,18,23,28,36,32,12,18},
                {43,34,35,18,25,18,21,25},
                {32,41,23,9,21,17,24,14},
                {12,9,20,42,9,19,26,22},
                {30,17,17,35,14,25,14,21},
                {15,21,37,24,19,15,35,15}
        };
        directions(hi);
        System.out.println(pathglobal);
        System.out.println(timeglobal);
    }

}
