# 🧠 Recursion Patterns Cheat Sheet (with Java Examples)

## 1️⃣ Take / Not-Take (Subset Style)

**Idea:**  
At each step, decide whether to include or exclude the current element. Used in subset-sum, knapsack, or subsequence problems.

**Pattern:**
```java
boolean solve(int i, int target, int[] nums) {
    if (target == 0) return true;
    if (i == nums.length || target < 0) return false;

    // take current element
    boolean take = solve(i + 1, target - nums[i], nums);
    // skip current element
    boolean notTake = solve(i + 1, target, nums);

    return take || notTake;
}
```

**Examples:**
*   Subset Sum
*   Partition Equal Subset Sum
*   0/1 Knapsack
*   Subsequences

## 2️⃣ Permutation / Arrangement (Order Matters)

**Idea:**
Generate all possible orderings or arrangements of elements. Use a visited array or list to track used elements.

**Pattern:**
```java
void permute(List<Integer> path, boolean[] used, int[] nums, List<List<Integer>> res) {
    if (path.size() == nums.length) {
        res.add(new ArrayList<>(path));
        return;
    }

    for (int i = 0; i < nums.length; i++) {
        if (used[i]) continue;
        used[i] = true;
        path.add(nums[i]);

        permute(path, used, nums, res);

        path.remove(path.size() - 1);
        used[i] = false;
    }
}
```

**Examples:**
*   Permutations
*   N-Queens
*   Word Break
*   Combination Sum II

## 3️⃣ Divide and Conquer

**Idea:**
Split the problem into independent subproblems, solve them recursively, and combine results. Classic for sorting, searching, and tree-based problems.

**Pattern:**
```java
int solve(TreeNode root) {
    if (root == null) return 0;
    int left = solve(root.left);
    int right = solve(root.right);
    return Math.max(left, right) + 1; // combine result
}
```

**Examples:**
*   Merge Sort, Quick Sort
*   Binary Search
*   Tree Height / Diameter
*   Max Path Sum in Binary Tree

## 4️⃣ DFS / Exploration (Graph or Grid Traversal)

**Idea:**
Explore all directions or neighbors recursively. Used in grids, mazes, or graphs.

**Pattern:**
```java
void dfs(int i, int j, char[][] grid) {
    if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '#')
        return;

    grid[i][j] = '#'; // mark visited

    dfs(i + 1, j, grid);
    dfs(i - 1, j, grid);
    dfs(i, j + 1, grid);
    dfs(i, j - 1, grid);
}
```

**Examples:**
*   Maze Paths
*   Flood Fill
*   Number of Islands
*   Word Search

## 5️⃣ Recursive Building / Backtracking (Generate All Combinations)

**Idea:**
Build partial results step by step and backtrack after exploring each branch. Used when constructing strings, combinations, or structured outputs.

**Pattern:**
```java
void generate(String path, int open, int close, int n, List<String> res) {
    if (path.length() == 2 * n) {
        res.add(path);
        return;
    }
    if (open < n) generate(path + "(", open + 1, close, n, res);
    if (close < open) generate(path + ")", open, close + 1, n, res);
}
```

**Examples:**
*   Generate Parentheses
*   Power Set / Subsets
*   Letter Combinations of a Phone Number
*   Restore IP Addresses

## ⚙️ Bonus: Hybrid Patterns

Some problems mix patterns — for example:
*   **Combination Sum** → Take/Not-Take + Backtracking
*   **Unique Paths / Maze Problems** → DFS + Backtracking
*   **DP problems** often start as recursion + memoization → then optimized to iterative DP.

## 🧾 Summary Table

| Pattern            | Key Decision         | Order Matters | Common Use-Cases                |
| ------------------ | -------------------- | :-----------: | ------------------------------- |
| Take / Not-Take    | Include or Exclude   |      ❌       | Subsets, Knapsack               |
| Permutation        | Choose Next Element  |      ✅       | Permutations, N-Queens          |
| Divide & Conquer   | Split & Combine      |    Depends    | Sorting, Trees                  |
| DFS / Exploration  | Move to Neighbor     |    Depends    | Grids, Graphs                   |
| Build / Backtrack  | Build Partial Result |    Depends    | Generate Strings, Parentheses   |
