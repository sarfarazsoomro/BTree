/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adsproject;

/**
 *
 * @author ssoomro
 */
public class BTreeHash {
    BTree[] BTarr;
    int s;
    int order;
    
    /**
     * Constructor, takes si:number of buckets and o:order of BTree
     * @param si
     * @param o
     */
    public BTreeHash(int si, int o) {
        s = si;
        if( si%2 == 0 ) {
            s=si+1;
        }
        BTarr =  new BTree[s];
        order=o;
    }
    
    /**
     * insert the key/value pair into BTreeHash
     * @param k
     * @param v
     */
    public void insert(int k, int v) {
        int x = xhash(k);
        if(BTarr[x]!=null) {
            BTarr[x].insert(k,v);
        } else {
            BTarr[x] = new BTree(order);
            BTarr[x].insert(k,v);
        }
    }
    
    /**
     * search for key k
     * @param k
     * @return
     */
    public boolean search(int k) {
        //x would have the correct position of the AVLTree in which k is to be 
        //inserted
        int x = xhash(k);
        if(BTarr[x]!=null) {
            return BTarr[x].search(k);
        } else {
            return false;
        }
    }    
    
    private int xhashCode(int k) {
        return (k>=0)?k:(k*-1);
    }
    
    private int xhash(int k) {
        return xhashCode(k)%s;
    }
    
    /**
     * String representation of BTreeHash
     * @return
     */
    public String toStringVerbose() {
        String s = "";
        for(int i=0; i<BTarr.length;i++) {
            s += "tree "+i+" count:"+BTarr[i].count+"\n";
        }
        return s;
    }
    
    /**
     * Sorted order output
     * @return
     */
    public String inOrder() {
        String st="";
        for(int i=0;i<s;i++) {
            st+=BTarr[i].inOrder()+"\n";
        }
        return st;        
    }
    
    public String levelOrder() {
        String st="";
        for(int i=0;i<s;i++) {
            st+=BTarr[i].levelOrder()+"\n";
        }
        return st;        
    }    
}
