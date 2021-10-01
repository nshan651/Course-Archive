package Lab_11;

class BTNode<E> {
    public E data;
    public BTNode<E> left;
    public BTNode<E> right;

    public BTNode(final E data, final BTNode<E> left, final BTNode<E> right) {
        if (data == null) throw new IllegalArgumentException("data is null");
        this.data = data;
        this.left = left;
        this.right = right;
    }
    public BTNode(final E data) { this(data, null, null); }

    <E> int size(final BTNode<E> root) {
        if (root == null) {
            return 0;
        } else {
            return (1 + size(root.left) + size(root.right));
        }
    }

    <E> int height(final BTNode<E> root) {
        if (root == null) {
            return 0;
        } else {
            return (1 + Math.max(height(root.left), height(root.right)));
        }
    }

    <E> void printTree(final BTNode<E> root) {
        if (root != null) {
            printTree(root.left);
            System.out.print(root.data + " ");
            printTree(root.right);
        }
    }

    <E> void printTreePostorder(final BTNode<E> root) {
        if (root != null) {
            printTreePostorder(root.left);
            printTreePostorder(root.right);
            System.out.print(root.data + " ");
        }
    }

    <E> void mirror(final BTNode<E> root) {
        if (root != null) {
            BTNode<E> swap = root.left;
            root.left = root.right;
            root.right = swap;
            mirror(root.left);
            /* Prints "inorder": left -> root -> right (or left-most node to right-most node) */
            System.out.print(root.data + " ");
            mirror(root.right);
        }
    }

    @Override public String toString() {
        final StringBuilder result = new StringBuilder();
        result.append("BTNode@");
        result.append(hashCode());
        result.append("(");
        result.append(data);
        result.append(", ");
        result.append(left != null ? "BTNode@" + left.hashCode() : ".");
        result.append(", ");
        result.append(right != null ? "BTNode@" + right.hashCode() : ".");
        result.append(")");
        return result.toString();
    }
}

class Main {
    public static void main(String[] args) {
        BTNode<Integer> empty = null;
        BTNode<Integer> leaf = new BTNode<>(9);

        /*Child Nodes  [4,5,8,7] */
        BTNode<Integer> a1 = new BTNode<>(4);
        BTNode<Integer> a2 = new BTNode<>(5);
        BTNode<Integer> a3 = new BTNode<>(8);
        BTNode<Integer> a4 = new BTNode<>(7);
        /* Second level [2,3,6] */
        BTNode<Integer> b1 = new BTNode<>(2, a1, a2);
        BTNode<Integer> b2 = new BTNode<>(6, a3, null);
        BTNode<Integer> b3 = new BTNode<>(3, b2, a4);
        /* Top level [1] */
        BTNode<Integer> root = new BTNode<>(1, b1, b3);
        //System.out.println(c1.toString());

        /* 1-Liner */
        BTNode<Integer> oneLine = new BTNode<>(1, new BTNode<>(2, new BTNode<>(4), new BTNode<>(5)),
                new BTNode<>(3, new BTNode<>(6, new BTNode<>(8), null), new BTNode<>(7)));

        /*
        System.out.println(root.size(root));
        System.out.println(root.height(root));
        root.printTree(root);
        System.out.println();
        root.printTreePostorder(root);
        System.out.println();
        */

        /* Mirror test */
        BTNode<Integer> m1 = new BTNode<>(1);
        BTNode<Integer> m2 = new BTNode<>(3);
        BTNode<Integer> m3 = new BTNode<>(5);
        BTNode<Integer> m4 = new BTNode<>(2, m1, m2);
        BTNode<Integer> mroot = new BTNode<>(4, m4, m3);
        //BTNode<Integer> mirrorTree = new BTNode<>(4, new BTNode<>(5), new BTNode<>(2, new BTNode<>(1), new BTNode<>(3)));
        mroot.printTree(mroot);
        System.out.println();
        mroot.mirror(mroot);
        //tree.mirror(root); // after


    }
}
