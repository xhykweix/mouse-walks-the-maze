import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.*;
import javax.swing.JFrame;
import java.io.*;
import javax.imageio.*;
class canvas extends CanvasBase{
   static int ponx = 400;
    static int pony = 400;
    static ArrayList<String> maze = new ArrayList<String>();
    static String[][] strmaze;
    static int con = 0;
    static int fps = 5;
    static String[] res;
    static String fileRes;
    Image image;
    public canvas() {
    	
    }
    
    public canvas(int f) {
        fps = f;
    }
    
    public void setRes(String[] res) {
        //�]�wres
        this.res = res;
    }
    
	public void paintCanvas(Graphics g) {
	    if(con<maze.size()){
	    	for(int x=0; x<ponx; x++){
	    		for(int y=0; y<pony; y++){
	    			strmaze[x][y] = maze.get(con);
	    			con+=1;
	    		}
	    	}
	    }
	    
	    for(int x=0; x<ponx; x++){
    		for(int y=0; y<pony; y++){
    			switch(strmaze[x][y]){
    				case "0":
    					try{
    						fileRes = this.res[Integer.parseInt(strmaze[x][y])];
		    				image = ImageIO.read(new File(fileRes));
			        		g.drawImage(image, y*50, x*50, 50, 50,null);
			        	}catch (Exception ex) {
			        	}
    					break;
    				case "1":
    					try{
    						fileRes = this.res[Integer.parseInt(strmaze[x][y])];
		    				image = ImageIO.read(new File(fileRes));
			        		g.drawImage(image, y*50, x*50, 50, 50,null);
			        	}catch (Exception ex) {
			        		g.fillRect(y*50,x*50,50,50);
			        	}
    					break;
    				case "2":
    					try{
    						fileRes = this.res[Integer.parseInt(strmaze[x][y])];
		    				image = ImageIO.read(new File(fileRes));
			        		g.drawImage(image, y*50, x*50, 50, 50,null);
			        	}catch (Exception ex) {
		    				g.fillOval(y*50+10,x*50+10,30,30);
			       		}
    					break;
    				case "3":
    					try{
    						fileRes = this.res[Integer.parseInt(strmaze[x][y])];
		    				image = ImageIO.read(new File(fileRes));
			        		g.drawImage(image, y*50, x*50, 50, 50,null);
			        	}catch (Exception ex) {
		    				g.fillRect(y*50+20,x*50+20,10,10);
			        	}
    					break;
    				case "4":
    					try{
    						fileRes = this.res[Integer.parseInt(strmaze[x][y])];
		    				image = ImageIO.read(new File(fileRes));
			        		g.drawImage(image, y*50, x*50, 50, 50,null);
			        	}catch (Exception ex) {
		    				g.fillOval(y*50+10,x*50+10,30,30);
			        	}
    					break;
				case "5":
					try{
    						fileRes = this.res[Integer.parseInt(strmaze[x][y])];
		    				image = ImageIO.read(new File(fileRes));
			        		g.drawImage(image, y*50, x*50, 50, 50,null);
			        	}catch (Exception ex) {
			        		g.fillRect(y*50,x*50,50,50);
			        	}
    					break;
                                 case "6":
				         try{
    						fileRes = this.res[Integer.parseInt(strmaze[x][y])];
		    				image = ImageIO.read(new File(fileRes));
			        		g.drawImage(image, y*50, x*50, 50, 50,null);
			        	}catch (Exception ex) {
			        		g.fillRect(y*50,x*50,50,50);
			        	}
    					break;
    				default:
    					try{
    						fileRes = this.res[Integer.parseInt(strmaze[x][y])];
		    				image = ImageIO.read(new File(fileRes));
			        		g.drawImage(image, y*50, x*50, 50, 50,null);
			        	}catch (Exception ex) {
		    				g.fillOval(y*50+10,x*50+10,30,30);
			        	}
    					break;
    			}
    		}
    	}
    }
    
   public void updateMaze(int[][] m){
		//��s�g�c
		for(int i=0; i<m.length; i++){
    		for(int j=0; j<m[i].length; j++){
    			maze.add(m[i][j]+"");
    		}
    	}
    }
    
    public void initMaze(int[][] m){
    	//��l�ưg�c
    	for(int i=0; i<m.length; i++){
    		for(int j=0; j<m[i].length; j++){
    			maze.add(m[i][j]+"");
    		}
    	}
    	
    	ponx = m.length;
    	pony = m[0].length;
    	
        strmaze = new String[ponx][pony];
    	JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        canvas canvas = new canvas();
        jf.getContentPane().add(canvas);
        System.out.println("FPS = " + fps);
        canvas.setFPS(fps);
        canvas.startPaint();
        
        jf.setBounds(100, 100, ponx*50+100, pony*50+100);
        jf.setVisible(true);
       }
}
public class mazecase extends canvas{

	static int a=1,count;
	static int b=1;
    static canvas can = new canvas();
    //setRes �]�wres
    //initMaze ��l�ưg�c
    //updateMaze ��s�g�c
    public static void main(String[] args) {
    	String[] res = {
    		"",//��0
    		"res/ff.png",//�j��1
    		"res/ww.png",//���I2
    		"",//���L����//3
    		"res/aa.png",//���O��4
			"res/zz.PNG",//����5
                        "res/tt.png", //�Q�t6   
    	};
    	can.setRes(res);
    	
        Scanner sca=new Scanner(System.in);
    	
    	System.out.print("�п�J�g�c������e(�ХHSPACE�j�})�G");
    	int maze[][]=new int[sca.nextInt()+2][sca.nextInt()+2];
    	System.out.println(maze.length+"*"+maze[0].length+"�g�c�w�g�ئn�F");
    	buildfence(maze);
		can.initMaze(maze);
    	gomaze(maze);
        
    }
    public static void buildfence(int[][] maze)
    {
    	for(int i=0;i<maze.length;i++){
    		for(int j=0;j<maze[0].length;j++){
    			if(i==0||j==0||i==maze.length-1 || j==maze[0].length-1)
    				maze[i][j] = 1;//outwall			
    			else if(i%2==0 || j%2==0)
    				maze[i][j] = 5;//inwall
    			else
    				maze[i][j] = 8;//beforeroad
    		}
    	}
    	//--------------------
    	boolean checkwall = false;
    	count = (int)(Math.random() * 1000000 % 4);
    	if(count == 0)maze[1][1] = 0;
    	else if(count == 1)maze[maze.length-2][1] = 0;
    	else if(count == 2)maze[maze.length-2][maze[0].length-2] = 0;
    	else maze[1][maze[0].length-2] = 0;
    	while(checkwall == false){
    		int x,y,wallcount = 0;//count��j���O�_�i�H�����A�åB���ư���C
    		while(true){//�H���D��inwall
    			y = (int)(Math.random() * 1000000 % maze.length-1);
    			x = (int)(Math.random() * 1000000 % maze[0].length-1);
    			if(x != 0 && y !=0 && (x % 2 == 0 || y % 2 == 0) && maze[y][x] == 5){
    				maze[y][x] = 8;
    				break;
    			}
    		}
    		for(int i=0;i<maze.length;i++){//�T�{���䦳�L��
        		for(int j=0;j<maze[0].length;j++){
        			if(maze[i][j] == 0){
        				if(maze[i-1][j] == 8){
        					wallcount++;
        					maze[i-1][j] = 0;
        				}
        				if(maze[i+1][j] == 8){
        					wallcount++;
        					maze[i+1][j] = 0;
        				}
        				if(maze[i][j-1] == 8){
        					wallcount++;
        					maze[i][j-1] = 0;
        				}
        				if(maze[i][j+1] == 8){
        					wallcount++;
        					maze[i][j+1] = 0;
        				}
        			}
        			if(i == maze.length-1 && j == maze[0].length-1 && wallcount != 0){
        				i = 0;j = 0;
        				wallcount = 0;
        			}
        		}
        	}
    		for(int i=0;i<maze.length;i++){
        		for(int j=0;j<maze[0].length;j++){
        			if(maze[i][j] == 0 && maze[i-1][j] == 0 && maze[i+1][j] == 0){//�W���U
        				if(maze[i][j-1] == 0 && maze[i][j+1] == 0){//���k
        					if(maze[i+1][j+1] == 0 && maze[i+1][j-1] == 0 && maze[i-1][j+1] == 0 && maze[i-1][j-1] == 0){//���W���U�k�W�k�U
        						maze[i][j] = 1;
        					}
        				}
        			}
        		}
        	}
    		checkwall = true;
    		for(int [] i:maze){
    			for(int j:i){
    				if(j == 8)
    					checkwall = false;
    			}
    		}
    	}
		maze[1][1]=4;//�_�I
    	maze[maze.length-2][maze[0].length-2]=2;//���D
        maze[maze.length-5][maze[0].length-5]=6;//�Q�t
    }//1:wall,0:road,4:start,2:end,3:walkbefore	
public static void gomaze(int[][] maze)
    {
    	int step=0;
    	int[][] memoryback=new int[maze.length*maze[0].length][3];
    	int dir=0;
    	int routelnd=0;
    	
    	stop:while(true)
    	{
    		if (maze[a][b]==2)
    		{
    			System.out.print("�ТעܢբݡI�I");
                        can.updateMaze(maze);
    			break stop;
    		}
    		else if(maze[a][b]==6)
			{
				System.out.print("�Q�Ǫ��Y���F");
				can.updateMaze(maze);
				break stop;
			}
			else
    		{
    			maze[a][b]=4;
    		}
    		can.updateMaze(maze);
                switch(dir)
    		{
    			case 0:
    				if (maze[a][b+1]==0||maze[a][b+1]==2||maze[a][b+1]==6)
    				{
    					memoryback[routelnd][0]=a;
    					memoryback[routelnd][1]=b;
    					memoryback[routelnd++][2]=dir;
    					maze[a][b]=3;
    					b++;
    					dir=0;
    					step++;
    				}
    				else
    				{
    					dir++;
    				}
    				break;
    			case 1:
    				if (maze[a+1][b]==0||maze[a+1][b]==2||maze[a+1][b]==6)
    				{
    					memoryback[routelnd][0]=a;
    					memoryback[routelnd][1]=b;
    					memoryback[routelnd++][2]=dir;
    					maze[a][b]=3;
    					a++;
    					dir=0;
    					step++;
    				}
    				else
    				{
    					dir++;
    				}
    				break;
    			case 2:
    				if (maze[a-1][b]==0||maze[a-1][b]==2||maze[a-1][b]==6)
    				{
    					memoryback[routelnd][0]=a;
    					memoryback[routelnd][1]=b;
    					memoryback[routelnd++][2]=dir;
    					maze[a][b]=3;
    					a--;
    					dir=0;
    					step++;
    				}
    				else
    				{
    					dir++;
    				}
    				break;
    			case 3:
    				if (maze[a][b-1]==0||maze[a][b-1]==2||maze[a][b-1]==6)
    				{
    					memoryback[routelnd][0]=a;
    					memoryback[routelnd][1]=b;
    					memoryback[routelnd++][2]=dir;
    					maze[a][b]=3;
    					b--;
    					dir=0;
    					step++;
    				}
    				else
    				{
    					dir++;
    				}
    				break;
    			case 4:
    				if (--routelnd<0)
    				{
    					System.out.println("�w�g�S�����F");
    					break stop;
    				}
    				maze[a][b]=3;
    				a=memoryback[routelnd][0];
    				b=memoryback[routelnd][1];
    				dir=memoryback[routelnd][2]+1;
    		}
    	}
    System.out.println("�`�@��"+step+"�B");
    }
}    