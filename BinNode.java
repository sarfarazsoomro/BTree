/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adsproject;

/**
 *
 * @author sarfaraz
 */
public class BinNode implements Comparable<BinNode> {
    int key;
    int val;
    BTNode left;
    BTNode right;
    
    /**
     * Constructor
     * @param k
     * @param v
     */
    public BinNode(int k, int v) {
        key = k;
        val = v;
    }

    /**
     * implementation of Comparable interface, since we would keep ArrayList of 
     * BinNode in BTNode, this helps in sorting the ArrayList
     * @param o
     * @return 
     */
    @Override
    public int compareTo(BinNode o) {
        if( this.key > o.key ) {
            return 1;
        } else if(this.key < o.key) {
            return -1;
        } else
            return 0;
    }
    
    /**
     * Override the toString method to print in easy to read format
     * @return 
     */
    @Override
    public String toString() {
        String s = "";
        s+=key+"\n";
        if(left!=null) {
            s+=left.toString()+"\n";
        }
        if(right!=null){
             s+=right.toString()+"\n";
        }
        return s;
    }
}
