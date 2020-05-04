package poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @author runb-cs112
 *
 */
public class Polynomial {
    
    /**
     * Reads a polynomial from an input stream (file or keyboard). The storage format
     * of the polynomial is:
     * <pre>
     *     <coeff> <degree>
     *     <coeff> <degree>
     *     ...
     *     <coeff> <degree>
     * </pre>
     * with the guarantee that degrees will be in descending order. For example:
     * <pre>
     *      4 5
     *     -2 3
     *      2 1
     *      3 0
     * </pre>
     * which represents the polynomial:
     * <pre>
     *      4*x^5 - 2*x^3 + 2*x + 3 
     * </pre>
     * 
     * @param sc Scanner from which a polynomial is to be read
     * @throws IOException If there is any input error in reading the polynomial
     * @return The polynomial linked list (front node) constructed from coefficients and
     *         degrees read from scanner
     */
    public static Node read(Scanner sc) 
    throws IOException {
        Node poly = null;
        while (sc.hasNextLine()) {
            Scanner scLine = new Scanner(sc.nextLine());
            poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
            scLine.close();
        }
        return poly;
    }
    
    /**
     * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
     * The returned polynomial MUST have all new nodes. In other words, none of the nodes
     * of the input polynomials can be in the result.
     * 
     * @param poly1 First input polynomial (front of polynomial linked list)
     * @param poly2 Second input polynomial (front of polynomial linked list
     * @return A new polynomial which is the sum of the input polynomials - the returned node
     *         is the front of the result polynomial
     */
    private static Node removeZero(Node head)
    {
        Node curr = head;
        Node prev = null;
        
        while(curr!=null)
        {
             if (curr.term.coeff == 0) 
             {
                 if (prev == null) 
                 {       // target is the first element 
                     head = curr.next;
                 } 
                 else 
                 {
                    prev.next = curr.next;
                 }
              } 
             else 
             {
                prev = curr;
             }
             curr = curr.next;
        }
        return head;
    }

    public static Node add(Node poly1, Node poly2) 
    {
        //do condition of poly or poly1 is null, return the latter
        //remove 0s
        Node ptrPoly1 = poly1; 
        Node ptrPoly2 = poly2; 
        Node front = null; //head of Poly Array
    
        front = new Node(0,0, null);
        Node ptr = front;
                //delete frontNode after begininng frontNode
        //TEST END
        while(ptrPoly2 != null || ptrPoly1!=null)
        {
            if(ptrPoly1==null)
            {
                ptr.next = new Node(ptrPoly2.term.coeff, ptrPoly2.term.degree,null);
                ptrPoly2 = ptrPoly2.next;
            }
            else if(ptrPoly2==null)
            {
                ptr.next = new Node(ptrPoly1.term.coeff, ptrPoly1.term.degree,null);
                ptrPoly1 = ptrPoly1.next;
            }
            else if((ptrPoly1.term.degree > ptrPoly2.term.degree))
            {
                ptr.next = new Node(ptrPoly2.term.coeff, ptrPoly2.term.degree,null);
                ptrPoly2 = ptrPoly2.next;
            }
            else if((ptrPoly2.term.degree > ptrPoly1.term.degree))
            {
                ptr.next = new Node(ptrPoly1.term.coeff, ptrPoly1.term.degree,null);
                ptrPoly1 = ptrPoly1.next;
            }
            else if(ptrPoly1.term.degree==ptrPoly2.term.degree)
            {

                float combinedCoefficient = (ptrPoly1.term.coeff) + (ptrPoly2.term.coeff); 
                ptr.next = new Node(combinedCoefficient, ptrPoly1.term.degree,null);
                //return ptr;
                ptrPoly1 = ptrPoly1.next;
                ptrPoly2 = ptrPoly2.next;
            }
            
            //if(ptr.next!=null) 
            //{
            ptr=ptr.next;
            //}
        }
        Node returnNode = removeZero(front.next);
        return returnNode;
        
    }
    
    /**
     * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
     * The returned polynomial MUST have all new nodes. In other words, none of the nodes
     * of the input polynomials can be in the result.
     * 
     * @param poly1 First input polynomial (front of polynomial linked list)
     * @param poly2 Second input polynomial (front of polynomial linked list)
     * @return A new polynomial which is the product of the input polynomials - the returned node
     *         is the front of the result polynomial
     */
    public static Node multiply(Node poly1, Node poly2) 
    {
    
        Node ptrPoly1 = poly1;
        Node ptrPoly2 = poly2;
        Node term1 = null;
        term1 = new Node(0,0, null);
        Node ptr = term1;
        //TEST
        Node cloneOfTerm = null;
        Node returnedNode =null;
        //TEST
        while(ptrPoly1!=null)
        {
            while(ptrPoly2!=null)
            {
                float combCoeff = ptrPoly1.term.coeff * ptrPoly2.term.coeff;
                int combDegree = ptrPoly1.term.degree + ptrPoly2.term.degree;
                ptr.next = new Node(combCoeff, combDegree, null);
                
                
                ptr = ptr.next;
                //ptr1 = ptr1.next;
                ptrPoly2 = ptrPoly2.next;
                //LINE BELOW IS TEST
                //cloneOfTerm = clone(term1.next);
                //term1.next =null;
            }
            cloneOfTerm = clone(term1.next);
            //LINE BELOW IS TEST
            returnedNode = add(cloneOfTerm, returnedNode);
            ptr=term1;
            term1.next=null;
            //term2 = clone(term1.next);
            ptrPoly2 = poly2;
            ptrPoly1 = ptrPoly1.next;
        }
        //Node temp2 = add(temp.next,temp.next);
        //COMBINE LIKE TERMS
        /*
         Node term1 = temp1;
         Node term2 = temp2;
         Node likeTerms = add(term1, temp2)
         */
        //Node cloned = clone(term1.next);
        Node removeZero = removeZero(returnedNode);
        return removeZero;
    }
        
    private static Node clone(Node head)
    {
        Node current = head;
        Node newList = null; 
        Node tail = null;    
        while (current != null)
        {
            if (newList == null)
            {
                newList = new Node(current.term.coeff, current.term.degree, newList);
                tail = newList;
            }
            else
            {
                tail.next = new Node(current.term.coeff, current.term.degree ,tail.next);
                tail = tail.next;
            }
            current = current.next;
        }
            return newList;
    }
    /**2
     * Evaluates a polynomial at a given value.
     * 
     * @param poly Polynomial (front of linked list) to be evaluated
     * @param x Value at which evaluation is to be done
     * @return Value of polynomial p at x
     */
    public static float evaluate(Node poly, float x) 
    {
        //REMOVE NODES WHERE [(-1,1)]+[(-1,1)] = [(0,1)]
        
        Node ptr = poly;
        float numEval = 0;
        while(ptr!=null)
        {
            numEval = (float) (numEval + (ptr.term.coeff*(Math.pow(x, ptr.term.degree))));
            ptr = ptr.next;
        }
        return numEval;
    }
    
    /**
     * Returns string representation of a polynomial
     * 
     * @param poly Polynomial (front of linked list)
     * @return String representation, in descending order of degrees
     */
    public static String toString(Node poly) {
        if (poly == null) {
            return "0";
        } 
        
        String retval = poly.term.toString();
        for (Node current = poly.next ; current != null ;
        current = current.next) {
            retval = current.term.toString() + " + " + retval;
        }
        return retval;
    }    
}