def tree_from_traversals(preorder, inorder):
    if len(preorder) != len(inorder):
        raise ValueError("traversals must have the same length")

    if set(preorder) != set(inorder):
        raise ValueError("traversals must have the same elements")

    if len(set(preorder)) != len(preorder):
        raise ValueError("traversals must contain unique items")

    if not preorder:
        return {}

    root_val = preorder[0]

    root_index_inorder = inorder.index(root_val)

    left_inorder = inorder[:root_index_inorder]
    right_inorder = inorder[root_index_inorder + 1:]

    left_preorder = preorder[1: 1 + len(left_inorder)]
    right_preorder = preorder[1 + len(left_inorder):]

    return {
        "v": root_val,
        "l": tree_from_traversals(left_preorder, left_inorder),
        "r": tree_from_traversals(right_preorder, right_inorder)
    }