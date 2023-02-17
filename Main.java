// Create random map with start point at {0,0} and end point at {1,1}.
// Move through map and try to reach {1, 1} using A* pathfinding.
// Only stores vital information using openList

class Main {
  static final int MAP_SIZE_X = 20;
  static final int MAP_SIZE_Y = 20;
  static final double DIFFICULTY = 0.3;
    
  static char[][] gameMap = new char[MAP_SIZE_X][MAP_SIZE_Y];

  static double[][][] openList = {{{0,0},{distance(0,0,MAP_SIZE_X,MAP_SIZE_Y),0,0}}}; // Used to store important information about the previous point
  
  public static void displayBoard() { // Displays the current state of the map.
    for(int x=0;x<MAP_SIZE_X;x++) {
      for(int y=0;y<MAP_SIZE_Y;y++) {
        System.out.print(gameMap[x][y] + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  static double distance(int x, int y, int x2, int y2) {
    return Math.sqrt(Math.pow((x-x2),2)+Math.pow((y-y2),2)); // Handy function for calculating euclidean distance
  }

  public static void A_Star(int x, int y) {
    //for(int i=0;i<openList.length;i++) {
      if(openList[0][0][0] == MAP_SIZE_X-1 && openList[0][0][1] == MAP_SIZE_Y-1) { // Check if we've arrived at the destination
        System.out.println("Computed!");
      } else {
        double currentH = openList[0][1][0]; // Check current distance to point
        System.out.println(currentH);
        
        int lowestX = 0;
        int lowestY = 0;
        double lowestF = Integer.MAX_VALUE;
        double lowestCost = 0;

        // All of the upcoming is just checking for open neighbors and calculating cost, heuristic, and f(cost+heuristic).
        
        if(x+1 < gameMap.length && gameMap[x+1][y] == '█') { 
          double cost = distance(x,y,x+1,y);
          double h = distance(x+1,y,MAP_SIZE_X,MAP_SIZE_Y);
          double f = cost+h;
          if(f<lowestF) {
            lowestX = x+1;
            lowestY = y;
            lowestF = f;
            lowestCost = cost;
          }
        }
        if(x+1 < gameMap.length && y+1 < gameMap.length && gameMap[x+1][y+1] == '█') {
          double cost = distance(x,y,x+1,y+1);
          double h = distance(x+1,y+1,MAP_SIZE_X,MAP_SIZE_Y);
          double f = cost+h;
          if(f<lowestF) {
            lowestX = x+1;
            lowestY = y+1;
            lowestF = f;
            lowestCost = cost;
          }
        }
        if(x-1 > 0 && gameMap[x-1][y] == '█') {
          double cost = distance(x,y,x-1,y);
          double h = distance(x-1,y,MAP_SIZE_X,MAP_SIZE_Y);
          double f = cost+h;
          if(f<lowestF) {
            lowestX = x-1;
            lowestY = y;
            lowestF = f;
            lowestCost = cost;
          }
        }
        if(y+1 < gameMap.length && gameMap[x][y+1] == '█') {
          double cost = distance(x,y,x,y+1);
          double h = distance(x,y+1,MAP_SIZE_X,MAP_SIZE_Y);
          double f = cost+h;
          if(f<lowestF) {
            lowestX = x;
            lowestY = y+1;
            lowestF = f;
            lowestCost = cost;
          }
        }
        if(x-1 > 0 && y+1 < gameMap.length && gameMap[x-1][y+1] == '█') {
          double cost = distance(x,y,x-1,y+1);
          double h = distance(x-1,y+1,MAP_SIZE_X,MAP_SIZE_Y);
          double f = cost+h;
          if(f<lowestF) {
            lowestX = x-1;
            lowestY = y+1;
            lowestF = f;
            lowestCost = cost;
          }
        }
        if(y-1 > 0 && gameMap[x][y-1] == '█') {
          double cost = distance(x,y,x,y-1);
          double h = distance(x,y-1,MAP_SIZE_X,MAP_SIZE_Y);
          double f = cost+h;
          if(f<lowestF) {
            lowestX = x;
            lowestY = y-1;
            lowestF = f;
            lowestCost = cost;
          }
        }
        if(x-1 > 0 && y-1 > 0 && gameMap[x-1][y-1] == '█') {
          double cost = distance(x,y,x-1,y-1);
          double h = distance(x-1,y-1,MAP_SIZE_X,MAP_SIZE_Y);
          double f = cost+h;
          if(f<lowestF) {
            lowestX = x-1;
            lowestY = y-1;
            lowestF = f;
            lowestCost = cost;
          }
        }
        if(x+1 < gameMap.length && y-1 > 0 && gameMap[x+1][y-1] == '█') {
          double cost = distance(x,y,x+1,y-1);
          double h = distance(x+1,y-1,MAP_SIZE_X,MAP_SIZE_Y);
          double f = cost+h;
          if(f<lowestF) {
            lowestX = x+1;
            lowestY = y-1;
            lowestF = f;
            lowestCost = cost;
          }
        }
        
        System.out.println(lowestX + ", " + lowestY);
        openList[0][0][0] = lowestX; // Storing new X coordinate
        openList[0][0][1] = lowestY; // Storing new Y coordinate
        openList[0][1][0] = distance(lowestX,lowestY,MAP_SIZE_X,MAP_SIZE_Y); // Store new heuristic
        openList[0][1][1] = openList[0][1][1] + lowestCost; // Store new total cost
        openList[0][1][2] = lowestF; // Store new f(g+h)
        gameMap[lowestX][lowestY] = 'X'; // Mark new point on map.
      }
    //}
  }
  
  public static void main(String[] args) {
    
    for(int x=0;x<MAP_SIZE_X;x++) {
      for(int y=0;y<MAP_SIZE_Y;y++) {
        double rand = Math.random();
        if(rand > 1-DIFFICULTY){ // Decide whether or not to place a barrier on this point
          gameMap[x][y] = '░';
        } else {
          gameMap[x][y] = '█';
        }
      }
    }
    gameMap[0][0] = 'O';
    gameMap[MAP_SIZE_X-1][MAP_SIZE_Y-1] = '█';


    try {
      while (true) {
        A_Star((int)openList[0][0][0],(int)openList[0][0][1]);
        
        displayBoard();
        Thread.sleep(2000); // Wait 1000 milliseconds
        System.out.print("\033[H\033[2J"); // Clear board
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    displayBoard();
  }
}