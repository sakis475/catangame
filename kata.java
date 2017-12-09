import java.util.*;

public class kata {

	public List<katanplayer> player;


	public int[][] board = new int[][] {

		/*w0*/{1,2,3,4,20,19},
		/*w1*/{7,8,9,10,22,21},
		/*w2*/{13,14,15,16,24,23},
		/*s0*/{4,5,6,7,21,20},
		/*s1*/{10,11,12,13,23,22},
		/*s2*/{1,19,24,16,17,18}
		
		};

		

	public int[][] neighbors = new int[][] {
		{0},
		{2,18,19},
		{1,3},
		{2,4},
		{3,5,20},
		{4,6},
		{5,7},
		{6,8,21},
		{7,9},
		{8,10},
		{9,11,22},
		{10,12},
		{11,13},
		{12,14,23},
		{13,15},
		{14,16},
		{15,17,24},
		{16,18},
		{17,1},
		{1,20,24},
		{4,19,21},
		{7,20,22},
		{10,21,23},
		{13,22,24},
		{16,19,23}
	};

	public int[][] roads = new int[25][25];


	//[koryfh] = 1 xtizeis, = id yparxei alos
	public int[] checkHouse = new int[25];

	public void initRoads() {
		//opu yparxei 1 mporeis na xtiseis
		//opu yparxei 0 einai adynato eite logo oti yparxei geitonas
		//eite gt den ginete app th arxh
		for(int i = 1; i < neighbors.length; i++) {
			for(int j = 0; j < neighbors[i].length; j++) {
				roads[i][(neighbors[i][j])] = 1;
			}
		}
	}

	public void initCheckHouse() {
		for(int i = 0 ; i < checkHouse.length; i++ ) {
			checkHouse[i] = 1;
		}
	}

	//diceRoll ginete mia fora th gyra gia olus tus paiktes ksexwra
	//dld prepei na trexei me to idio dice gia olus tus paiktes mia fora
	public void diceRoll (katanplayer player, int dice) {
			

		int[] turnGoods = new int[6];
		

		for(int i = 0; i < 6; i++){

			if (player.sHome.contains(board[dice][i])) {
				if(dice < 3) {
					turnGoods[0]++;
				} else if(dice < 6) {
					turnGoods[1]++;
				}
			}
		}

		
		for(int i = 0; i < 4 ; i++) {
			player.goods.set(i, player.goods.get(i) + turnGoods[i]);
		}
			
		

	}


	

	public void trade() {

	} 


	/* Pairnei 2 koryfes kai xtizei ena dromo */
	public void buildRoad(int k1, int k2, katanplayer player) {
		boolean checkRoad = false;

		//Arxika vlepw an den yparxe allos dromos k1-k2
		if(roads[k1][k2] == 1) {

			//epeita an yparxei spiti se mia apo tis korifes tote mporei na xtisei
			if(player.sHome.contains(k1) || player.sHome.contains(k2)) {
				roads[k1][k2] = player.id;
				roads[k2][k1] = player.id;
				
			} else {

			/*An yparxei dromos se geitona tote xtise*/
			for(int i = 0; i < neighbors[k1].length; i++) {
				if (roads[k1][neighbors[k1][i]] == player.id) {
					roads[k1][k2] = player.id;
					roads[k2][k1] = player.id;
					break;
				} 
			}
			for(int i = 0; i < neighbors[k2].length; i++) {
				if (roads[k2][neighbors[k2][i]] == player.id) {
					roads[k2][k1] = player.id;
					roads[k1][k2] = player.id;
					break;
				} 
			}
			}//end else


		} else {
			System.out.println("You can't build at " + k1 + "-" + k2);
		}


	}



	public void buildHouse(int koryfh, katanplayer player) {
		
		boolean checkRoad = false;

		//checkarei an yparxun estw enas dromos tu id player
		for(int i = 0; i < neighbors[koryfh].length; i++) {
			if(roads[koryfh][(neighbors[koryfh][i])] == player.id) {
				checkRoad = true;
				break;
			}
			
		}
		




		//an den einai piasmenh h koryfh kai yparxyn dromoi gyrw apo ayton tote xtise
		if(checkHouse[koryfh] == 1 && checkRoad) {
			checkHouse[koryfh] = player.id;
			player.sHome.add(koryfh); 
			findField(koryfh, player);
			for(int i = 0; i < neighbors[koryfh].length; i++)
			//thetei tus geitones ths koryfh se 0 gia na mhn mporyn sto melllon na xtisun alloi
			checkHouse[(neighbors[koryfh][i])] = 0;
		} else {
			System.out.println("Can't build at " + koryfh + " already builded by " + checkHouse[koryfh]);
		}

		




	}


	/*i einai ta xwrafia j einai oi korifes
	 *pairnei mia koryfh kai vriskh ta fields pu antistixun*/
	public void findField(int koryfh, katanplayer player) {
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(i < 3 && board[i][j] == koryfh) {
					
					player.fields.add(i);

				} else if (i < 6 && board[i][j] == koryfh) {

					player.fields.add(i);

				}
			}
		}
		
	}

	public void setPlayer(katanplayer player, int newkor1, int newkor2, int roada1, int roada2,
		int roadb1, int roadb2) {
		roads[roada1][roada2] = player.id;
		roads[roadb1][roadb2] = player.id;

		roads[roada2][roada1] = player.id;
		roads[roadb2][roadb1] = player.id;


		checkHouse[newkor1] = player.id;
		checkHouse[newkor2] = player.id;
		player.sHome.add(newkor1);
		player.sHome.add(newkor2);
		player.findField(newkor1);
		player.findField(newkor2);
	}
	
	public void startGame() {
		initRoads();
		initCheckHouse();
		player = new ArrayList<>();
		int id = 10;
		Scanner sc = new Scanner(System.in);
		System.out.println("How many players there are? ");
		int playerNumber = sc.nextInt();
		for(int i = 0; i < playerNumber; i++) {
			Scanner str = new Scanner(System.in);
			System.out.print("Name of Player"+(i+1) + " : ");
			String name = str.nextLine();
			player.add( (new katanplayer(id++,name)) );
		}

		for(int i = 0; i < playerNumber; i++) {
			Scanner str = new Scanner(System.in);
			System.out.println("INFO FOR: " + player.get(i).name);
			System.out.println("Give me First small house: ");
			int k1 = str.nextInt();
			System.out.println("Give me Second small house: ");
			int k2 = str.nextInt();
			System.out.println("Give me first coot road1");
			int ra1 = str.nextInt();
			System.out.println("Give me second coot road1");
			int ra2 = str.nextInt();
			System.out.println("Give me first coot road2");
			int rb1 = str.nextInt();
			System.out.println("Give me second coot road2");
			int rb2 = str.nextInt();
			setPlayer(player.get(i), k1, k2, ra1, ra2, rb1, rb2);
		}
		

	}

	public static void main(String[] args) {

		kata game = new kata();
		//O player1 exei sHome stis korifes 24, 22 
		game.startGame();

		System.out.println("Build House = 1\tBuild Road = 2");

		

		/*for(int i = 0; i < 25; i++) {
			for(int j = 0; j < 25; j++) {
				System.out.print(game.roads[i][j]);
			}
			System.out.println();
		} */
		/*for(int i = 0; i < player1.fields.size(); i++)
		System.out.println(player1.fields.get(i));*/

		/*for(int i = 0; i < player1.goods.size() ; i++)
			System.out.println(player1.goods.get(i));*/
		

	}



}//end of class

