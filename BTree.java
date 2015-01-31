/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adsproject;

import java.util.Collections;

/**
 *
 * @author sarfaraz
 */
public class BTree {
    BTNode t;
    int order;
    int count=0;
    /**
     * Constructor, takes the order of the BTree as input
     * @param o
     */
    public BTree(int o) {
        order = o;
        t=new BTNode(order);
    }
    
    @Override
    public String toString() {
        return t.toString();
    }
    
    /**
     * Wrapper Insert the key/value pair into BTree
     * @param k
     * @param v
     */
    public void insert(int k, int v) {
        insertS(t, k, v);
        count++;
    }
    
    /**
     * The actual function that inserts into the BTree in non-recursive way. That's
     * why needs a reference to the BTNode
     * All the heavy lifting is done here
     * @param tr: BTNode to insert key into
     * @param k:key
     * @param v:value
     */
    private void insertS(BTNode tr, int k, int v) {
        BTNode temp = tr;
        if(temp.leaf==true) {
            //this is a leaf node
            temp.keys.add(new BinNode(k,v));
            Collections.sort(temp.keys);
            //this variable keeps track of whether we are going top down(inserting)
            //or coming up after inserting to fix the tree due to some overfull node
            //down
            Boolean comingFromDown=false;
            //if after inserting the node becomes over full
            while(temp.keys.size()==order) {
                //once we are in this loop we can come up to non leaf nodes as well
                //i.e. when fixing the tree imbalance
                
                //time to break up the tree
                if(temp.parent==null){
                    //root node
                    int mid = (int)Math.floor((double)order/2);
                    //A would have all keys 0 to mid-1
                    BTNode A = new BTNode(order);
                    //B would have all keys mid+1 to end
                    BTNode C = new BTNode(order);
                    //while temp would retain the mid key
                    A.parent = temp;
                    for(int i=0;i<mid;i++){
                        A.insert(temp.keys.get(i));
                    }
                    for(int i=0;i<mid;i++){
                        temp.keys.remove(0);
                    }
                    //if we are coming from downstair, this is definitely not a leaf
                    if(comingFromDown){
                        A.leaf=false;
                    }
                    //All the children of A had temp node as their parent, we 
                    //need to point all of them to the newly created node
                    for(int i=0;i<A.keys.size();i++) {
                        if(A.keys.get(i).left!=null){
                            A.keys.get(i).left.parent = A;
                            A.leaf=false;
                        }                        
                        if(A.keys.get(i).right!=null){
                            A.keys.get(i).right.parent = A;
                            A.leaf=false;
                        }
                    }
                    
                    C.parent = temp;
                    for(int i=1;i<temp.keys.size();i++){
                        C.insert(temp.keys.get(i));
                    }
                    //if we are coming from downstair, this is definitely not a leaf
                    if(comingFromDown){
                        C.leaf=false;
                    }
                    
                    //All the children of C had temp node as their parent, we 
                    //need to point all of them to the newly created node
                    for(int i=0;i<C.keys.size();i++) {
                        if(C.keys.get(i).left!=null){
                            C.keys.get(i).left.parent = C;
                            C.leaf=false;
                        }                        
                        if(C.keys.get(i).right!=null){
                            C.keys.get(i).right.parent = C;
                            C.leaf=false;
                        }
                    }
                    
                    for(int i=0;i<mid;i++){
                        if( temp.keys.size()>1 )
                            temp.keys.remove(1);
                    }
                    temp.keys.get(0).left = A;
                    temp.keys.get(0).right = C;
                    temp.leaf=false;
                } else {
                    //not a root node
                    int mid = (int)Math.floor((double)order/2);
                    BinNode B = new BinNode(temp.keys.get(mid).key, temp.keys.get(mid).val);
                    BTNode BC = new BTNode(order);
                    BC.parent = temp.parent;
                    for(int i=mid+1;i<temp.keys.size();i++){
                        BC.insert(temp.keys.get(i));
                    }
                    
                    //All the children of BC had temp node as their parent, we 
                    //need to point all of them to the newly created node
                    for(int i=0;i<BC.keys.size();i++) {
                        if(BC.keys.get(i).left!=null){
                            BC.keys.get(i).left.parent = BC;
                            BC.leaf=false;
                        }
                        if(BC.keys.get(i).right!=null){
                            BC.keys.get(i).right.parent = BC;
                            BC.leaf=false;
                        }
                    }                    
                    
                    B.right=BC;
                    B.left=temp;
                    for(int i=0;i<=mid;i++){
                        if(temp.keys.size()>mid)
                            temp.keys.remove(mid);
                    }
                    if(comingFromDown) {
                        temp.leaf = false;
                    }
                    temp.parent.keys.add(B);
                    temp.parent.leaf=false;
                    Collections.sort(temp.parent.keys);
                    for(int i=0;i<temp.parent.keys.size()-1;i++){
                        temp.parent.keys.get(i+1).left = temp.parent.keys.get(i).right;
                    }
                    //temp.leaf=false;
                    temp = temp.parent;
                    comingFromDown=true;
                }
            }
        } else {
            //find the appropriate leaf node to insert the new key into
            if( k < temp.keys.get(0).key ) {
                insertS(temp.keys.get(0).left, k, v);
            } else if(k>temp.keys.get(temp.keys.size()-1).key) {
                insertS(temp.keys.get(temp.keys.size()-1).right, k, v);
            } else {
                for(int i=0;i<temp.keys.size()-1;i++) {
                    if(k>temp.keys.get(i).key && k<temp.keys.get(i+1).key) {
                        insertS(temp.keys.get(i).right, k, v);
                        break;
                    }
                }
            }
        }
    }
    
    /**
     * searches for the key in the BTree
     * @param k
     * @return
     */
    public Boolean search(int k) {
        return t.contains(k);
    }
    
    /**
     * Outputs sorted order traversal for BTree
     * @return
     */
    public String inOrder() {
        return t.inOrder();
    }
    
    public String levelOrder(){
        return t.levelOrder();
    }
}
