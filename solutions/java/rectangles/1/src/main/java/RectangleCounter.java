class RectangleCounter {
    int countRectangles(String[] grid) {
        int countRect = 0;
        for(int i=0; i< grid.length; i++){
            for(int j=0; j< grid[i].length(); j++){
                if(grid[i].charAt(j)== '+'){
                    for(int k= j+1; k < grid[i].length(); k++){
                        if(grid[i].substring(j, k+1).matches("^\\+[+-]*\\+$")){
                            for(int n = i+1; n < grid.length; n++){
                                if(grid[n].substring(j,k+1).matches("^[+|].*[+|]$")){
                                    if(grid[n].substring(j,k+1).matches("^\\+[+-]*\\+$")) countRect++;
                                }
                                else break;
                            }
                        }
                    }
                  } 
            }
        }
    return countRect;  
  }
}