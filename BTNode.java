/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adsproject;

import java.util.ArrayList;

/**
 *
 * @author sarfaraz
 */
public class BTNode {
    int order;
    //whether current node is a leaf node or not
    Boolean leaf = true;
    ArrayList<BinNode> keys;
    //pointer to the parent of this node
    BTNode parent = null;
    
    /**
     * Constructor, takes the order of the btree
     * @param o
     */
    public BTNode(int o){
        order = o;
        keys = new ArrayList<BinNode>();
    }
    
    /**
     * insert a new BinNode in the current BTNode. BinNode represents a key/value
     * pair with left and right references to child BTNodes
     * @param n
     */
    public void insert(BinNode n){
        keys.add(n);
    }
    
    @Override
    public String toString(){
        return keys.toString();
    }
    
    /**
     * searches for the key in the current BTNode or any of it's children
     * @param k
     * @return
     */
    public Boolean contains(int k){
        Boolean found = false;
        //go through all the keys and figure which child to go down to
        for(int i=0; i<keys.size(); i++) {
            if(keys.get(i).key==k) {
                found=true;
                break;
            } else if( i==0 && k<keys.get(i).key ) {
                found = keys.get(i).left.contains(k);
                break;
            } else if( i==keys.size()-1 && k>keys.get(i).key ) {
                found = keys.get(i).right.contains(k);
                break;
            } else if( i<keys.size()-1 && k>keys.get(i).key && k<keys.get(i+1).key) {
                found = keys.get(i).right.contains(k);
                break;
            }
        }
        return found;
    }
    
    /**
     * Outputs the in-order traversal of this node
     * @return
     */
    public String inOrder() {
        String s="";
        for(int i=0;i<keys.size();i++) {
            if(i==0) {
                if(keys.get(i).left!=null)
                    s+=keys.get(i).left.inOrder();                
            }
            s+=keys.get(i).val+" ";
            if(keys.get(i).right!=null)
                s+=keys.get(i).right.inOrder();
        }
        return s;
    }
    
    public String levelOrder() {
        String s="";
        ArrayList<BinNode> temp = keys;
        ArrayList<BinNode> temp2 = keys;
        int size = 0;
        while(temp2.size()>0) {
            for(int i=0;i<temp2.size();i++) {
                s+=temp2.get(i).key+" ";
            }
            temp2 = new ArrayList<BinNode>();
            for(int i=0;i<temp.size();i++) {
                if(i==0){
                    if(temp.get(i).left!=null){
                        temp2.addAll(temp.get(i).left.keys);
                    }
                }
                if(temp.get(i).right!=null)
                    temp2.addAll(temp.get(i).right.keys);
            }
            temp=temp2;
        }
        return s;
    }
}
