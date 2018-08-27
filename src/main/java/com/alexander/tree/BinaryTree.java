package com.alexander.tree;

public class BinaryTree<T extends Comparable> {
    //根节点
    private TreeNode<T> root;

    //定义结点
    private static class TreeNode<T extends Comparable> {
        private TreeNode<T> left;
        private TreeNode<T> right;
        private T data;

        TreeNode(T data) {
            this.data = data;
        }
    }

    /**
     * 插入结点
     *
     * @param data insert data
     */
    public void insert(T data) {
        TreeNode<T> newNode = new TreeNode<>(data);
        TreeNode<T> currNode = root;
        TreeNode<T> parentNode;

        //空树插入
        if (root == null) {
            root = newNode;
            return;
        }

        while (true) {
            parentNode = currNode;
            //判定插入值的大小，大的往右插直到插到空位
            if (data.compareTo(currNode.data) > 0) {
                currNode = currNode.right;
                if (currNode == null) {
                    parentNode.right = newNode;
                    return;
                }
            } else {
                //判定插入值的大小，小的往左插直到插到空位
                currNode = currNode.left;
                if (currNode == null) {
                    parentNode.left = newNode;
                    return;
                }
            }
        }
    }

    /**
     * 前序遍历
     *
     * @param currentNode input start node
     */
    public void preOrder(TreeNode currentNode) {
        if (currentNode == null)
            return;
        System.out.print(currentNode.data + " ");
        preOrder(currentNode.left);
        preOrder(currentNode.right);
    }

    /**
     * 中序遍历
     *
     * @param currentNode input start node
     */
    public void inOrder(TreeNode currentNode) {
        if (currentNode == null)
            return;
        inOrder(currentNode.left);
        System.out.print(currentNode.data + " ");
        inOrder(currentNode.right);
    }

    /**
     * 后序遍历
     *
     * @param currentNode input start node
     */
    public void postOrder(TreeNode currentNode) {
        if (currentNode == null)
            return;
        postOrder(currentNode.left);
        postOrder(currentNode.right);
        System.out.print(currentNode.data + " ");
    }

    /**
     * 查找结点
     *
     * @param data 待查找结点
     * @return
     */
    public TreeNode<T> find(T data) {
        TreeNode<T> currNode = root;
        while (currNode != null) {
            if (data.compareTo(currNode.data) > 0) {
                //大数向右
                currNode = currNode.right;
            } else if (data.compareTo(currNode.data) < 0) {
                //小数向左
                currNode = currNode.left;
            } else {
                //找到返回
                return currNode;
            }
        }
        return null;
    }

    /**
     * 删除结点 分为3种情况
     * 1.叶子结点
     * 2.该节点有一个子节点
     * 3.该节点有二个子节点
     *
     * @param data 待删除值
     */
    public boolean delete(T data) throws Exception {
        TreeNode<T> curr = root;
        //保持一个父节点的引用
        TreeNode<T> parent = curr;

        //删除结点是左子结点还是右子结点，
        boolean isLeft = true;
        while (curr != null && curr.data.compareTo(data) != 0) {
            parent = curr;
            if (data.compareTo(curr.data) > 0) {
                curr = curr.right;
                isLeft = false;
            } else {
                curr = curr.left;
                isLeft = true;
            }
        }

        if (curr == null) {
            throw new Exception("要删除的结点不存在");
        }

        //第一种情况,要删除的结点为叶子结点
        if (curr.left == null && curr.right == null) {
            if (curr == root) {
                root = null;
                return true;
            }
            if (isLeft) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (curr.left == null) {
            //第二种情况，要删除的结点有一个子节点且是右子结点
            if (curr == root) {
                root = curr.right;
                return true;
            }
            if (isLeft) {
                parent.left = curr.right;//如果该节点是父节点的左结点，那么将父节点的左子节点指向该节点的右子节点
            } else {
                parent.right = curr.right;//如果该节点是父节点的右结点，那么将父节点的右子节点指向该节点的右子节点
            }
        } else if (curr.right == null) {
            //第二种情况，要删除的结点有一个子节点且是左子结点
            if (curr == root) {
                root = curr.left;
                return true;
            }
            if (isLeft) {
                parent.left = curr.left;//如果该节点是父节点的左结点，那么将父节点的左子节点指向该节点的左子节点
            } else {
                parent.right = curr.left;//如果该节点是父节点的右结点，那么将父节点的左子节点指向该节点的左子节点
            }
        } else {

            //第三种情况，也是最复杂的一种情况，要删除的结点有两个子节点，需要找寻中序后继结点
            TreeNode<T> succeeder = getSucceeder(curr);
            if (curr == root) {
                root = succeeder;
                return true;
            }
            if (isLeft) {
                parent.left = succeeder;
            } else {
                parent.right = succeeder;
            }
            //当后继结点为删除结点的右子结点
            succeeder.left = curr.left;

        }
        return true;
    }

    public TreeNode<T> getSucceeder(TreeNode<T> delNode) {
        TreeNode<T> succeeder = delNode;
        TreeNode<T> parent = delNode;
        TreeNode<T> currNode = delNode.right;
        //寻找后继结点
        while (currNode != null) {//去右子树找最左边的结点即为后续结点
            parent = succeeder;
            succeeder = currNode;
            currNode = currNode.left;
        }
        //如果后继结点不是要删除结点的右子结点
        if (succeeder != delNode.right) {
            parent.left = succeeder.right;
            //将后继结点的左右子结点分别指向要删除结点的左右子节点
            succeeder.left = delNode.left;
            succeeder.right = delNode.right;
        }
        return succeeder;
    }

    public static void main(String[] args) throws Exception{
        BinaryTree<String> binaryTree = new BinaryTree<>();
        //插入操作必须根据自己的结构一层一层，从左到右插入，才能保证树的结构
        binaryTree.insert("D");
        binaryTree.insert("B");
        binaryTree.insert("F");
        binaryTree.insert("A");
        binaryTree.insert("C");
        binaryTree.insert("E");
        binaryTree.insert("G");
        //前序遍历
        System.out.println("前序遍历：");
        binaryTree.preOrder(binaryTree.root);
        System.out.println();
        //中序遍历
        System.out.println("中序遍历：");
        binaryTree.inOrder(binaryTree.root);
        System.out.println();
        //后序遍历
        System.out.println("后序遍历：");
        binaryTree.postOrder(binaryTree.root);
        System.out.println();
        //查找结点
        TreeNode node = binaryTree.find("A");
        System.out.println("找到结点，其值为：" + node.data);
        //删除结点
        binaryTree.delete("B");
        System.out.print("删除结点B:\n    前序遍历:");
        binaryTree.preOrder(binaryTree.root);
        System.out.println();
        System.out.print("    中序遍历:");
        binaryTree.inOrder(binaryTree.root);
    }

}
