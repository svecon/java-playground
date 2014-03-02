/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package libraries;

/**
 *
 * @author sveco
 */
public class SearchTree {

  private static final boolean RED = true;
  private static final boolean BLACK = false;

  private Node root;

  private class Node
  {
    int key;
    Node left, right;
    boolean color;

    public Node(int key, boolean color){
      this.key = key;
      this.color = color;
    }

  }

  private Node insert(Node h, int key)
  {
    if (h == null)
      return new Node(key, RED);

    if (key == h.key) h.key = key;
    else if (key < h.key)
      h.left = insert(h.left, key);
    else
      h.right = insert(h.right, key);

    return fixUp(h);
  }

  private int min(Node x)
  {
    if (x.left == null) return x.key;
    else                return min(x.left);
  }

  private Node delete(Node h, int key)
  {
    // int cmp = key.compareTo(h.key);
    // if (cmp < 0)
    if (key < h.key)
    {
      if (!isRed(h.left) && !isRed(h.left.left))
        h = moveRedLeft(h);
      h.left = delete(h.left, key);
    }
    else
    {
      if (isRed(h.left)) h = rotateRight(h);
     // if (cmp == 0 && (h.right == null))
      if (key == h.key && (h.right == null))
        return null;
      if (!isRed(h.right) && !isRed(h.right.left))
        h = moveRedRight(h);
    // if (cmp == 0)
      if (key == h.key)
      {
        h.key = min(h.right);
        h.right = deleteMin(h.right);
      }
      else h.right = delete(h.right, key);
    }
    return fixUp(h);
  }

  private Node deleteMin(Node h)
  {
    if (h.left == null)
      return null;
    if (!isRed(h.left) && !isRed(h.left.left))
      h = moveRedLeft(h);
    h.left = deleteMin(h.left);
    return fixUp(h);
  }

  private Node moveRedLeft(Node h)
  {
    colorFlip(h);
    if (isRed(h.right.left))
    {
      h.right = rotateRight(h.right);
      h = rotateLeft(h);
      colorFlip(h);
    }
    return h;
  }

  private Node moveRedRight(Node h)
  {
    colorFlip(h);
    if (isRed(h.left.left))
    {
      h = rotateRight(h);
      colorFlip(h);
    }
    return h;
  }

  private Node fixUp(Node h)
  {
    if (isRed(h.right))
      h = rotateLeft(h);
    if (isRed(h.left) && isRed(h.left.left))
      h = rotateRight(h);
    if (isRed(h.left) && isRed(h.right))
      colorFlip(h);
    return h;
  }

  private Node rotateLeft(Node h)
  {
    Node x = h.right;
    h.right = x.left;
    x.left = h;
    x.color = x.left.color;
    x.left.color = RED;
    return x;
  }

  private Node rotateRight(Node h)
  {
    Node x = h.left;
    h.left = x.right;
    x.right = h;
    x.color = x.right.color;
    x.right.color = RED;
    return x;
  }

  private Node colorFlip(Node x)
  {
    x.color = !x.color;
    x.left.color = !x.left.color;
    x.right.color = !x.right.color;
    return x;
  }

  private boolean isRed(Node x)
  {
    if (x == null) return false;
    return (x.color == RED);
  }

  public SearchTree add(int key) {
    root = insert(root, key);
    return this;
  }

  public SearchTree delete(int key){
    root = delete(root, key);
    return this;
  }

  public void printTree(){
    print(root, "");
  }

  private void print(Node root, String prefix) {
    if (root == null) {
      System.out.println(prefix + "+- <null>");
      return;
    }

    System.out.println(prefix + "+- " + root.key);
    print(root.left,  prefix + "|  ");
    print(root.right, prefix + "|  ");
  }

}