package DOLAT_CAPITAL_9July;

import java.util.Scanner;

/**
 * The Summation class demonstrates the OOP principle of Encapsulation.
 * By marking data fields private and providing controlled public entry points
 * (Constructors, Getters, and Setters), we protect the internal state of the object.
 */
class Summation{
    // Private field - Hidden from direct external access/modification (In boilerplate code it was given as a Public field.)
    private int N;

    /**
     * Option 1: Default Constructor
     * Useful when you want to instantiate the object first and set the data later via setters.
     */
    public Summation() {
    }

    /**
     * Option 2: Parameterized Constructor
     * A cleaner alternative to initialize the object with data immediately upon creation.
     */
    public Summation(int n) {
        this.N = n;
    }
    /**
     * Setter Method
     * Standard way to safely update or initialize the private variable 'n'.
     */
    public void setN(int N){
        this.N=N;
    }
    /**
     * Getter Method
     * Essential for read access, allowing external classes to view 'n' safely without modifying it.
     */
    public int getN() {
        return this.N;
    }
    public int getSum(){
        return (this.N*(this.N+1))/2;
    }
}
public class Problem_2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        Summation series=new Summation();
        series.setN(N);
        System.out.println(series.getSum());
    }
}
