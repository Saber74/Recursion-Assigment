/*
/*Recursion assigment #1
Nizar Alrifai
Taking in a menu the program calculates the best combination of food so that the lowest
tip percentage is paid as long as the tip is a minimum of 15%
 */
public class question1 {
    public static String foodglobal = ""; //menu to display
    public static double tipglobal =0; //the tip variable constantly used to check wether tip within current case is better than the smallest previous tip
    public static double percent; //a useless variable used to automate percentage
    public static void main(String args[]) {
        String[] food = {"Bandera Pizza Bread", "Boston's Pizza Bread", "Garlic Twist Bread", "Milkshake", "Sun-Dried Tomato Bruschetta", "Three Cheese Toast", "Double Order wings", "Starter Size wings", "Cactus Nachos", "Baked Ravioli Bites", "Southwest Quesadilla"};
        double[] itemsPrices = {6.49, 5.35, 7.49, 5.35, 6.99, 6.35, 16.49, 8.99, 10.29, 8.49, 9.25};
        double cash = 32.78;
        order(food, itemsPrices, cash);
        System.out.println(foodglobal); //printing what we ordered
        System.out.println(percent); //printing tip percent
    }

    public static void order(String[] menu, double[] foodPrice, double money) {//non recursive takes the menu, prices and the cash on the user
        double tip = money - money / 1.15; //making sure there is a 15% min tip
        double price = 0; //the total amount to be paid for food
        money -= tip; //maximum amount price can ever be
        String selection = ""; //to store what food was picked
        int index = 0; //variable to check what food item we are on
        order(menu, selection, foodPrice, price, tip, index, money); //calling recursive function

    }

    public static void order(String[] available, String buy, double[] availablePrice, double pay, double tip, int index, double cash) {
   /*recursive function we take a string array being the menu, then the empty string of what we wanna order, followed by price of item
        then pay which resembles amount spent on food , then tip,then index of food menu, then max money that can be spent*/
        if (index == available.length) {//index went through all items
            if (pay <= cash) { //if the money used on food equals or is less than max amount of money we can use for food
                tip += cash - pay; //adding whatever remains of the money to tip
                if(tipglobal==0) tipglobal=tip+1; //making sure thee first time the global variable is always bigger
                if (tip < tipglobal) { //checking if tip is smaller, if it is then that is the new smallest tip and best order so far
                    tipglobal = tip;
                    foodglobal = buy;
                    percent =100* tip / pay;
                }
            }
        }
        else {//checking to see when we reach the cheese toast if it is better to order it, order it with the dip, or not order either at all
            if(available[index]=="Three Cheese Toast"){
                order(available, buy + "\n" + available[index], availablePrice, pay + availablePrice[index], tip, index + 1, cash);
                order(available, buy + "\n" + available[index]+"\n\t"+"Add Bolognese Dip", availablePrice, pay + availablePrice[index]+1.99, tip, index + 1, cash);
                order(available, buy, availablePrice, pay, tip, index + 1, cash);
            }
            else if(available[index]=="Cactus Nachos"){ //same as cheese toast but for nachos
                order(available, buy + "\n" + available[index], availablePrice, pay + availablePrice[index], tip, index + 1, cash);
                order(available, buy + "\n" + available[index]+"\n\t"+"Add Spicy Chicken", availablePrice, pay + availablePrice[index]+2, tip, index + 1, cash);
                order(available, buy, availablePrice, pay, tip, index + 1, cash);
            }
            else { //recursive call where we either take the meal or not, if take it we add it to the string and add its price
                order(available, buy + "\n" + available[index], availablePrice, pay + availablePrice[index], tip, index + 1, cash);
                order(available, buy, availablePrice, pay, tip, index + 1, cash);
            }
        }
    }
}

