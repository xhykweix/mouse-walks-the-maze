import java.util.*;

public class mazetest {
	static int cx = 0 , cy = 0 , dir = 0,count=0,ind=0;
	
    public static void main(String[] args) {
        int [][] maze = new int [rint()+2][rint()+2];
        createmaze(maze);
        sop("\n");
        pmaze(maze);
        sop("\nstart(x):");
        int sx=rint();
        sop("start(y):");
        int sy=rint();
        sop("end(x):");
        int ex=rint();
        sop("end(y):");
        int ey=rint();
        maze[sy][sx]=5;
        maze[ey][ex]=6;
        pmaze(maze);
        mouse(maze,sx,sy);
    }
    static void createmaze(int [][] m){
    	for(int i=0;i<m.length;i++){
    		for(int j=0;j<m[0].length;j++){
    			if(i==0 || j==0 || i==m.length-1 || j==m[0].length-1)
    				m[i][j] = 1;//outwall			
    			else if(i%2==0 || j%2==0)
    				m[i][j] = 2;//inwall
    			else
    				m[i][j] = 8;//beforeroad
    		}
    	}
    	//--------------------
    	boolean checkwall = false;
    	count = (int)(Math.random() * 1000000 % 4);
    	if(count == 0)m[1][1] = 0;
    	else if(count == 1)m[m.length-2][1] = 0;
    	else if(count == 2)m[m.length-2][m[0].length-2] = 0;
    	else m[1][m[0].length-2] = 0;
    	while(checkwall == false){
    		int x,y,wallcount = 0;//count算隔壁是否可以替換，並且重複執行。
    		while(true){//隨機挑選inwall
    			y = (int)(Math.random() * 1000000 % m.length-1);
    			x = (int)(Math.random() * 1000000 % m[0].length-1);
    			if(x != 0 && y !=0 && (x % 2 == 0 || y % 2 == 0) && m[y][x] == 2){
    				m[y][x] = 8;
    				break;
    			}
    		}
    		for(int i=0;i<m.length;i++){//確認旁邊有無路
        		for(int j=0;j<m[0].length;j++){
        			if(m[i][j] == 0){
        				if(m[i-1][j] == 8){
        					wallcount++;
        					m[i-1][j] = 0;
        				}
        				if(m[i+1][j] == 8){
        					wallcount++;
        					m[i+1][j] = 0;
        				}
        				if(m[i][j-1] == 8){
        					wallcount++;
        					m[i][j-1] = 0;
        				}
        				if(m[i][j+1] == 8){
        					wallcount++;
        					m[i][j+1] = 0;
        				}
        			}
        			if(i == m.length-1 && j == m[0].length-1 && wallcount != 0){
        				i = 0;j = 0;
        				wallcount = 0;
        			}
        		}
        	}
    		for(int i=0;i<m.length;i++){
        		for(int j=0;j<m[0].length;j++){
        			if(m[i][j] == 0 && m[i-1][j] == 0 && m[i+1][j] == 0){//上中下
        				if(m[i][j-1] == 0 && m[i][j+1] == 0){//左右
        					if(m[i+1][j+1] == 0 && m[i+1][j-1] == 0 && m[i-1][j+1] == 0 && m[i-1][j-1] == 0){//左上左下右上右下
        						m[i][j] = 1;
        					}
        				}
        			}
        		}
        	}
    		checkwall = true;
    		for(int [] i:m){
    			for(int j:i){
    				if(j == 8)
    					checkwall = false;
    			}
    		}
    	}
    }//-----------------------------------
    static void mouse(int [][] m , int sx,int sy){
    	int [][] stor = new int [(m.length-2)*(m[0].length-2)][3];
    	cy=sy;
    	cx=sx;
    	while(true){
    		if(m[cy][cx]==6){
				sop("\nGood Job,We got it");
				return;
    		}
    		else
    			m[cy][cx]=3;
    		pmaze(m);
    		switch(dir){
    			case 0://right0
    				if((m[cy][cx+1] == 0)||(m[cy][cx+1] == 6)){
    					m[cy][cx ] = 4;
    					stor[ind][0]=cy;
    					stor[ind][1]=cx;
    					stor[ind++][2]=dir;
    					cx+= 1;
    					dir = 0;
    				}
    				else{
    					dir++;
    				}
    				break;
    			case 1://down1
    				if((m[cy+1][cx] == 0)||(m[cy+1][cx] == 6)){
    					m[cy][cx] = 4;
    					stor[ind][0]=cy;
    					stor[ind][1]=cx;
    					stor[ind++][2]=dir;
    					cy+= 1;
    					dir = 0;
    				}
    				else{
    					dir++;
    				}
    				break;
    			case 2://left2
    				if((m[cy][cx-1] == 0)||(m[cy][cx-1] == 6)){
    					m[cy][cx] = 4;
    					stor[ind][0]=cy;
    					stor[ind][1]=cx;
    					stor[ind++][2]=dir;
    					cx-= 1;
    					dir = 0;
    				}
    				else{
    					dir++;
    				}
    				break;
    			case 3://up3
    				if((m[cy-1][cx] == 0)||(m[cy-1][cx] == 6)){
    					m[cy][cx] = 4;
    					stor[ind][0]=cy;
    					stor[ind][1]=cx;
    					stor[ind++][2]=dir;
    					cy-= 1;
    					dir = 0;
    				}
    				else{
    					dir++;
    				}
    				break;
    			default:
    				if(--ind < 0){
    					sop("\ndead mouse");
    					return;
    				}
    				m[cy][cx]=7;
    				cy=stor[ind][0];
    				cx=stor[ind][1];
    				dir=stor[ind][2];
    		}
    	}
    }
    static void pmaze(int [][] m){
    	sop("\n");
    	int k=0;
    	for(int [] i:m){
    		System.out.printf("%2d",k++);
    		for(int j:i){
    			switch(j){
    				case 0:
    					sop("  ");//beforeroad
    					break;
    				case 8:
    					sop("ｘ");//spaceroad
    					break;
    				case 1:
    					sop("█");//outwall
    					break;
    				case 2:
    					sop("█");//inwall
    					break;
    				case 3:
    					sop("＠");//mouse
    					break;
    				case 4:
    					sop("。");//road
    					break;
    				case 5:
    					sop("ｏ");
    					break;
    				case 6:
    					sop("ｘ");
    					break;
    				case 7:
    					sop("、");
    					break;
    			}
    		}
    		sop("\n");
    	}
    	sop("  ");
    	for(int i=0;i<m[0].length;i++){
    		System.out.printf("%2d",i);
    	}
    }
    static int rint(){
    	Scanner data = new Scanner( System.in );
    	return data.nextInt();
    }
    static void sop(String s){
    	System.out.print(s);
    }
}