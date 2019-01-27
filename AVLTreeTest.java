package pack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
/*
 * <h1>Write a program that has 7 operations, your program should run until exit operation is selected. The 7 operations are </h1>
1. Create a Balanced Binary Search Tree using the input Strings entered from
console.
2. Find the length of the Balanced Binary Search Tree.
3. Add an element to BST
4. Delete an element from BST.
5. Print Elements of the BST.
6. Check if BST is Max Binary Heap or not.
7. Find the number of Anagrams for each input string in the BST.
8. Exit


@author Amit Pandit
@version 1.0
@since   10-08-2018
 */
/* Class : AVLNode
 * This class contains constructors for node initialization of a Tree.
 */
class AVLNode {
 AVLNode left, right;
 String data;
 int anagramCount;
 int height;

 public AVLNode() {
  left = null;
  right = null;
  data = null;
  height = 0;
 }
 public AVLNode(String n) {
  left = null;
  right = null;
  data = n;
  height = 0;
 }
}
/* Class :  AVLTree
 * This class contains operations/functions to be carried out on a tree.
 * such as insert,delete,print,findingAnagramCount.
 */
class AVLTree {
 private AVLNode root;

 public AVLTree() {
  root = null;
 }


 /*
  * This method is use to clear the tree. 
  * @param args Unused.
  * @return root node initialized to null.
  */
 public void makeEmpty() {
  root = null;
 }

 /*
  * This method is use to insert data into the tree. 
  * @param args : data
  */
 public void insert(String data) {
  root = insert(data, root);
 }

 /*
  * This method is use to calculate height of tree. 
  * @param args : root node.
  * @return tree height.
  */
 private int height(AVLNode t) {
  return t == null ? -1 : t.height;
 }

 /*
  * This method is use to find height of tree. 
  * @param args Unused.
  * @return tree height.
  */
 public int heightOfTree() {
  return height(root);
 }


 /*
  * This method is use to check if the BST is complete binary tree or not. 
  * @param args : root node,index of node,count of number of total nodes in tree.
  * @return : boolean value
  */
 boolean isCompleteBinaryTree(AVLNode root, int index, int number_nodes) {
  if (root == null)
   return true;

  if (index >= number_nodes)
   return false;

  return isCompleteBinaryTree(root.left, 2 * index + 1, number_nodes) &&
   isCompleteBinaryTree(root.right, 2 * index + 2, number_nodes);

 }

 /*
  * This method is use to check if the BST is MAX heap or not 
  * @param args : unused
  * @return : boolean value
  */
 boolean isHeap() {
  if (root == null)
   return true;

  if (isHeapUtil(root) && isCompleteBinaryTree(root, 0, countNodes())) {
   return true;
  }
  System.out.println(">> BST is not Complete Binary Tree");
  return false;
 }

 /*
  * This method is use to compare the two values i.e left and right 
  * @param args : right value,left value
  * @return : boolean value
  */
 private int max(int lhs, int rhs) {
  return lhs > rhs ? lhs : rhs;

 }
 /*
  * This method is use to check if the BST is MAX heap or not 
  * @param args : root node
  * @return : boolean value
  */
 boolean isHeapUtil(AVLNode t) {

  if (t.left == null && t.right == null)
   return true;

  if (t.right == null) {
   AVLNode t1 = t.left;
   int results = (t.data).compareTo(t1.data);
   if (results > 0) {
    return true;
   }
  } else {

   AVLNode t1 = t.left;
   AVLNode t2 = t.right;

   int results = (t.data).compareTo(t1.data);
   int results2 = (t.data).compareTo(t2.data);
   if (results > 0 && results2 < 0)
    return isHeapUtil(t.left) && isHeapUtil(t.right);
   else
    return false;
  }
  return false;
 }

 /*
  * This method is use to insert a data node into the tree
  * @param args : root node
  * @return : node
  */
 private AVLNode insert(String x, AVLNode t) {

  if (t == null) {
   t = new AVLNode(x);
  } else if (x.compareTo(t.data) < 0) {
   t.left = insert(x, t.left);
   if (height(t.left) - height(t.right) == 2)
    if (x.compareTo(t.left.data) < 0)
     t = rotateWithLeftChild(t);
    else
     t = doubleWithLeftChild(t);
  } else if (x.compareTo(t.data) > 0) {
   t.right = insert(x, t.right);
   if (height(t.right) - height(t.left) == 2)
    if (x.compareTo(t.right.data) > 0)
     t = rotateWithRightChild(t);
    else
     t = doubleWithRightChild(t);
  } else
   System.out.println(">> Duplicate entries identified !! Only one value will be considered");
  t.height = max(height(t.left), height(t.right)) + 1;
  return t;
 }
 /*
  * This method is use to balance AVL tree by performing rotate left
  * @param args : node
  * @return : node
  */
 private AVLNode rotateWithLeftChild(AVLNode k2) {
  AVLNode k1 = k2.left;
  k2.left = k1.right;
  k1.right = k2;
  k2.height = max(height(k2.left), height(k2.right)) + 1;
  k1.height = max(height(k1.left), k2.height) + 1;
  return k1;
 }
 /*
  * This method is use to balance AVL tree by performing rotate right
  * @param args : node
  * @return : node
  */
 private AVLNode rotateWithRightChild(AVLNode k1) {
  AVLNode k2 = k1.right;
  k1.right = k2.left;
  k2.left = k1;
  System.out.println("reachd mile 2");
  k1.height = max(height(k1.left), height(k1.right)) + 1;
  k2.height = max(height(k2.right), k1.height) + 1;
  return k2;
 }
 /*
  * This method is use to balance AVL tree by performing double rotate left
  * @param args : node
  * @return : node
  */
 private AVLNode doubleWithLeftChild(AVLNode k3) {
  k3.left = rotateWithRightChild(k3.left);
  return rotateWithLeftChild(k3);
 }
 /*
  * This method is use to balance AVL tree by performing double rotate right
  * @param args : node
  * @return : node
  */
 private AVLNode doubleWithRightChild(AVLNode k1) {
  k1.right = rotateWithLeftChild(k1.right);
  return rotateWithRightChild(k1);
 }

 /*
  * This method is use to calculate total number of nodes in the tree
  * @param args : unused
  * @return : unused
  */
 public int countNodes() {
  return countNodes(root);
 }
 private int countNodes(AVLNode r) {
  if (r == null)
   return 0;
  else {
   int l = 1;
   l += countNodes(r.left);
   l += countNodes(r.right);
   return l;
  }
 }


 public List < String > extractValues() {
  return extractValues(root);
 }

 /*
  * This method is use to print the inorder traversal of tree
  * @param args : unused
  * @return : unused
  */
 public void inorder() {
  inorder(root);
 }

 private void inorder(AVLNode r) {
  if (r != null) {
   inorder(r.left);
   System.out.print(r.data + " ");
   inorder(r.right);
  }
 }

 private static List < String > extractValues(AVLNode n) {
  List < String > result = new ArrayList < > ();
  if (n.left != null) {
   result.addAll(extractValues(n.left));
  }

  if (n.right != null) {
   result.addAll(extractValues(n.right));
  }

  result.add(n.data);

  return result;
 }

 /*
  * This method is use to print the preorder traversal of tree
  * @param args : unused
  * @return : unused
  */
 public void preorder() {
  preorder(root);
 }
 private void preorder(AVLNode r) {
  if (r != null) {

   System.out.print(r.data + " ");
   preorder(r.left);
   preorder(r.right);
  }
 }

 /*
  * This method is use to print the postorder traversal of tree
  * @param args : unused
  * @return : unused
  */
 public void postorder() {
  postorder(root);
 }
 private void postorder(AVLNode r) {
  if (r != null) {
   postorder(r.left);
   postorder(r.right);
   System.out.print(r.data + " ");
  }
 }
 /*
  * This method is use to find minimum node either on right or left
  * @param args : root node
  * @return : node
  */
 private AVLNode findMin(AVLNode t) {
  if (t == null)
   return t;

  while (t.left != null)
   t = t.left;
  return t;
 }

 /*
  * This method is use remove the give data node
  * @param args : unused
  * @return : unused
  */
 public void remove(String x) {
  root = remove(x, root);
 }
 private AVLNode remove(String x, AVLNode t) {
  if (t == null)
   return t;

  int compareResult = x.compareTo(t.data);

  if (compareResult < 0) {
   t.left = remove(x, t.left);

  } else if (compareResult > 0) {
   t.right = remove(x, t.right);


  } else if (t.left != null && t.right != null) {
   t.data = findMin(t.right).data;
   t.right = remove(t.data, t.right);
  } else
   t = (t.left != null) ? t.left : t.right;


  AVLNode rootNode = root;
  rootNode.height = max(height(rootNode.left), height(rootNode.right)) + 1;
  int balance = getBalance(root);

  // Left Left Case  
  if (balance > 1 && getBalance(root.left) >= 0)
   return rotateWithRightChild(root);

  // Left Right Case  
  if (balance > 1 && getBalance(root.left) < 0) {
   // root.left = leftRotate(root.left);  
   return doubleWithRightChild(root);
  }

  // Right Right Case  
  if (balance < -1 && getBalance(root.right) <= 0)
   return rotateWithLeftChild(root);

  // Right Left Case  
  if (balance < -1 && getBalance(root.right) > 0) {
   return doubleWithLeftChild(root);
  }

  return t;
 }

 /*
  * This method is use to get the balance of current node
  * @param args : root node
  * @return : returns balance
  */
 int getBalance(AVLNode N) {
  if (N == null)
   return 0;
  return height(N.left) - height(N.right);
 }


}
/* Class : AnagramFinding
 * This class contains methods required to find the count of anagram for each given string.
 */
class AnagramFinding {
 /*
  * This method is use to check if the anagram exists for given string and finding out the count for same.
  * @param args : list of strings given
  * @return : prints the string and its anagram count
  */
 static void anagramExists(List < String > extractValues) {

  HashMap < Integer, List < String > > map = new HashMap < > ();
  for (int i = 0; i < extractValues.size(); i++) {
   String word = extractValues.get(i);
   char[] letters = word.toCharArray();
   Arrays.sort(letters);
   String newWord = new String(letters);
   int n = newWord.hashCode();
   if (map.containsKey(n)) {
    List < String > words = map.get(n);
    words.add(word);
    map.put(n, words);
   } else {
    List < String > words = new ArrayList < > ();
    words.add(word);
    map.put(n, words);
   }
  }


  for (Integer i: map.keySet()) {
   List < String > values = map.get(i);

   if (values.size() > 1)
    for (int j = 0; j < values.size(); j++) {
     System.out.print(values.get(j) + "-" + (values.size() - 1) + "\n");
    }
   else {
    System.out.println(values + "-" + (values.size() - 1) + "\n");
   }
  }

 }



}
/* Class : AVLTreeTest
 * This class is the main class where all user related functionality is implemented.
 */
public class AVLTreeTest {
 public static void main(String[] args) {
  Scanner scan = new Scanner(System.in);
  AVLTree avlt = new AVLTree();

  char ch;
  /*  Perform tree operations  */
  do {
   System.out.println("\nAVLTree Operations\n");
   System.out.println("1.Create a Balanced Binary Search Tree");
   System.out.println("2.Find length of Balanced Binary Search Tree");
   System.out.println("3.Add element to BST");
   System.out.println("4.Delete element from BST");
   System.out.println("5.Print elements of BST");
   System.out.println("6.Check if BST is Max heap or not");
   System.out.println("7.Find the number of Anagrams for each input string in the BST");
   System.out.println("8.Exit");

   int choice = scan.nextInt();
   switch (choice) {
    case 1:
     System.out.println("Enter the size of string array");
     int size = 0;
     String[] arrayNodes = null;
     try {
      size = scan.nextInt();
      arrayNodes = new String[size];
     } catch (InputMismatchException e) {
      System.out.println("**Please enter correct Integer value");
     }

     for (int i = 0; i < size; i++) {
      System.out.println("Enter element " + i);
      BufferedReader ob = new BufferedReader(new InputStreamReader(System.in));
      String s;
      try {
       s = ob.readLine();
       arrayNodes[i] = s;
       avlt.insert(s);

      } catch (IOException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
      }
     }
     System.out.println("Balanced BST has been constructed using below provided elements:");
     System.out.print("\nIn order : ");
     avlt.inorder();
     break;
    case 2:
     System.out.println("Length of tree:" + avlt.heightOfTree());
     break;
    case 3:

     System.out.println("Enter string element to insert");
     String s;
     BufferedReader ob = new BufferedReader(new InputStreamReader(System.in));
     try {
      s = ob.readLine();
      avlt.insert(s);
     } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
     }
     //System.out.println("Nodes = "+ avlt.countNodes());
     System.out.print("\nIn order : ");
     avlt.inorder();
     break;
    case 4:
     //System.out.println("Empty status = "+ avlt.isEmpty());
     System.out.println("Enter string element to remove");
     String rm;
     BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
     try {
      rm = obj.readLine();
      avlt.remove(rm);
     } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
     }
     System.out.print("\nIn order : ");
     avlt.inorder();
     break;
    case 5:
     System.out.print("\nPost order : ");
     avlt.postorder();
     System.out.print("\nPre order : ");
     avlt.preorder();
     System.out.print("\nIn order : ");
     avlt.inorder();

     break;
    case 6:


     if (avlt.isHeap()) {
      System.out.println("BST is a max Heap !!");
     } else {
      System.out.println("BST is not a max Heap !!");
     }
     System.out.print("\nIn order : ");
     avlt.inorder();
     break;
    case 7:
     System.out.println("Anagrams count");
     AnagramFinding.anagramExists(avlt.extractValues());

     break;
    default:
     System.out.println("Wrong Entry \n ");
     break;
   }

   System.out.println("\nDo you want to continue (Type y or n) \n");
   ch = scan.next().charAt(0);
  } while (ch == 'Y' || ch == 'y');
 }
}