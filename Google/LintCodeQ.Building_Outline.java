/*
Building Outline
Given N buildings in a x-axis，each building is a rectangle and can be represented 
by a triple (start, end, height)，where start is the start position on x-axis, 
end is the end position on x-axis and height is the height of the building. 
Buildings may overlap if you see them from far away，find the outline of them.

An outline can be represented by a triple, (start, end, height), 
where start is the start position on x-axis of the outline, end is the 
end position on x-axis and height is the height of the outline.
Example
Given 3 buildings：

[
  [1, 3, 3],
  [2, 4, 4],
  [5, 6, 1]
]
The outlines are：

[
  [1, 2, 3],
  [2, 4, 4],
  [5, 6, 1]
]
*/

public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
     class Edge {
        int pos;
        int height;
        boolean isStart;
        
        public Edge(int pos, int height, boolean isStart) {
            this.pos = pos;
            this.height = height;
            this.isStart = isStart;
        }
    } 
     
    public ArrayList<ArrayList<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (buildings == null || buildings.length == 0 || buildings[0].length == 0) {
            return result;
        }
        
        ArrayList<Edge> edges = new ArrayList<>();
        for (int[] building : buildings) {
            Edge startEdge = new Edge(building[0], building[2], true);
            edges.add(startEdge);
            Edge endEdge = new Edge(building[1], building[2], false);
            edges.add(endEdge);
        }
        
        // Collections.sort(edges, (a, b) -> {
        //     if (a.pos != b.pos) {
        //         return a.pos - b.pos;
        //     } else if (a.isStart && b.isStart) {
        //         return b.height - a.height;
        //     } else if (!a.isStart && !b.isStart) {
        //         return a.height - b.height;
        //     } else {
        //         return a.isStart ? -1 : 1;
        //     }
        // });
        
        Collections.sort(edges, new Comparator<Edge>(){
            @Override
            public int compare(Edge a, Edge b) {
                if (a.pos != b.pos) {
                    return a.pos - b.pos;
                } else if (a.isStart && b.isStart) {
                    //if pos is the same, and they are both starts, put higher height to left
                    return b.height - a.height;
                } else if (!a.isStart && !b.isStart) {
                    //if pos is the same, and they are both ends, put lower height to left
                    return a.height - b.height;
                } else {
                    return a.isStart ? -1 : 1;
                }
            }
        });
        
        //PriorityQueue<Integer> maxHeap = new PriorityQueue<>(2 * buildings.length, (a, b) -> b - a);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(2 * buildings.length, new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
        
        ArrayList<Integer> current = null;
        
        for (Edge edge : edges) {
            if (edge.isStart) {
                //start point
                if (maxHeap.isEmpty() || edge.height > maxHeap.peek()) {
                    current = new ArrayList<Integer>(Arrays.asList(edge.pos, edge.height));
                    result.add(current);
                }
                maxHeap.offer(edge.height);
            } else {
                //end point
                maxHeap.remove(edge.height);
                
                if (maxHeap.isEmpty() || edge.height > maxHeap.peek()) {
                    if (maxHeap.isEmpty()) {
                        current = new ArrayList<Integer>(Arrays.asList(edge.pos, 0));
                    } else {
                        //choose lower since the current edge is removed
                        current = new ArrayList<Integer>(Arrays.asList(edge.pos, maxHeap.peek()));
                    }
                    result.add(current);
                }
            }
        }
        
        ArrayList<ArrayList<Integer>> solution = new ArrayList<>();
        int start = result.get(0).get(0);
        int height = result.get(0).get(1);
        
        for (int i = 1; i < result.size(); i++) {
            ArrayList<Integer> now = new ArrayList<>();
            int end = result.get(i).get(0);
            if (height > 0) {
                now.addAll(Arrays.asList(start, end, height));
                solution.add(now);
            }
            start = end;
            height = result.get(i).get(1);
        }
        return solution;
    }
}


